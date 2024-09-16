# Training

## General Remarks

- Talk through your thinking process, flag uncertainty
- Be: fast, positive/enthusiastic/passionate, transparent, fact-based, methodical, and express explicitly (to prevent hidden assumptions and getting stuck)
- If stuck: **remain calm**, check asumptions, **try more examples**, **simplify**, **ask**, avoid silence or filling it

---

## Algorithm Design

### 1. Disambiguate / **Clarify by example** (✅ ❎)

- Missing details? Special cases & their indication / handling?
- Restrictions? Guarantees?
- Types? Ranges? Sizes? Cardinality? How often? Unique / repetitions / empty / partially ordered?
- Can I modify the input data structure?
- Function signature & spec?
- Accounted for entirety of interviewer's description?
- Beware of: assumptions, **"familiar" exercises**, **early optimization**; Start simple

### 2. Talk through the design process / List ideas

- List alternatives telling their pros and cons (e.g. time/space complexity); is there a time vs. space trade-off?
- Tactic: Write (or draw) examples to identify a pattern
- Tactic: Simplify (relax constraints) then generalize
- Tactic: Base case & build up (induction, scale), dyn. prog.
- Tactic: Match to other similar problem / data struct. (for example: **heap**, **graph**, stack, etc.)
- Tactic: (Consider greedy alg., backtracking, iterating the smaller param.)
- Pick one achievable in an interview (**solution likely to be simple enough**); indicate complexity

### 3. Code

- Pick a data structure
- Modularize (break-up code into distinct parts for clarity)
- Test input (as a remark)
- Beware of < vs <=, +1 vs +0, null checks, overflows
- Code must compile - obey syntax, but okay to ask if don't remember name or behavior

### 4. Test

- Describe how would you test, run through a simple case
- Normal case
- Non-trivial, corner cases
- Full coverage time permitting
- (Other: invalid input, randomized tests, load testing)
- Point out options for refactoring (?)

### 5. Keep improving - can we do better?

### [Practice algorithm design challenges](algorithms)

