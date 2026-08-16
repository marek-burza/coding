# Topological sorting

## 1. Topological order

Write a function to return a valid sequential execution order when given a list of tasks with dependencies. Validity is defined as: tasks must appears only after all their dependencies.

Given inputs must work with the function.

Linear chain:

- Given Input: `[("a", []), ("b", ["a"]), ("c", ["b"]), ("d", ["c"])]`
- Expected output: `["a", "b", "c", "d"]`

Branching graph:

- Given Input: `[("d", ["b", "c"]), ("b", ["a"]), ("c", ["a"]), ("a", [])]`
- Expected output: `["a", "b", "c", "d"]`           # input is not pre-sorted; "b" and "c" interchangeable

Disjoint graph:

- Given Input: `[("a", []), ("b", []), ("c", ["a"]), ("d", ["b"])]`
- Expected output: `["a", "b", "c", "d"]`           # "a" and "b" interchangeable

Diamond graph:

- Given Input: `[("a", []), ("b", ["a"]), ("c", ["a"]), ("d", ["b", "c"]), ("e", ["d"])]`
- Expected output: `["a", "b", "c", "d", "e"]`

## 2. Cycle detection and bad input

Extend the function to handle invalid inputs. How you surface errors is your call.

Valid graph:

- Given Input: `[("c", ["b"]), ("b", ["a"]), ("a", [])]`
- Expected output: `["a", "b", "c"]`                # input is not pre-sorted

Simple cycle:

- Given Input: `[("a", ["b"]), ("b", ["a"])]`
- Expected output: error - cycle {a, b}

Self-loop:

- Given Input: `[("a", []), ("b", ["b"]), ("c", ["a"])]`
- Expected output: error - self-loop on "b"

## 3. Maximum parallelism

Same input format as 2. Every task should run in the earliest slot it can. Tasks that can share a slot are packed into a tuple.

Linear:

- Given Input: `[("a", []), ("b", ["a"]), ("c", ["b"])]`
- Expected output: `["a", "b", "c"]`

Diamond:

- Given Input: `[("a", []), ("b", ["a"]), ("c", ["a"]), ("d", ["b", "c"])]`
- Expected output: `["a", ("b", "c"), "d"]`

Wide:

- Given Input: `[("a", []), ("b", []), ("c", []), ("d", ["a", "b", "c"])]`
- Expected output: `[("a", "b", "c"), "d"]`
