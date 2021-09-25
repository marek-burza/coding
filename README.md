# General Remarks

* Talk through your thinking process, flag uncertainty
* Be: fast, positive, transparent, fact-based, methodical, and express explicitly (to prevent hidden assumptions and getting stuck)
* If stuck: **remain calm**, check asumptions, **try more examples**, **simplify**, **ask**, avoid silence or filling it

----------

# Algorithm Design

1. Disambiguate / **Clarify by example** (✅ ❎)
  - Missing details? Special cases & their indication / handling?
  - Restrictions? Guarantees?
  - Types? Ranges? Sizes? Cardinality? How often? Unique / repetitions / empty / partially ordered?
  - Function signature & spec?
  - Beware of: assumptions, **"familiar" exercises**, **early optimization**; Start simple
2. Talk through the design process / List ideas
  - List alternatives telling their pros and cons (e.g. time/space complexity)
  - Algorithm design tactics
    - Write (or draw) examples to identify a pattern
    - Simplify (relax constraints) then generalize
    - Base case & build up (induction, scale), dyn. prog.
    - Match to other similar problem / data struct. (for example: **heap**, **graph**, stack, etc.)
    - (Consider greedy alg., backtracking, iterating the smaller param.)
  - Pick one achievable in an interview (**solution likely to be simple enough**)
3. Code
  - Pick a data structure
  - Modularize (for clarity)
  - Test input (as a remark)
  - Beware of < vs <=, +1 vs +0, overflows
  - Code must compile - obey syntax, but okay to ask if don't remember name or behavior
4. Test - describe how would you test, run through a simple case
  - Normal case
  - Non-trivial, all execution paths
  - Edge cases, no solution
  - (Other: invalid input, randomized tests, load testing)
5. Keep improving - can we do better?

* [Practice algorithm design challenges](algorithms) [![codecov](https://codecov.io/gh/altermarkive/training/branch/master/graph/badge.svg)](https://codecov.io/gh/altermarkive/training)
  - [LeetCode](https://leetcode.com/) [solutions](algorithms/code/leetcode)
  - [Codility](https://codility.com/) [solutions](algorithms/code/codility)
  - [Geeks for Geeks](https://www.geeksforgeeks.org/) [solutions](algorithms/code/geeksforgeeks)
  - [HackerRank](https://www.hackerrank.com/) [solutions](algorithms/code/hackerrank)

* [Flashcards](https://github.com/altermarkive/training/releases)

----------

# System Design:

0. Communicate, steer (be the tech lead), get feedback!
1. Explore and understand
  - **Disambiguate**, gather requirements - ask clarifying questions (missing details or restrictions) - beware of assumptions!
  - Agree on **scope** / **use cases**
    (First a minimum viable product then explore other functions: API vs UI? Customizable? Monetization? Analytics? Scale / numbers?)
2. Design - break down the system into parts
  - Start with basic, **abstract** design (e.g. key-value store, web server); Prioritize
3. Scale-up / distribute
  - **Say what can fail / overflow**, **trade-offs** (CAP theorem - consistency, availability, partitioning),
  - Say how to distribute and how to fix whats broken by distributing
  - Show specific alternatives / solution space
  - **Ballpark estimates** (order of magnitude, as of 2015) - might be a part of point 1
    - L1 hit: 0.5ns
    - L2 hit: 7ns
    - Branch mispredict: 5ns
    - SRAM: 10ns
    - DRAM/MUTEX: 100ns
    - System call (overhead): 400ns
    - Roundtrip in datacenter: 0.5ms
    - Process context switch: 3ms
    - Disk seek: 5-10ms (!)
    - Fork: 70ms (statically linked), 160ms (dynamically)
    - HD transfer rate 1Gbit/s
    - 500M tweets per day
    - 300M monthly active Twitter users
    - 700M total Twitter users
    - 40k/s Google searches
    - 10T Google indexed pages

* Review [System Design Process](https://www.hiredintech.com/system-design/the-system-design-process/) on Hired in Tech
* [Watch the lecture on Scalability Harvard Web Development](https://youtu.be/-W9F__D3oY4)
* [Review an example - Restaurant Waiting Time](http://altermarkive.github.io/interview-training/system-design/restaurant-waiting-time.html)
* [Jackson Gabbard - Intro to Architecture and Systems Design - Interviews](https://youtu.be/ZgdS0EUmn70)
* Design Principles - [SOLID](https://en.wikipedia.org/wiki/SOLID), Low Coupling & High Cohesion, [Heroku's 12 Factors](https://12factor.net/), [Richardson Maturity Model](https://en.wikipedia.org/wiki/Richardson_Maturity_Model), [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself), Semantic Versioning (and locking versions), Immutable Object/Server/etc., Idempotent Operations, Minimal Privileges & Isolation, Encryption in Transit & at Rest
* Architectural Safety Measures: [Circuit-breakers](https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern), [Correlation IDs](https://dzone.com/articles/correlation-id-for-logging-in-microservices), Timeouts, System Bulkheads
* Embracing System Failure: [OWASP Top Ten](https://owasp.org/www-project-top-ten/), [Chaos Engineering](https://en.wikipedia.org/wiki/Chaos_engineering) & [Antifragile Engineering](https://en.wikipedia.org/wiki/Antifragile)
* [Microservices](https://www.google.com/search?q=awesome+microservices)

----------

# Object Oriented Design

1. What do we want to do (with the objects)?
2. What are the core objects (we operate on)?
3. Anything missing (look closer at details/aspects)?
4. Go deeper - how will the methods work?

----------

# Extra materials

* Algorithm Design:
  - [Review the Algorithm Design Process at Hired in Tech](https://www.hiredintech.com/algorithm-design/the-algorithm-design-canvas/)
  - [Actual interview questions on CareerCup](https://www.careercup.com/user?id=5095734581919744)
  - [Cracking the Coding Interview](https://www.google.nl/search?q=cracking+the+coding+interview+filetype:pdf)
  - [Programming Interviews Exposed](https://www.google.nl/search?q=programming+interviews+exposed+filetype:pdf)
* System Design:
  - [High Scalability Blog](https://highscalability.com/)
