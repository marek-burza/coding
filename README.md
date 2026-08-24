# Training Coding

## General Approach

### 0. Be the tech lead

- Iterate **fast** on the design while prioritizing what to work on next
- Talk through your thinking process explicitly/transparently - trade-offs, decisions, uncertainty (to prevent hidden assumptions and getting stuck)
- Be positive, fact-based, **remain calm**
- If stuck: check assumptions, **try different examples**, **simplify**, ["dare to be the idiot"](https://www.youtube.com/watch?v=BkLzo_oNVho), **ask for help** if necessary

### 1. Disambiguate and understand

- Gather requirements - go from an ill-defined goal to a formulated statement of what to build (and what is out of scope)
- Beware of: assumptions, **"familiar" exercises**, **early optimization**
- Start simple / MVP, then explore beyond
- Agree on **scope** / **use cases**

Example clarifying questions:

- Missing details? Special cases & their indication / handling?
- Restrictions? Guarantees?
- Types? Ranges? Sizes? Cardinality? How often? Unique / repetitions / empty / partially ordered?
- Can I modify the input data structure?
- Function signature & spec?
- Accounted for entirety of interviewer's description?
- Mobile vs. web? API vs UI? Customizable? Monetization? Descending or random order? Analytics? Scale / numbers?

### 2. Talk through the design process / List ideas

- List alternatives / solution space telling their pros and cons (e.g. time/space complexity); is there a time vs. space trade-off?
- Start with basic, **abstract** design (e.g. key-value store, single web server)

Tactics:

- Write (or draw) examples to identify a pattern
- Simplify (relax constraints) then generalize
- Base case & build up, dynamic programming (top-down - memoization, bottom-up - tabulation)
- Match to other similar problem / data struct. For example: **heap** (with _O(log(n))_ insert & delete, _O(n)_ search), **graph** (_X × E (adjacency list) vs. N² / 8 (adjacency matrix)_ - X is pointer size in bytes, and 8 is from boolean packing), stack, hashtable, etc.
- Pick solution achievable in an interview (**it is likely to be simple enough**)

### 3. Code / Implement

- Pick a data structure
- Modularize (break-up code into distinct parts for clarity). For system design: Delineate what is where (cloud, user's equipment)
- Validate input (as a remark)
- Beware of < vs <=, +1 vs +0, null checks, overflows
- Okay to ask if API unknown (name, arguments, etc.)
- Indicate complexity (_O(N × log(N))_ for sorting, _O(N²)_ worst case for quicksort); **Ballpark estimates**

### 4. Test

- Describe how would you test, run through a simple case
- Normal case
- Non-trivial, corner cases
- Full coverage time permitting
- System design: Describe how the user interacts with the system and what is the sequence (of inner system interactions)
- **Say what can fail / overflow / bottleneck**, **trade-offs** (CAP theorem - consistency, availability, partitioning),
- (Other: invalid input, randomized tests, load testing)

### 5. Iterate - Keep improving (can we do better?)

- Prioritize the next steps
- Is anything missing (look closer at details/aspects)?
- How would it change the behavior of users?

### [Practice algorithm design challenges](algorithms)

![Status Python](https://github.com/marek-burza/coding/workflows/Python/badge.svg)
![Status Go](https://github.com/marek-burza/coding/workflows/Go/badge.svg)
![Status Rust](https://github.com/marek-burza/coding/workflows/Rust/badge.svg)
[![Coverage](https://codecov.io/gh/marek-burza/coding/branch/main/graph/badge.svg)](https://codecov.io/gh/marek-burza/coding)

- [HackerRank](https://www.hackerrank.com/) - [my solutions](algorithms/code/hackerrank)
- [LeetCode](https://leetcode.com/) - [my solutions](algorithms/code/leetcode)
- [Codility](https://codility.com/) - [my solutions](algorithms/code/codility)
- [Geeks for Geeks](https://www.geeksforgeeks.org/) - [my solutions](algorithms/code/geeksforgeeks)
- [Codeforces](https://codeforces.com/)
- [InterviewBit](https://www.interviewbit.com/) (consider their mock interviews)
- [Kattis](https://open.kattis.com/)
- [ACM-ICPC questions](https://icpc.baylor.edu/worldfinals/problems)
- [Codejam questions](https://code.google.com/codejam/past-contests)

### Additional Coding Materials

- [Patterns for Coding Questions](https://www.designgurus.io/course/grokking-the-coding-interview) ❗
- [LeetCode - Top Interview Questions](https://leetcode.com/explore/featured/card/top-interview-questions-easy/) ❗
- [Algorithm Design Process at Hired in Tech](https://www.hiredintech.com/algorithm-design/the-algorithm-design-canvas/)
- [Actual interview questions on CareerCup](https://www.careercup.com/user?id=5095734581919744)
- [Cracking the Coding Interview](https://www.google.nl/search?q=cracking+the+coding+interview+filetype:pdf)
- [Programming Interviews Exposed](https://www.google.nl/search?q=programming+interviews+exposed+filetype:pdf)
- [Google: Prepare for an Engineering Interview](https://youtu.be/ko-KkSmp-Lk)
- [Interview tips from Google Software Engineers](https://youtu.be/XOtrOSatBoY)
- [Coding Interview University](https://github.com/jwasham/coding-interview-university)

### Additional System Design Materials

- [Alex Yu - System Design Interview](https://www.amazon.com/dp/B08B3FWYBX/ref=cm_sw_em_r_mt_dp_X3C1WZV5Q0VX0Q0HX7CX) ❗
- [NeetCode: System Design (including 20 system design concepts in 10 Minutes)](https://www.youtube.com/playlist?list=PLot-Xpze53le35rQuIbRET3YwEtrcJfdt) ❗
- [Learn System Design in a Hurry](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction) ❗
- [System Design Primer](https://github.com/donnemartin/system-design-primer) ❗
- [Jackson Gabbard - Intro to Architecture and Systems Design - Interviews](https://youtu.be/ZgdS0EUmn70)
- [System Design Process](https://www.hiredintech.com/system-design/the-system-design-process/) on Hired in Tech
- Patrick Halina - [Systems Design Interview Guide](http://patrickhalina.com/posts/systems-design-interview-guide) & [ML Systems Design Interview Guide](http://patrickhalina.com/posts/ml-systems-design-interview-guide/)
- [System Design Primer](https://github.com/donnemartin/system-design-primer)
- [Build Your Own X](https://github.com/danistefanovic/build-your-own-x)
- Design principles, patterns, best practices - [SOLID](https://en.wikipedia.org/wiki/SOLID)
  (single responsibility, open to extension closed to modification, liskov substitution interface accepting base should not need to worry about derivations, many specific interfaces better than one generic, depend on abstractions not concretions),
  Low Coupling & High Cohesion, [Heroku's 12 Factors](https://12factor.net/),
  [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design),
  REST & [Richardson Maturity Model](https://en.wikipedia.org/wiki/Richardson_Maturity_Model) & [CQRS](https://en.wikipedia.org/wiki/Command%E2%80%93query_separation#Command_query_responsibility_segregation),
  [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) too much,
  Semantic Versioning (and locking versions), Immutable Object/Server/etc.,
  [Idempotent Operations](https://microservices.io/patterns/communication-style/idempotent-consumer.html),
  [event sourcing](https://microservices.io/patterns/data/event-sourcing.html), Minimal Privileges & Isolation,
  Encryption in Transit & at Rest, Service discovery and Service Registry (Zookeeper, etcd, Consul),
  ACID (atomicity, consistency, isolation, durability), [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/#specification)
- Architectural Safety Measures: [Circuit-breakers](https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern) & back-off timeouts, [Correlation IDs](https://dzone.com/articles/correlation-id-for-logging-in-microservices) & healthchecks & monitoring & logging & alerts, System Bulkheads
- Embracing System Failure: [OWASP Top Ten](https://owasp.org/www-project-top-ten/), [Chaos Engineering](https://en.wikipedia.org/wiki/Chaos_engineering) & [Antifragile Engineering](https://en.wikipedia.org/wiki/Antifragile)
- [Microservices](https://www.google.com/search?q=awesome+microservices): [best practices](https://microservices.io/) & [antipatterns / pitfalls](https://www.oreilly.com/content/microservices-antipatterns-and-pitfalls/)

## Other Materials

- [Tech Interview Handbook](https://www.techinterviewhandbook.org/) ([best practices](https://www.techinterviewhandbook.org/coding-interview-cheatsheet/)) ❗
- [Gergely Orosz - Preparing for the Systems Design and Coding Interview](https://blog.pragmaticengineer.com/preparing-for-the-systems-design-and-coding-interviews/)
- Mock interviews: [interviewing.io](https://interviewing.io/), [Pramp](https://www.pramp.com/), [Meet a Pro](https://www.meetapro.com/)
- [Valve: Handbook for New Employees](https://cdn.cloudflare.steamstatic.com/apps/valve/Valve_NewEmployeeHandbook.pdf)
- [Go Advice](https://github.com/cristaloleg/go-advice)


## AI/ML Engineering Materials

- [ML Engineering Flashcards](machine-learning-engineering/machine-learning-engineering.md)
- [50 Must-Know PyTorch Interview Questions in 2026](https://github.com/Devinterview-io/pytorch-interview-questions)
- [Top 140 PyTorch Interview Questions and Answers](https://hackmd.io/@husseinsheikho/pytorch-interview)


## Behavioral Interview

- [LeetCode - leap.ai - Rock the Behavioral Interview](https://leetcode.com/explore/interview/card/leapai/) ❗
- [Tech Interview Handbook: The 30 most common Software Engineer behavioral interview questions](https://www.techinterviewhandbook.org/behavioral-interview-questions/)
- [Good answers](https://www.linkedin.com/posts/jithesh-anand_interviewpreparation-jobinterviewtips-careersuccess-activity-7213379334311448576-Z-HS) to behavioral questions
- [Top Facebook Behavioral Interview Questions - Facebook Jedi Interview Round](https://medium.com/the-interview-sage/top-facebook-behavioral-interview-questions-facebook-jedi-interview-round-a816b3d5a750)
