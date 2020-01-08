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

* [Practice algorithm design challenges](algorithm-design):
  - [LeetCode](https://leetcode.com/)
  - [Codility](https://codility.com/)
  - [Geeks for Geeks](https://www.geeksforgeeks.org/)
  - [HackerRank](https://www.hackerrank.com/)

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

----------

# Object Oriented Design

1. What do we want to do (with the objects)?
2. What are the core objects (we operate on)?
3. Anything missing (look closer at details/aspects)?
4. Go deeper - how will the methods work?

----------

# Flashcards

<table>

<tr><td align="center">
DYNAMIC PROG.
</td><td align="left">
TOP-DOWN: MEMOIZATION<br/>
<br/>
BOTTOM-UP: SUBPROBLEMS<br/>
(TABULAR FORM)
</td></tr>

<tr><td align="center">
DFS vs. BFS
</td><td align="left">
WORST CASE O(B<sup>D</sup>)<br/>
DFS: RECURSIVE vs. BFS: FIFO<br/>
(DFS MAY OVERFLOW THE STACK)<br/>
PRE-ORDER: VISIT ROOT<br/>
IN-ORDER: LEFT, ROOT, RIGHT<br/>
POST-ORDER: VISIT CHILDREN
</td></tr>

<tr><td align="center">
HASH TABLES
</td><td align="left">
<ul>
<li><b>FLOAT:</b> M * (KEY - MIN) / (MAX - MIN)</li>
<li><b>INT:</b> KEY % M where {M: MERSENNE PRIME}<br/>% IMPL. CAN BE FAST</li>
<li><b>STRING:</b> Σ 128<sup>i</sup> str[i] % M<br/>128 - DROP NONCODING BITS</li>
</ul>
RESOLUTION:<br/>
<ul>
<li>SEPARATE CHAINING (LISTS)<br/>(A.K.A. OPEN HASHING, CLOSED ADDRESSING)</li>
<li>LINEAR PROBING (ARRAY)<br/>(A.K.A. CLOSED HASHING, OPEN ADDRESSING)</li>
</ul>
</td></tr>

<tr><td align="center">
HEAPS<br/>
(Java: PriorityQueue<sup><a href="https://github.com/altermarkive/interview-training/blob/master/algorithm-design/java/src/test/java/leetcode/LC155MinStack.java">1</a>, <a href="ttps://github.com/altermarkive/interview-training/blob/master/algorithm-design/java/src/test/java/leetcode/LC215KthLargestElementInAnArray.java">2</a></sup>)
</td><td align="left">
CHILD = INDEX' * 2<br/>
PARENT = INDEX' / 2<br/>
<br/>
BUILD: for each index from last parent to 0 (the root) traverse down picking the biggest/smallest child and correcting parent-child order<br/>
INSERT: append to the end of the array and traverse up correcting parent-child order O(log(n))"<br/>
DELETE: overwrite the node with the last and depending on comparing with its parent either traverse up or down O(log(n))"<br/>
SEARCH: linear scan through the array O(n)"<br/>
<br/>
') correct index by 1 for 0-based array<br/>
") WORST/AVG
</td></tr>

<tr><td align="center">
HEAPS<br/>
(USAGE)
</td><td align="left">
<b>RUNNING MEDIAN</b> - GREATER (SMALLER) HALF OF THE NUMBERS IN MIN (MAX) HEAP; INSERT INCOMING INTO HEAP DEPENDING ON COMPARISON WITH CURRENT MEDIAN; REBALANCE IF NECESSARY; MEDIAN IS ONE OF ROOTS OR THEIR AVERAGE; REMOVE OUTGOING SIMILAR
</td></tr>

<tr><td align="center">
GRAPHS
</td><td align="left">
<b>ADJACENCY MATRIX</b><br/>
PRO: LOOKUP TIME (MANY CONNECTIONS)<br/>
CON: SIZE (ALL POSSIBLE CONNECTIONS)<br/>
<b>ADJACENCY LIST</b><br/>
PRO: SIZE, SPEED (FEW CONNECTIONS), SPARSE<br/>
<b>SPACE TRADEOFF</b> X * E &gt; N<sup>2</sup> / 8<br/>
(X depends on pointer size; matrix is packed)<br/>
<b>OBJECTS &amp; POINTERS</b>
</td></tr>

<tr><td align="center">
TRIE TREE
</td><td align="left">
reTRIEval, PREFIX TREE<br/>
(MARKOV CHAIN TXT GEN, AUTOCOMPLETE)<br/>
EXAMPLE: For autocomplete as you build the trie add a flag to each node, indicating if this character is a terminating one.
</td></tr>

<tr><td align="center">
SELF BALANCING BST
</td><td align="left">
Java - red-black tree: TreeMap<br/>
Other algorithm: AVL tree (uses rotation)
</td></tr>

<tr><td align="center">
QUICK SORT
</td><td align="left">
<ul>
<li>(<b><a href="ttps://github.com/altermarkive/interview-training/blob/master/algorithm-design/java/src/test/java/leetcode/LC384ShuffleAnArray.java">shuffle</a></b> first or sample for pivot)</li>
<li>PICK PIVOT</li>
<li>REORDER W.R.T. PIVOT</li>
<li>APPLY TO ARRAYS SEPARATED BY PIVOT</li>
</ul>
UNSTABLE<br/>
WORST O(N<sup>2</sup>) <b>!!!</b><br/>
BEST O(N log(N))<br/>
AVERAGE O(N log(N))
</td></tr>

<tr><td align="center">
MERGE SORT
</td><td align="left">
<ul>
<li>DIVIDE INTO SUBLISTS</li>
<li>SORT SUBLISTS RECURSIVELY</li>
<li>MERGE SUBLISTS</li>
</ul>
STABLE<br/>
WORST O(N log(N)) <b>!!!</b><br/>
BEST O(N log(N))<br/>
AVERAGE O(N log(N))
</td></tr>

<tr><td align="center">
MINIMUM SPANNING TREE
</td><td align="left">
EXAMPLE <b>PRIM'S ALGORITHM</b>
<ol>
<li>CREATE TREE WITH ONE RANDOM VERTEX</li>
<li>CREATE SET OF ALL EDGES</li>
<li>LOOP TILL EVERY EDGE USED</li>
<ol>
<li>USE AN EDGE WITH SMALLEST WEIGHT CONNECTING VERTEX IN THE TREE TO VERTEX NOT YET IN THE TREE</li>
</ol>
</ol>
O((|E| + |V|) log|V|) WITH BINARY HEAP &amp; ADJACENCY
</td></tr>

<tr><td align="center">
DIJKSTRA'S ALGORITHM<br/>
(BUT FLOYD ON NEGATIVE)
</td><td align="left">
SHORTEST PATH O(|V|<sup>2</sup>):
<ol>
<li>FOREACH 0 IF INITIAL, ∞ OTHERWISE (DISTANCE)</li>
<li>MARK ALL UNVISITED; INITIAL AS CURRENT</li>
<li>BREADTH FIRST; FOR EACH CHILD ITS DISTANCE AND OVERWRITE IF LESS</li>
<li>LOWEST DISTANCE UNVISITED NODE AS CURRENT</li>
<li>END WHEN ALL VISITED</li>
</ol>
O((|E| + |V|) log|V|) WITH A PRIORITY QUEUE (SELF-BALANCING BST OR BINARY HEAP)
</td></tr>

<tr><td align="center">
LEADER ELECTION
</td><td align="left">
<b>LELANN-CHANG-ROBERTS</b><br/>
ONLY FORWARD LARGER ID IF HAVE NOT SENT OWN<br/>
TIME O(N)<br/>
AVG. MESSAGE O(N log(N))<br/>
WORST MESSAGE O(N<sup>2</sup>)<br/>
<b>HIRSCHBERG-SINCLAIR</b> ELECTION IN NEIGHBORHOOD<br/>
WORST MESSAGE O(N log(N))
</td></tr>

<tr><td align="center">
P VS. NP<br/>
COMPLETE
</td><td align="left">
<b>P</b>: SOLUTION FOUND IN POLYNOMIAL TIME<br/>
<b>NP</b>: SOLUTION VERIFIABLE IN POLYNOMIAL TIME<br/>
<b>COMPLETE</b>: IF ANY PROBLEM IN THAT CLASS CAN BE REDUCED TO IT<br/>
<b>HARD</b>: IF PROBLEM ALLOWS QUICKLY SOLVE ANY PROBLEM IN THE CLASS<br/>
<br/>
P ≠ NP:<br/>
P ⊂ NP ∧ NP-COMPLETE ≡ NP ∩ NP-HARD<br/>
((P) NP ( NP-COMPLETE ) NP-HARD)
</td></tr>

<tr><td align="center">
NP COMPLETE PROBLEMS
</td><td align="left">
GRAPH COLORING<br/>
TRAVELING SALESMAN<br/>
KNAPSACK PROBLEM<br/>
BOOLEAN SATISFIABILITY<br/>
GRAPH ISOMORPHISM<br/>
SET COVER PROBLEM
</td></tr>

<tr><td align="center">
KNAPSACK PROBLEM
</td><td align="left">
NUMBER OF EACH SO WEIGHT &lt;= W &amp; VALUE MAX<br/>
BOTTOM-UP DYN. PROG:<br/>
W - INTEGERS: UNBOUNDED (SAME ITEM &gt; ONCE): max<sub>wi&lt;=w</sub>(m[w-1], max(vi,m[w-wi])<br/>
<br/>
PSEUDO POLYNOMIAL TIME (LIKE TESTING IF N IS PRIME): - EXP. IN LENGTH (NUMBER OF DIGITS) OF INPUT; POLY - IN NUMERIC VALUE OF INPUT
</td></tr>

<tr><td align="center">
SET COVER PROBLEM
</td><td align="left">
FROM SET OF SETS SELECT SMALLEST NUMBER OF SETS COVERING UNION<br/>
<br/>
GREEDY (CHOOSE ONE COVERING MOST)
</td></tr>

<tr><td align="center">
TSP
</td><td align="left">
<b>EXACT ALGORITHM</b> HELD-KARP O(N<sup>2</sup>2<sup>N</sup>)<br/>
<b>APPROXIMATE ALGORITHM</b>
<ul>
<li><b>CONSTRUCTIVE HEURISTICS</b> GREEDY (NN - 25% of optimal), MINIMUM SPANNING TREE BASED ALGORITHMS (CHRISTOFIDES ALG.)</li>
<li><b>ITERATIVE IMPROVEMENT</b></li>
<li><b>RANDOM IMPROVEMENT</b> ANT COLONY OPTIMIZATION</li>
</ul>
</td></tr>

<tr><td align="center">
PROCESS VS. THREAD<br/>
CONTEXT SWITCH
</td><td align="left">
<b>THREAD</b>: <i>PART OF PROCESS</i>, <i>SHARED MEMORY</i>, <i>ONLY STACK &amp; REGISTERS SAVED</i>, CTX SWITCH - SMALLER CACHE IMPACT<br/>
<b>PROCESS</b>: <i>INDEPENDENT</i>, <i>SEPARATE MEMORY (MAP)</i>, STATE / IPC (FILE HANDLES, DEVICE HANDLES, SOCKETS), CTX SWITCH - MEMORY MAP SWITCH, BIGGER CACHE IMPACT (SOME ARCH. MUST FLUSH), POSSIBLY STATE SWITCH, PAGING<br/>
</td></tr>

<tr><td align="center">
DEADLOCK<br/>
VS.<br/>
LIVELOCK
</td><td align="left">
TWO OR MORE PROCESSES WAIT FOR THE OTHER(S) TO RELEASE THE RESOURCE<br/>
<br/>
THE STATE DOES CHANGE BUT STILL NO PROGRESS<br/>
(COURTESY RELEASE)<br/>
AVOIDANCE BY RANDOMIZATION
</td></tr>

<tr><td align="center">
LOCKS
</td><td align="left">
SLEEPLOCK - SPINLOCK<br/>
<br/>
TEST-AND-SET - FETCH-AND-ADD - CMP-AND-SWAP<br/>
<br/>
<b>MUTEX</b>: 2 STATE<br/>
<b>SEMAPHORE</b>: COUNTS NO OF UNITS OF RESOURCE AVAILABLE<br/>
<b>MONITOR</b>: OBJ. WHERE METHODS ARE MUTUALLY EXCLUSIVE
</td></tr>

<tr><td align="center">
DINING PHILOS.
</td><td align="left">
<ul>
<li>SEMAPHORES / MUTEXES (GRAB FIRST THEN SECOND; RISK: CAN STARVE)</li>
<li>CENTRAL MONITOR / WAITER / CONDUCTOR (CAN EAT IF NEITHER NEIGHBOR EATS; RISK: REDUCED PARALLELISM)</li>
<li>RESOURCE HIERARCHY (NUMBER FORKS; PICK AND PUT DOWN IN ORDER; RISK: LOW EFFICIENCY)</li>
</ul>
</td></tr>

<tr><td align="center">
LOCK (JAVA, PYTHON), CONCURRENT
</td><td align="left">
threading .Lock.acquire( [blocking] )<br/>
threading .Lock.release( [blocking] )<br/>
synchronized<br/>
<a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html">java.util.concurrent</a>
</td></tr>

<tr><td align="center">
SCHEDULING
</td><td align="left">
<ul>
<li>FIFO</li>
<li>FIXED PRIORITY PREEMPTIVE (NOT COOPERATIVE)</li>
<li>ROUND ROBIN (NO INTERRUPTS)</li>
<li>MULTILEVEL FEEDBACK QUEUE: 1. PREFERENCE TO SHORT OR I/O, 2. MULTIPLE FIFO, 3. STARTS AT END OF TOP FIFO, 4. (NORMALLY) TO THE END OF QUEUE (VOLUNTARY), 5. USES UP QUANTUM - PREEMPT AND LOWER QUEUE</li>
</ul>
</td></tr>

<tr><td align="center">
STRING (JAVA)
</td><td align="left">
compareTo - lexi, ignoreCase, starts, endsWith, equals, indexOf, lastIndexOf, matches - regex, replaceAll - regex, replaceFirst - regex, split, substring, toUpperCase, trim
</td></tr>

<tr><td align="center">
SORT &amp; BINARY SEARCH (JAVA)
</td><td align="left">
Arrays<br/>
Collections .sort(List[, Comparator])<br/>
int compare(T, T)<br/>
binarySearch(T[] array, T key)
</td></tr>

<tr><td align="center">
LISTS (JAVA)
</td><td align="left">
Vector<br/>
Collections .synchronizedList(List)<br/>
Where List: ArrayList or LinkedList
</td></tr>

<tr><td align="center">
-∞ (JAVA, PYTHON)
</td><td align="left">
Double .NEGATIVE_INFINITY<br/>
float('-inf')
</td></tr>

<tr><td align="center">
SORT (PYTHON)
</td><td align="left">
sorted([...], key=lambda item: item[1], reverse=True)
</td></tr>

<tr><td align="center">
TCP HANDSHAKE
</td><td align="left">
OPEN
<ol>
<li>→ SYN</li>
<li>← SYN-ACK</li>
<li>→ ACK</li>
</ol>
CLOSE
<ol>
<li>→ FIN</li>
<li>← ACK</li>
<li>← FIN</li>
<li>→ ACK</li>
</ol>
</td></tr>

<tr><td align="center">
MATH
</td><td align="left">
<b>PERMUTATION</b><br/>
N! (factorial)<br/>
<b>VARIATION WITHOUT REP.</b><br/>
N! / (N-K)!<br/>
<b>VARIATION WITH REP. (EQUIVALENT GROUPS REPEAT)</b><br/>
N<sup>K</sup><br/>
<b>COMBINATION</b><br/>
N over K (binomial coefficient)
</td></tr>

</table>

----------

# Extra materials

* Algorithm Design:
  - [Review the Algorithm Design Process at Hired in Tech](https://www.hiredintech.com/algorithm-design/the-algorithm-design-canvas/)
  - [Actual interview questions on CareerCup](https://www.careercup.com/user?id=5095734581919744)
  - [Cracking the Coding Interview](https://www.google.nl/search?q=cracking+the+coding+interview+filetype:pdf)
  - [Programming Interviews Exposed](https://www.google.nl/search?q=programming+interviews+exposed+filetype:pdf)
* System Design:
  - [Slide 2 from Scalability, Availability & Stability Patterns](https://www.mif.vu.lt/~donatas/Vadovavimas/Temos/Reactive%20systems/Scalability/2010%20Scalability%20patterns(slides).pdf)
  - [High Scalability Blog](https://highscalability.com/)
  - [Distributed Algorithms in NoSQL Databases](https://highlyscalable.wordpress.com/2012/09/18/distributed-algorithms-in-nosql-databases/)
  - [Jackson Gabbard - Intro to Architecture and Systems Design - Interviews](https://youtu.be/ZgdS0EUmn70)