![Status Python](https://github.com/altermarkive/training/workflows/Python/badge.svg)
![Status Go](https://github.com/altermarkive/training/workflows/Go/badge.svg)
![Status Java](https://github.com/altermarkive/training/workflows/Java/badge.svg)
![Status Rust](https://github.com/altermarkive/training/workflows/Rust/badge.svg)
[![Coverage](https://codecov.io/gh/altermarkive/training/branch/master/graph/badge.svg)](https://codecov.io/gh/altermarkive/training)

- [HackerRank](https://www.hackerrank.com/) - [my solutions](algorithms/code/hackerrank)
- [LeetCode](https://leetcode.com/) - [my solutions](algorithms/code/leetcode)
- [Codility](https://codility.com/) - [my solutions](algorithms/code/codility)
- [Geeks for Geeks](https://www.geeksforgeeks.org/) - [my solutions](algorithms/code/geeksforgeeks)
- [Codeforces](https://codeforces.com/)
- [InterviewBit](https://www.interviewbit.com/) (consider their mock interviews)
- [Kattis](https://open.kattis.com/)
- [ACM-ICPC questions](https://icpc.baylor.edu/worldfinals/problems)
- [Codejam questions](https://code.google.com/codejam/past-contests)

### Additional Materials

- [Patterns for Coding Questions](https://www.designgurus.io/course/grokking-the-coding-interview) ❗
- [LeetCode - Top Interview Questions](https://leetcode.com/explore/featured/card/top-interview-questions-easy/) ❗
- [Algorithm Design Process at Hired in Tech](https://www.hiredintech.com/algorithm-design/the-algorithm-design-canvas/)
- [Actual interview questions on CareerCup](https://www.careercup.com/user?id=5095734581919744)
- [Cracking the Coding Interview](https://www.google.nl/search?q=cracking+the+coding+interview+filetype:pdf)
- [Programming Interviews Exposed](https://www.google.nl/search?q=programming+interviews+exposed+filetype:pdf)
- [Google: Prepare for an Engineering Interview](https://youtu.be/ko-KkSmp-Lk)
- [Interview tips from Google Software Engineers](https://youtu.be/XOtrOSatBoY)
- [Coding Interview University](https://github.com/jwasham/coding-interview-university)

### [Flashcards](http://altermarkive.github.io/training/algorithms/algorithms.html) ([print-to-PDF](http://altermarkive.github.io/training/algorithms/algorithms.html?print-pdf))

---

## System Design

### 0. Be the tech lead

- Iterate **quickly** on the design while prioritizing what to work on next
- Communicate trade-offs, decissions
- Have a dialog with your stakeholder (interviewer)
- Dare to ask for help if necessary (but own it with confidence)

### 1. Explore and understand

- Go from an ill-defined goal to a formulated statement of what to build (and what is out of scope)
- **Disambiguate**, gather requirements - ask clarifying questions (missing details or restrictions) - beware of assumptions!
- Agree on **scope** / **use cases**
  (First a minimum viable product then explore other functions - embodiment, features, feature aspects/specifics, scale/numbers: Mobile vs. web? API vs UI? Customizable? Monetization? Descending or random order? Analytics? Scale / numbers?)

### 2. Design - break down the system into simple parts

- Start with basic, **abstract** design (e.g. key-value store, web server)
- Delineate what is where (cloud, user's equipment)
- Describe how the user interacts with the system and what is the sequence (of inner system interactions)
- Prioritize the next steps

### 3. Scale-up / distribute

- **Say what can fail / overflow / bottleneck**, **trade-offs** (CAP theorem - consistency, availability, partitioning),
- Say how to distribute and how to fix whats broken by distributing
- Show specific alternatives / solution space
- **Ballpark estimates**
- If you do not know something - be upfront about it but do offer a direction
- If you are particularly familiar with an aspect - do mention it

### 4. Iterate

- Is anything missing (look closer at details/aspects)?
- How would it change the behavior of users?

### Additional materials

- [Alex Yu - System Design Interview](https://www.amazon.com/dp/B08B3FWYBX/ref=cm_sw_em_r_mt_dp_X3C1WZV5Q0VX0Q0HX7CX) ❗
- [NeetCode: System Design (including 20 system design concepts in 10 Minutes)](https://www.youtube.com/playlist?list=PLot-Xpze53le35rQuIbRET3YwEtrcJfdt) ❗
- [Learn System Design in a Hurry](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction) ❗
- [Jackson Gabbard - Intro to Architecture and Systems Design - Interviews](https://youtu.be/ZgdS0EUmn70)
- [System Design Process](https://www.hiredintech.com/system-design/the-system-design-process/) on Hired in Tech
- Patrick Halina - [Systems Design Interview Guide](http://patrickhalina.com/posts/systems-design-interview-guide) & [ML Systems Design Interview Guide](http://patrickhalina.com/posts/ml-systems-design-interview-guide/)
- [System Design Primer](https://github.com/donnemartin/system-design-primer)
- [Build Your Own X](https://github.com/danistefanovic/build-your-own-x)
- Design principles, patterns, best practices - [SOLID](https://en.wikipedia.org/wiki/SOLID),
  Low Coupling & High Cohesion, [Heroku's 12 Factors](https://12factor.net/),
  [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design),
  REST & [Richardson Maturity Model](https://en.wikipedia.org/wiki/Richardson_Maturity_Model) & [CQRS](https://en.wikipedia.org/wiki/Command%E2%80%93query_separation#Command_query_responsibility_segregation),
  [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) too much,
  Semantic Versioning (and locking versions), Immutable Object/Server/etc.,
  [Idempotent Operations](https://microservices.io/patterns/communication-style/idempotent-consumer.html),
  [event sourcing](https://microservices.io/patterns/data/event-sourcing.html), Minimal Privileges & Isolation,
  Encryption in Transit & at Rest, Service discovery and Service Registry (Zookeeper, etcd, Consul),
  ACID (atomicity, consistency, isolation, durability)
- Architectural Safety Measures: [Circuit-breakers](https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern) & back-off timeouts, [Correlation IDs](https://dzone.com/articles/correlation-id-for-logging-in-microservices) & healthchecks & monitoring & logging & alerts, System Bulkheads
- Embracing System Failure: [OWASP Top Ten](https://owasp.org/www-project-top-ten/), [Chaos Engineering](https://en.wikipedia.org/wiki/Chaos_engineering) & [Antifragile Engineering](https://en.wikipedia.org/wiki/Antifragile)
- [Microservices](https://www.google.com/search?q=awesome+microservices): [best practices](https://microservices.io/) & [antipatterns / pitfalls](https://www.oreilly.com/content/microservices-antipatterns-and-pitfalls/)

---

## Other Materials

- [Tech Interview Handbook](https://www.techinterviewhandbook.org/) ([best practices](https://www.techinterviewhandbook.org/coding-interview-cheatsheet/)) ❗
- [Gergely Orosz - Preparing for the Systems Design and Coding Interview](https://blog.pragmaticengineer.com/preparing-for-the-systems-design-and-coding-interviews/)
- [LeetCode - leap.ai - Rock the Behavioral Interview](https://leetcode.com/explore/interview/card/leapai/) ❗
- Mock interviews: [interviewing.io](https://interviewing.io/), [Pramp](https://www.pramp.com/), [Meet a Pro](https://www.meetapro.com/)
