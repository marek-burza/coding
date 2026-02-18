package datastorageapi

import (
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"
	"sync"
	"time"
)

type replyPut struct {
	OID  string `json:"oid"`
	Size int64  `json:"size"`
}

/*
This is a rather naive function for figuring out the
repository and objectID from the URL.
*/
func urlMatch(url string) (repository string, objectID string, count int) {
	fragments := strings.Split(url, "/")
	repository = fragments[2]
	objectID = ""
	if len(fragments) > 3 {
		objectID = fragments[3]
	}
	return repository, objectID, len(fragments)
}

/*
Download an Object

GET /data/{repository}/{objectID}
Response

Status: 200 OK
{object data}
Objects that are not on the server will return a 404 Not Found.
*/
func getData(w http.ResponseWriter, _ *http.Request, _ string, objectID string, count int, storage map[string][]byte) {
	if count != 4 {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	if buffer, found := storage[objectID]; found {
		w.WriteHeader(http.StatusOK)
		w.Write(buffer) //nolint:errcheck,gosec
		// count, fail := w.Write(buffer)
		// if fail != nil || count != len(buffer) {
		//     w.WriteHeader(http.StatusInternalServerError)
		// }
	} else {
		w.WriteHeader(http.StatusNotFound)
	}
}

/*
Upload an Object

PUT /data/{repository}
{object data}
Response

Status: 200 OK
'{"oid": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}'
*/
func putData(w http.ResponseWriter, req *http.Request, repository string, objectID string, count int, storage map[string][]byte) { // nolint:staticcheck
	if count != 3 {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	mutex.Lock() // Note: Added after submission
	buffer, _ := io.ReadAll(req.Body)
	// buffer, fail := io.ReadAll(req.Body)
	// if fail != nil {
	// 	   w.WriteHeader(http.StatusInternalServerError)
	// 	   mutex.Unlock()
	// 	   return
	// }
	identifier := []byte(repository)
	identifier = append(identifier, buffer...)
	hashed := sha256.Sum256(identifier)
	objectID = hex.EncodeToString(hashed[:]) // nolint:staticcheck
	storage[objectID] = buffer
	reply := replyPut{objectID, int64(len(buffer))}
	buffer, _ = json.Marshal(reply)
	// buffer, fail = json.Marshal(reply)
	// if fail != nil {
	//     delete(storage, objectID)
	//     w.WriteHeader(http.StatusInternalServerError)
	//     mutex.Unlock()
	//     return
	// }
	w.WriteHeader(http.StatusCreated)
	w.Write(buffer) //nolint:errcheck,gosec
	// count, fail = w.Write(buffer)
	// if fail != nil || count != len(buffer) {
	//     delete(storage, objectID)
	//     w.WriteHeader(http.StatusInternalServerError)
	// }
	mutex.Unlock() // Note: Added after submission
}

/*
Remove an Object

DELETE /data/{repository}/{objectID}
Response

Status: 200 OK
Objects that are not on the server will return a 404 Not Found.
*/
func deleteData(w http.ResponseWriter, _ *http.Request, _ string, objectID string, count int, storage map[string][]byte) {
	if count != 4 {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	mutex.Lock() // Note: Added after submission
	if _, found := storage[objectID]; found {
		delete(storage, objectID)
		w.WriteHeader(http.StatusOK)
	} else {
		w.WriteHeader(http.StatusNotFound)
	}
	mutex.Unlock() // Note: Added after submission
}

var mutex sync.Mutex // Note: Added after submission
var server *http.Server
var handled = false

func main() {
	// We'll store the data in memory in a map.
	storage := make(map[string][]byte)

	handler := func(w http.ResponseWriter, req *http.Request) {
		repository, objectID, count := urlMatch(req.URL.Path)
		w.Header().Set("Content-Type", "application/json")
		switch req.Method {
		case "GET":
			getData(w, req, repository, objectID, count, storage)
		case "PUT":
			putData(w, req, repository, objectID, count, storage)
		case "DELETE":
			deleteData(w, req, repository, objectID, count, storage)
		default:
			w.WriteHeader(http.StatusMethodNotAllowed)
		}
		fmt.Println(req.Method + " repository: " + repository + " objectID: " + objectID)
	}

	if !handled {
		http.HandleFunc("/data/", handler)
		handled = true
	}
	server = &http.Server{
		Addr:              ":8282",
		ReadHeaderTimeout: 10 * time.Second,
	}
	server.ListenAndServe() //nolint:errcheck,gosec
	// if err := server.ListenAndServe(); err != http.ErrServerClosed {
	//     log.Fatal(err)
	// }
}
