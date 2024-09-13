use axum::{
    body::Bytes,
    extract::{Path, State},
    http::StatusCode,
    routing::{delete, get, put},
    Json, Router,
};
use serde::Serialize;
use sha2::{Digest, Sha256};
use std::{
    collections::HashMap,
    sync::{Arc, RwLock},
};
use tokio::net::TcpListener;
use tracing_subscriber;

#[derive(Serialize)]
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
    Path(repository): Path<String>,
    Path(object_id): Path<String>,
) -> (StatusCode, Bytes) {
    assert!(!repository.is_empty());
    let state = state.read().unwrap();
    let result = state.storage.get(&object_id);
    if let Some(bytes) = result {
        return (StatusCode::OK, bytes.clone());
    }
    (StatusCode::NOT_FOUND, Bytes::new())
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
    bytes: Bytes,
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
    Path(repository): Path<String>,
    Path(object_id): Path<String>,
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
    storage: HashMap<String, Bytes>,
}

pub type SharedState = Arc<RwLock<AppState>>;

#[tokio::main]
pub async fn main() {
    // We'll store the data in memory in a map.
    let shared_state = SharedState::default();
    tracing_subscriber::fmt::init();
    let router = Router::new()
        .route("/data/:repository/:object_id", get(get_data))
        .route("/data/:repository", put(put_data))
        .route("/data/:repository/:object_id", delete(delete_data))
        .with_state(Arc::clone(&shared_state));
    let listener = TcpListener::bind("0.0.0.0:8282").await.unwrap();
    axum::serve(listener, router).await.unwrap();
}
