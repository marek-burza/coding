# Coding Exercise: Data Storage API

Implement a small HTTP service to store objects organized by repository.
Clients of this service should be able to GET, PUT, and DELETE objects.

## Expectations

We ask that you spend no more than 2 hours on this exercise. We value your time and don't want to set unreasonable expectations on how long you should work on this exercise.

We ask you complete this exercise so you have an opportunity to build a service in your own time rather than an in-person interview, coding on a whiteboard.

## General Requirements

- The service should de-duplicate data objects by repository.
- The service should listen on port `8282`.
- The included tests should pass and not be modified. Adding additional tests is encouraged.
- The service must implement the API as described below.
- The data can be persisted in memory, on disk, or wherever you like.
- Do not include any extra dependencies. Anything in the Go standard library is fine.

## Suggestions

- Your code will be read by humans, so organize it sensibly.
- Use this repository to store your work. Committing just the final solution is _OK_ but we'd love to see your incremental progress. We suggest taking a look at [GitHub flow](https://guides.github.com/introduction/flow/) to structure your commits.
- [Submit a pull request](https://help.github.com/articles/creating-a-pull-request/) once you are happy with your work.
- Treat this pull request as if you’re at work submitting it to your colleagues, or to an open source project. The body of the pull request can be used to describe your reasoning and any assumptions, limitations or trade-offs in your implementation, or anything you're really proud of in your submission 😄.
- Remember that this is a web application and concurrent requests could come in. If you have time, this is a good problem to address.

## API

### Upload an Object

```default
PUT /data/{repository}
```

#### Response (Upload an Object)

```default
Status: 201 Created
{
  "oid": "1845f5a412dbdfacf95193f296dd0f5b2a16920da5a7ffa4c5832f223b03de97",
  "size": 1234
}
```

### Download an Object

```default
GET /data/{repository}/{objectID}
```

#### Response (Download an Object)

```default
Status: 200 OK
{object data}
```

Objects that are not on the server will return a `404 Not Found`.

### Delete an Object

```default
DELETE /data/{repository}/{objectID}
```

#### Response

```default
Status: 200 OK
```

## Getting started and Testing

In `testapp.go` you'll find a naive first draft of the answer to the exercise written for you. Please improve this draft so that it passes the test written in `coding_test.go`. You can test this by running:

```default
go build -o testapp
go test -count=1
```

Or simply:

```default
make
```

Once you have a good version of `testapp.go` please submit that file in a pull request.
Behind the scenes we add the following GitHub Actions file to your code:

```default
name: Go
on: [push]
jobs:

  build:
    name: Build
    runs-on: ubuntu-latest
    steps:

    - name: Set up Go 1.16
      uses: actions/setup-go@v1
      with:
        go-version: 1.16
      id: go

    - name: Check out code into the Go module directory
      uses: actions/checkout@v1

    - name: Build
      run: go build -o testapp

    - name: Test
      run: go test -count=1

    - name: Lint
      run: golint ./...
```
