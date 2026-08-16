# Banking API - Objective

Your assignment is to build an internal API for a fake financial institution using Python and any framework except Django and Litestar.

## Brief

While modern banks have evolved to serve a plethora of functions, at their core, banks must provide certain basic features. Today, your task is to build the basic HTTP API for one of those banks. Imagine you are designing a backend API for bank employees. It could ultimately be consumed by multiple frontends (web, iOS, Android etc).

## Important
We do not expect you to work more than 4 hours on this case challenge and we acknowledge not everything can be implemented in a production ready manner. You can choose where to use a mock/stub vs. where you focus on the implementation. If you have to make compromises, please list in your documentation what needs to be done to make it production ready. 

## Use of AI tools
You are encouraged to use AI tools such as Claude, ChatGPT, or similar assistants as part of your solution process. We consider effective use of AI a core skill in modern software development. To give us insight into your approach, please submit the full conversation log(s) alongside your solution - we are interested in how you prompt, iterate, and critically evaluate AI output, not just the end result.

## Tasks

- Implement assignment using:
  - Language: Python
  - Framework: any framework except Django and Litestar
- There should be API routes that allow them to:
  - Create a new bank account for a customer, with an initial deposit amount. A single customer may have multiple bank accounts.
  - Transfer amounts between any two accounts, including those owned by
    different customers.
  - Retrieve balances for a given account.
  - Retrieve transfer history for a given account.
- Write tests for your business logic.
- Add documentation what needs to be done before putting your solution into production

Feel free to pre-populate your customers with the following:

```json
[
  {
    "id": 1,
    "name": "John Doe"
  },
  {
    "id": 2,
    "name": "Jack Smith"
  },
  {
    "id": 3,
    "name": "Jane Taylor"
  },
  {
    "id": 4,
    "name": "Jade Wilson"
  }
]
```

You are expected to design any other required models and routes for your API.

## Evaluation Criteria

- Python best practices
- Completeness: did you complete the features?
- Correctness: does the functionality act in sensible, thought-out ways?
- Maintainability: is it written in a clean, maintainable way?
- Production readiness: is there a clear path towards taking the prototype into production?
- Testing:
  - Is the system adequately tested?
  - Can the application be installed and tested out of the box by the reviewer?
- Documentation:
  - is the API well-documented?
  - are design choices well-explained?

## Outro

Please organize, design, test and document your code as if it were going into production.

All the best and happy coding!
