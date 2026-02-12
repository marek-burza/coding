package datastorageapi

import (
	"context"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"
	"testing"
	"time"
)

func TestDataStore(t *testing.T) {
	s, err := launchServer()
	if err != nil {
		t.Fatalf("error: %s", err)
	}
	t.Cleanup(func() {
		_ = s.Shutdown()
	})

	testPut(t)
	testGet(t)
	testDelete(t)
}

func testPut(t *testing.T) {
	payload1 := strings.NewReader("something")
	res1 := putBlob(t, payload1)

	payload2 := strings.NewReader("other")
	res2 := putBlob(t, payload2)

	if res1.OID == res2.OID {
		t.Errorf("expected to have unique oid")
	}

	if res1.Size != payload1.Size() {
		t.Errorf("expected a size of %d, got %d", payload1.Size(), res1.Size)
	}

	if res2.Size != payload2.Size() {
		t.Errorf("expected a size of %d, got %d", payload2.Size(), res2.Size)
	}
}

func testGet(t *testing.T) {
	content1 := "something"
	payload1 := strings.NewReader(content1)
	res1 := putBlob(t, payload1)

	content2 := "other"
	payload2 := strings.NewReader(content2)
	res2 := putBlob(t, payload2)

	body, status := getBlob(t, res1.OID)
	if status != http.StatusOK {
		t.Errorf("expected HTTP status of %d, got %d", http.StatusOK, status)
	}
	if body != content1 {
		t.Errorf("expected content of %s, got %s", content1, body)
	}

	body, status = getBlob(t, res2.OID)
	if status != http.StatusOK {
		t.Errorf("expected HTTP status of %d, got %d", http.StatusOK, status)
	}
	if body != content2 {
		t.Errorf("expected content of %s, got %s", content2, body)
	}
}

func testDelete(t *testing.T) {
	content := "something"
	payload := strings.NewReader(content)
	res := putBlob(t, payload)

	status := deleteBlob(t, res.OID)
	if status != http.StatusOK {
		t.Errorf("expected HTTP status of %d, got %d", http.StatusOK, status)
	}

	_, status = getBlob(t, res.OID)
	if status != http.StatusNotFound {
		t.Errorf("expected HTTP status of %d, got %d", http.StatusNotFound, status)
	}

	status = deleteBlob(t, res.OID)
	if status != http.StatusNotFound {
		t.Errorf("expected HTTP status of %d, got %d", http.StatusNotFound, status)
	}
}

type _response struct {
	OID  string `json:"oid"`
	Size int64  `json:"size"`
}

type testServer struct {
}

func (s *testServer) Shutdown() error {
	if err := server.Shutdown(context.TODO()); err != nil {
		return err
	}
	return nil
}

// closes an io.Closer and ignores the returned error
func closeIgnore(closer io.Closer) {
	_ = closer.Close()
}

func launchServer() (*testServer, error) {
	go main()

	time.Sleep(time.Millisecond * 400) // Wait until we boot up

	return &testServer{}, nil
}

func getBlob(t *testing.T, oid string) (string, int) {
	objURL := fmt.Sprintf("http://localhost:8282/data/codingtest/%s", oid)
	req, _ := http.NewRequest("GET", objURL, nil)
	res, err := http.DefaultClient.Do(req)
	if err != nil {
		t.Fatalf("error making GET request: %s", err)
	}
	defer closeIgnore(res.Body)

	body, err := io.ReadAll(res.Body)
	if err != nil {
		t.Fatalf("error reading GET response: %s", err)
	}

	return string(body), res.StatusCode
}

func putBlob(t *testing.T, payload io.Reader) *_response {
	req, _ := http.NewRequest("PUT", "http://localhost:8282/data/codingtest", payload)
	res, err := http.DefaultClient.Do(req)
	if err != nil {
		t.Fatalf("error making PUT request: %s", err)
	}
	defer closeIgnore(res.Body)

	if res.StatusCode != http.StatusCreated {
		t.Errorf("expected response code 201, got %d", res.StatusCode)
	}

	contentType := res.Header.Get("Content-Type")
	if contentType != "application/json" {
		t.Errorf("expected Content-Type 'application/json', got %s", contentType)
	}

	var data _response
	if err := json.NewDecoder(res.Body).Decode(&data); err != nil {
		t.Fatalf("error decoding response: %s", err)
	}

	return &data
}

