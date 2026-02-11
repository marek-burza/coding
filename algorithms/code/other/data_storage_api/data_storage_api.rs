use axum::{
    Json, Router,
    extract::{Path, State},
    http::StatusCode,
    routing::{delete, get, put},
};
use serde::{Deserialize, Serialize};
use sha2::{Digest, Sha256};
use std::{
    collections::HashMap,
    sync::{Arc, RwLock},
};
#[cfg(not(test))]
use tokio::net::TcpListener;

#[derive(Deserialize, Serialize)]
pub struct PutReply {
    oid: String,
}

/*
Download an Object

GET /data/{repository}/{objectID}
Response

Status: 200 OK
{object data}
Objects that are not on the server will return a 404 Not Found.
*/
pub async fn get_data(
    State(state): State<SharedState>,
    Path((repository, object_id)): Path<(String, String)>,
) -> (StatusCode, axum::body::Bytes) {
    assert!(!repository.is_empty());
    let state = state.read().unwrap();
    let result = state.storage.get(&object_id);
    if let Some(bytes) = result {
        return (StatusCode::OK, bytes.clone());
    }
    (StatusCode::NOT_FOUND, axum::body::Bytes::new())
}

/*
Upload an Object

PUT /data/{repository}
{object data}
Response

Status: 200 OK
'{"oid": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}'
*/
pub async fn put_data(
    State(state): State<SharedState>,
    Path(repository): Path<String>,
    bytes: axum::body::Bytes,
) -> (StatusCode, Json<PutReply>) {
    let identifier = format!(
        "{}{}",
        repository,
        String::from_utf8_lossy(&bytes).into_owned(),
    );
    let mut hasher = Sha256::new();
    hasher.update(identifier);
    let result = hasher.finalize();
    let object_id = format!("{:x}", result);
    state
        .write()
        .unwrap()
        .storage
        .insert(object_id.clone(), bytes);
    let reply = PutReply {
        oid: object_id.to_string(),
    };
    (StatusCode::OK, Json(reply))
}

/*
Remove an Object

DELETE /data/{repository}/{objectID}
Response

Status: 200 OK
Objects that are not on the server will return a 404 Not Found.
*/
pub async fn delete_data(
    State(state): State<SharedState>,
    Path((repository, object_id)): Path<(String, String)>,
) -> StatusCode {
    assert!(!repository.is_empty());
    let mut state = state.write().unwrap();
    if state.storage.remove(&object_id).is_some() {
        return StatusCode::OK;
    }
    StatusCode::NOT_FOUND
}

#[derive(Default)]
pub struct AppState {
    storage: HashMap<String, axum::body::Bytes>,
}

pub type SharedState = Arc<RwLock<AppState>>;

fn create_router(shared_state: &SharedState) -> Router {
    Router::new()
        .route("/data/:repository/:object_id", get(get_data))
        .route("/data/:repository", put(put_data))
        .route("/data/:repository/:object_id", delete(delete_data))
        .with_state(Arc::clone(shared_state))
}

#[cfg(not(test))]
#[tokio::main]
pub async fn main() {
    // We'll store the data in memory in a map.
    let shared_state = SharedState::default();
    let router = create_router(&shared_state);
    let listener = TcpListener::bind("0.0.0.0:8282").await.unwrap();
    axum::serve(listener, router).await.unwrap();
}

#[cfg(test)]
mod tests {
    use super::*;
    use axum::{
        Router,
        body::{Body, to_bytes},
        http::{self, Request, StatusCode},
    };
    use tower::util::ServiceExt;

    async fn get_blob(router: Router, repository: String, oid: String) -> (String, StatusCode) {
        let path = format!("/data/{}/{}", repository, oid);
        let request = Request::builder()
            .method(http::Method::GET)
            .uri(path)
            .body(Body::empty())
            .expect("Failed to build request");
        let response = router.oneshot(request).await.expect("Request failed");
        let status = response.status();
        let body = response.into_body();
        let bytes = to_bytes(body, i32::MAX as usize).await.unwrap();
        let string = String::from_utf8(bytes.to_vec()).unwrap();
        (string, status)
    }

    async fn put_blob(router: Router, repository: String, payload: String) -> (String, StatusCode) {
        let path = format!("/data/{}", repository);
        let request = Request::builder()
            .method(http::Method::PUT)
            .uri(path)
            .body(Body::from(payload))
            .expect("Failed to build request");
        let response = router.oneshot(request).await.expect("Request failed");
        let status = response.status();
        let body = response.into_body();
        let bytes = to_bytes(body, i32::MAX as usize).await.unwrap();
        let string = String::from_utf8(bytes.to_vec()).unwrap();
        let put_reply: PutReply = serde_json::from_str(&string).expect("Failed to parse JSON!");
        let oid = put_reply.oid;
        (oid, status)
    }

    async fn delete_blob(router: Router, repository: String, oid: String) -> StatusCode {
        let path = format!("/data/{}/{}", repository, oid);
        let request = Request::builder()
            .method(http::Method::DELETE)
            .uri(path)
            .body(Body::empty())
            .expect("Failed to build request");
        let response = router.oneshot(request).await.expect("Request failed");
        response.status()
    }

