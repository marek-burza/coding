# General Remarks

* Be fast (but relaxed & creative)
* Talk through your thinking process, flag uncertainty
* Be positive, humble and express explicitly (to prevent hidden assumptions and getting stuck)

----------

# Algorithm Design

1. Identify class of problem / Disambiguate / List constraints / Clarify by example (✔ ✘)
  - Missing details? Special cases & their indication / handling?
  - Restrictions? Guarantees?
  - Types? Ranges? Sizes? Cardinality? How often? Unique / repetitions / empty / partially ordered?
  - Function signature & spec?
  - Beware of assumptions!
2. Talk through the design process / List ideas
  - List alternatives telling their pros and cons (e.g. time/space complexity)
  - Algorithm design tactics
    - Write (or draw) examples to identify a pattern
    - Simplify (relax constraints) then generalize
    - Base case & build up (induction, scale), dyn. prog.
    - Match to other similar problem / data struct. (for example: **heap**, **graph**, stack, etc.)
    - (Consider greedy alg., backtracking, iterating the smaller param.)
  - Pick one achievable in an interview (solution likely to be simple enough)
3. Code
  - Pick a data structure
  - Decompose if necessary (for clarity)
  - Test input (as a remark)
  - Beware of < vs <=, +1 vs +0, overflows
  - Code must compile - obey syntax, but okay to ask if don't remember name or behavior
4. Test - say how would you test, dry-run a simple one
  - Normal case
  - Non-trivial, all execution paths
  - Edge cases, no solution
  - (Invalid input)
  - (Randomized tests)
  - (Load testing)
5. Keep improving - can we do better?

* Review topics from the flashcards - [TeX](Flashcards.tex), [PDF](Flashcards.pdf)
* Practice easy and medium problems on LeetCode ([leetcode.com](https://leetcode.com/)) - I solved these so far [bit.ly/1OYxxXy](https://github.com/altermarkive/Training-LeetCode-OJ)

----------

# System Design:

0. Communicate, steer (be the tech lead), get feedback!
1. Explore and understand
  - **Disambiguate**, gather requirements - ask clarifying questions (missing details or restrictions) - beware of assumptions!
  - Agree on **scope** / **use cases**
    (First a minimum viable product then explore other functions: API vs UI? Customizable? Monetization? Analytics? Scale / numbers?)
2. Break down the system into parts
  - Start with basic, abstract design
  - Prioritize
3. Scale-up / distribute
  - Identify **bottlenecks**, **trade-offs** (CAP theorem - consistency, availability, partitioning), what can fail
  - **Ballpark estimates** (order of magnitude, as of 2015) - might be a part of point 1
    - Branch mispredict 5ns
    - L1 hit ~5 cycles (0.5ns)
    - L2 hit ~10 cycles (7ns)
    - L3 hit ~50 cycles (depends if same core)
    - SRAM 10ns
    - DRAM/MUTEX 100ns
    - System call (overhead) 400ns
    - Process context switch 3ms
    - DISK SEEK 5-10ms (!)
    - Fork: 70ms (statically linked), 160ms (dynamically)
    - HD TRANSFER RATE 1Gbit/s
    - 500M tweets per day
    - 300M monthly active Twitter users
    - 700M total Twitter users
    - 40k/s Google searches
    - 10T Google indexed pages
  - Show specific alternatives / solution space

* Review System Design Process on Hired in tech [bit.ly/1B6HOEc](http://www.hiredintech.com/system-design/the-system-design-process/) and [bit.ly/1Dgisc0](http://www.hiredintech.com/system-design/final-thoughts/)
* Watch the lecture on Scalability Harvard Web Development [youtu.be/-W9F__D3oY4](https://www.youtube.com/watch?v=-W9F__D3oY4)

----------

# Extra materials

* Algorithm Design:
  - Review the Algorithm Design Process at Hired in Tech [bit.ly/1RjEEtY](http://www.hiredintech.com/algorithm-design/the-algorithm-design-canvas/)
  - Actual interview questions on CareerCup [bit.ly/1KaJceA](http://www.careercup.com/user?id=5095734581919744)
  - Cracking the Coding Interview [bit.ly/1K8vnNL](https://www.google.nl/search?q=cracking+the+coding+interview+filetype:pdf)
  - Programming Interviews Exposed [bit.ly/1RwnGJp](https://www.google.nl/search?q=programming+interviews+exposed+filetype:pdf)
* System Design:
  - Five Considerations For Large Scale Systems
  [bit.ly/1RhITWY](https://sites.google.com/site/craigandera/craigs-stuff/scalability-considerations/five-considerations-for-large-scale-systems?tmpl=%2Fsystem%2Fapp%2Ftemplates%2Fprint%2F)
  - Slide 2 from Scalability, Availability & Stability Patterns [bit.ly/1PngBUR](http://www.slideshare.net/jboner/scalability-availability-stability-patterns) (PDF - [bit.ly/1RhIrrN](http://www.mif.vu.lt/~donatas/Vadovavimas/Temos/Reactive%20systems/Scalability/2010%20Scalability%20patterns(slides).pdf))
  - Software Engineering Advice from Building Large-Scale Distributed Systems - Jeff Dean [bit.ly/1mPGLcu](http://research.google.com/people/jeff/stanford-295-talk.pdf)
  - High Scalability ([highscalability.com](http://highscalability.com/)):
  Facebook [bit.ly/1RFwWc4](https://www.usenix.org/conference/nsdi13/technical-sessions/presentation/nishtala),
  Twitter [bit.ly/1LceVw5](http://www.infoq.com/presentations/Twitter-Timeline-Scalability)
  and YouTube [youtu.be/w5WVu624fY8](http://www.youtube.com/watch?v=w5WVu624fY8)
  - Distributed Algorithms in NoSQL Databases [bit.ly/1PnixwL](https://highlyscalable.wordpress.com/2012/09/18/distributed-algorithms-in-nosql-databases/)