func deleteBlob(t *testing.T, oid string) int {
	objURL := fmt.Sprintf("http://localhost:8282/data/codingtest/%s", oid)
	req, _ := http.NewRequest("DELETE", objURL, nil)
	res, err := http.DefaultClient.Do(req)
	if err != nil {
		t.Fatalf("error making DELETE request: %s", err)
	}
	res.Body.Close() // ignore error

	return res.StatusCode
}

func TestDataStoreExtended(t *testing.T) {
	s, err := launchServer()
	if err != nil {
		t.Fatalf("error: %s", err)
	}
	t.Cleanup(func() {
		_ = s.Shutdown()
	})

	if _, code, _ := freeForm(t, "POST", []string{"data", "reg1", "content1"}, nil); code != http.StatusMethodNotAllowed {
		t.Fatalf("error making POST request")
	}
	if _, code, _ := freeForm(t, "GET", []string{"data", "reg2"}, nil); code != http.StatusBadRequest {
		t.Fatalf("error making GET request with too few params")
	}
	if _, code, _ := freeForm(t, "GET", []string{"data", "reg3", "obj3", "con3"}, nil); code != http.StatusBadRequest {
		t.Fatalf("error making GET request with too many params")
	}
	if _, code, _ := freeForm(t, "PUT", []string{"data", "reg4", "obj4"}, nil); code != http.StatusBadRequest {
		t.Fatalf("error making PUT request with too many params")
	}
	if _, code, _ := freeForm(t, "DELETE", []string{"data", "reg5"}, nil); code != http.StatusBadRequest {
		t.Fatalf("error making DELETE request with too few params")
	}
	if _, code, _ := freeForm(t, "DELETE", []string{"data", "reg6", "obj6", "con6"}, nil); code != http.StatusBadRequest {
		t.Fatalf("error making DELETE request with too many params")
	}
	if _, code, _ := freeForm(t, "YKWIM", []string{"data", "reg6", "obj6", "con6"}, nil); code != http.StatusMethodNotAllowed { // Note: Added after submission
		t.Fatalf("error making YKWIM request with too many params")
	}
	if _, code, _ := freeForm(t, "DELETE", []string{"data", "reg7", "unknown"}, nil); code != http.StatusNotFound { // Note: Added after submission
		t.Fatalf("error making DELETE with not found params")
	}

}

func TestDataStoreRepositorySeparation(t *testing.T) {
	s, err := launchServer()
	if err != nil {
		t.Fatalf("error: %s", err)
	}
	t.Cleanup(func() {
		_ = s.Shutdown()
	})

	content1 := "other"
	payload1 := strings.NewReader(content1)
	content2 := "other"
	payload2 := strings.NewReader(content2)

	_, code1, rep1 := freeForm(t, "PUT", []string{"data", "rep1"}, payload1)
	if code1 != http.StatusCreated && rep1 != nil {
		t.Fatalf("error making first put request")
	}
	_, code2, rep2 := freeForm(t, "PUT", []string{"data", "rep2"}, payload2)
	if code2 != http.StatusCreated && rep2 != nil {
		t.Fatalf("error making second put request")
	}
	_, code3, _ := freeForm(t, "DELETE", []string{"data", "rep1", rep1.OID}, nil)
	if code3 != http.StatusOK {
		t.Fatalf("error making delete request")
	}
	body5, code4, _ := freeForm(t, "GET", []string{"data", "rep2", rep2.OID}, nil)
	if code4 != http.StatusOK || body5 != "other" {
		t.Fatalf("error making get request")
	}
}

func freeForm(t *testing.T, method string, items []string, payload io.Reader) (string, int, *_response) {
	joined := strings.Join(items, "/")
	objURL := fmt.Sprintf("http://localhost:8282/%s", joined)
	req, _ := http.NewRequest(method, objURL, payload)
	res, err := http.DefaultClient.Do(req)
	if err != nil {
		t.Fatalf("error making %s request: %s", method, err)
	}
	defer closeIgnore(res.Body)

	body, err := io.ReadAll(res.Body)
	if err != nil {
		t.Fatalf("error reading %s response: %s", method, err)
	}

	contentType := res.Header.Get("Content-Type")
	if contentType != "application/json" {
		t.Errorf("expected Content-Type 'application/json', got %s", contentType)
	}

	var data _response
	if res.Body == nil {
		return string(body), res.StatusCode, nil
	}
	if err := json.Unmarshal(body, &data); err != nil {
		return string(body), res.StatusCode, nil
	}
	return string(body), res.StatusCode, &data
}