    #[tokio::test]
    async fn test_put() {
        let shared_state = SharedState::default();
        let router = create_router(&shared_state);
        let payload1 = "something".to_string();
        let (oid1, status1) = put_blob(router.clone(), "repository".to_string(), payload1).await;
        let payload2 = "other".to_string();
        let (oid2, status2) = put_blob(router.clone(), "repository".to_string(), payload2).await;
        assert_ne!(oid1, oid2);
        assert_eq!(status1, StatusCode::OK);
        assert_eq!(status2, StatusCode::OK);
    }

    #[tokio::test]
    async fn test_get() {
        let shared_state = SharedState::default();
        let router = create_router(&shared_state);
        let content1 = "something".to_string();
        let (oid1, _) = put_blob(router.clone(), "repository".to_string(), content1.clone()).await;
        let content2 = "other".to_string();
        let (oid2, _) = put_blob(router.clone(), "repository".to_string(), content2.clone()).await;
        let (body, status) = get_blob(router.clone(), "repository".to_string(), oid1).await;
        assert_eq!(status, StatusCode::OK);
        assert_eq!(body, content1);
        let (body, status) = get_blob(router.clone(), "repository".to_string(), oid2).await;
        assert_eq!(status, StatusCode::OK);
        assert_eq!(body, content2);
    }

    #[tokio::test]
    async fn test_delete() {
        let shared_state = SharedState::default();
        let router = create_router(&shared_state);
        let content = "something".to_string();
        let (oid, _) = put_blob(router.clone(), "repository".to_string(), content.clone()).await;
        let status = delete_blob(router.clone(), "repository".to_string(), oid.clone()).await;
        assert_eq!(status, StatusCode::OK);
        let (_, status) = get_blob(router.clone(), "repository".to_string(), oid.clone()).await;
        assert_eq!(status, StatusCode::NOT_FOUND);
    }

    async fn free_form(
        router: Router,
        method: http::Method,
        items: &[String],
        payload: Option<String>,
    ) -> (String, String, StatusCode) {
        let path = format!("/data/{}", items.join("/"));
        let body = if let Some(content) = payload {
            Body::from(content)
        } else {
            Body::empty()
        };
        let request = Request::builder()
            .method(method)
            .uri(path)
            .body(body)
            .expect("Failed to build request");
        let response = router.oneshot(request).await.expect("Request failed");
        let status = response.status();
        let body = response.into_body();
        let bytes = to_bytes(body, i32::MAX as usize).await.unwrap();
        let string = String::from_utf8(bytes.to_vec()).unwrap();
        let oid = match serde_json::from_str::<PutReply>(&string) {
            Ok(put_reply) => put_reply.oid,
            Err(_) => "".to_string(),
        };
        (string, oid, status)
    }

    #[tokio::test]
    async fn test_data_store_extended() {
        let shared_state = SharedState::default();
        let router = create_router(&shared_state);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::POST,
            &[
                "data".to_string(),
                "reg1".to_string(),
                "content1".to_string(),
            ],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::GET,
            &["data".to_string(), "reg1".to_string()],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::GET,
            &[
                "data".to_string(),
                "reg1".to_string(),
                "obj3".to_string(),
                "con3".to_string(),
            ],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::PUT,
            &["data".to_string(), "reg5".to_string()],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::METHOD_NOT_ALLOWED);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::PUT,
            &["data".to_string(), "reg4".to_string(), "reg4".to_string()],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::DELETE,
            &["data".to_string(), "reg5".to_string()],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::DELETE,
            &[
                "data".to_string(),
                "reg6".to_string(),
                "obj6".to_string(),
                "con6".to_string(),
            ],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
        let (_, _, code) = free_form(
            router.clone(),
            http::Method::DELETE,
            &[
                "data".to_string(),
                "reg7".to_string(),
                "unknown".to_string(),
            ],
            None,
        )
        .await;
        assert_eq!(code, StatusCode::NOT_FOUND);
    }

    #[tokio::test]
    async fn test_data_store_repository_separation() {
        let shared_state = SharedState::default();
        let router = create_router(&shared_state);
        let payload1 = "other".to_string();
        let payload2 = "other".to_string();
        let (_, oid1, code1) = free_form(
            router.clone(),
            http::Method::PUT,
            &["rep1".to_string()],
            Some(payload1),
        )
        .await;
        assert_eq!(code1, StatusCode::OK);
        let (_, oid2, code2) = free_form(
            router.clone(),
            http::Method::PUT,
            &["rep2".to_string()],
            Some(payload2),
        )
        .await;
        assert_eq!(code2, StatusCode::OK);
        let (_, _, code3) = free_form(
            router.clone(),
            http::Method::DELETE,
            &["rep1".to_string(), oid1],
            None,
        )
        .await;
        assert_eq!(code3, StatusCode::OK);
        let (body4, _, code4) = free_form(
            router.clone(),
            http::Method::GET,
            &["rep2".to_string(), oid2],
            None,
        )
        .await;
        assert_eq!(code4, StatusCode::OK);
        assert_eq!(body4, "other".to_string());
    }
}
