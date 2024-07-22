# DYNAMIC PROGRAMMING

---

TOP-DOWN: MEMOIZATION<br/>
BOTTOM-UP: TABULATION (SUBPROBLEMS)

---

# DFS vs. BFS

---

WORST CASE *O(B<sup>D</sup>)*<br/>
DFS: RECURSIVE versus BFS: FIFO<br/>
(DFS MAY OVERFLOW THE STACK)<br/>
PRE-ORDER: VISIT ROOT<br/>
IN-ORDER: LEFT, ROOT, RIGHT<br/>
POST-ORDER: VISIT CHILDREN

---

# HASH TABLES

---

* **FLOAT:** *((KEY - MIN) / (MAX - MIN)) % M*<br/>
* **INT:** *KEY % M* where *{M: MERSENNE PRIME}*<br/>
IMPLEMENTATION CAN BE FAST<br/>
* **STRING:** *binary(string) % M*<br/>
(CAST CONSIDERING NUMBER OF CHARACTER CODING BITS)<br/>

**RESOLUTION**<br/>
(constant if enough buckets, linear within a bucket)<br/>
* SEPARATE CHAINING (LISTS)<br/>
(OPEN HASHING, CLOSED ADDRESSING)
* LINEAR PROBING (ARRAY)<br/>
(CLOSED HASHING, OPEN ADDRESSING)

---

# HEAPS (part 1)
(Java: PriorityQueue)<br/>
[HackerRank: qheap1](https://www.hackerrank.com/challenges/qheap1)<br/>
[LeetCode: min-stack](https://leetcode.com/problems/min-stack/)<br/>
[LeetCode: kth-largest-element-in-an-array](https://leetcode.com/problems/kth-largest-element-in-an-array/)<br/>

---

*CHILD = INDEX × 2*<br/>
*PARENT = INDEX / 2*<br/>
(correct index by 1 for 0-based array; *O* - WORST/AVG)<br/>

* BUILD: for each index from last parent to 0 (the root) traverse down picking the biggest/smallest child and correcting parent-child order
* INSERT: append to the end of the array and traverse up correcting parent-child order *O(log(n))*
* DELETE: overwrite node with last one and if less/greater than parent traverse up/down *O(log(n))*
* SEARCH: linear scan through the array *O(n)*

---

# HEAPS (part 2)
(USAGE)

---

**RUNNING MEDIAN** - GREATER (SMALLER) HALF OF THE NUMBERS IN MIN (MAX) HEAP\;
INSERT INCOMING INTO HEAP DEPENDING ON COMPARISON WITH CURRENT MEDIAN;
REBALANCE IF NECESSARY;
MEDIAN IS ONE OF ROOTS OR THEIR AVERAGE;
REMOVE OUTGOING SIMILAR

---

# GRAPHS

---

* **ADJACENCY MATRIX**<br/>
PRO: LOOKUP TIME (MANY CONNECTIONS)<br/>
CON: SIZE (ALL POSSIBLE CONNECTIONS)
* **ADJACENCY LIST**<br/>
PRO: SIZE, SPEED (FEW CONNECTIONS), SPARSE
**SPACE TRADEOFF:** *X × E vs. N² / 8*<br/>
(X - pointer size in bytes; matrix is packed - 8 booleans per byte)
* **OBJECTS AND POINTERS**

---

# TRIE TREE

---

reTRIEval, PREFIX TREE<br/>
(MARKOV CHAIN TXT GEN, AUTOCOMPLETE)<br/>
EXAMPLE: For autocomplete as you build the trie add a flag to each node,
indicating if this character is a terminating one.

---

# SELF-BALANCING BST

---

Java - red-black tree: TreeMap<br/>
Other algorithm: AVL tree (uses rotation)

---

# QUICK SORT

---

* (**shuffle** first or sample for pivot - median as pivot helps; [LeetCode: shuffle-an-array](https://leetcode.com/problems/shuffle-an-array/))
* PICK PIVOT
* REORDER WITH RESPECT TO PIVOT
* APPLY TO ARRAYS SEPARATED BY PIVOT

UNSTABLE<br/>
WORST: *O(N²)* **!!!**<br/>
BEST: *O(N × log(N))*<br/>
AVERAGE: *O(N × log(N))*

---

# MERGE SORT

---

* DIVIDE INTO SUBLISTS
* SORT SUBLISTS RECURSIVELY
* MERGE SUBLISTS

STABLE<br/>
WORST: *O(N × log(N))* **!!!**<br/>
BEST: *O(N × log(N))*<br/>
AVERAGE: *O(N × log(N))*

---

# MINIMUM SPANNING TREE

---

EXAMPLE **PRIM'S ALGORITHM**
1. CREATE TREE WITH ONE RANDOM VERTEX
2. CREATE SET OF ALL EDGES
3. LOOP TILL EVERY EDGE USED: USE AN EDGE WITH SMALLEST WEIGHT CONNECTING VERTEX IN THE TREE TO VERTEX NOT YET IN THE TREE

*O((|E| + |V|) × log|V|)* WITH BINARY HEAP AND ADJACENCY

---

# DIJKSTRA'S ALGORITHM
(BUT FLOYD ON NEGATIVE)

---

SHORTEST PATH *O(|V|²)*:
1. FOREACH 0 IF INITIAL, *∞* OTHERWISE (DISTANCE)
2. MARK ALL UNVISITED; INITIAL AS CURRENT
3. BREADTH FIRST; FOR EACH CHILD ITS DISTANCE AND OVERWRITE IF LESS
4. LOWEST DISTANCE UNVISITED NODE AS CURRENT
5. END WHEN ALL VISITED

*O((|E| + |V|) × log|V|)* WITH A PRIORITY QUEUE (SELF-BALANCING BST OR BINARY HEAP)

---

# LEADER ELECTION

---

**LELANN-CHANG-ROBERTS**<br/>
ONLY FORWARD LARGER ID IF HAVE NOT SENT OWN<br/>
TIME *O(N)*<br/>
AVERAGE MESSAGE *O(N × log(N))*<br/>
WORST MESSAGE *O(N²)*<br/>
**HIRSCHBERG-SINCLAIR** ELECTION IN NEIGHBORHOOD<br/>
WORST MESSAGE *O(N × log(N))*

---

# P vs. NP
COMPLETE

---

* **P**: SOLUTION FOUND IN POLYNOMIAL TIME
* **NP**: SOLUTION VERIFIABLE IN POLYNOMIAL TIME
* **COMPLETE**: IF ANY PROBLEM IN THAT CLASS CAN BE REDUCED TO IT
* **HARD**: IF PROBLEM ALLOWS QUICKLY SOLVE ANY PROBLEM IN THE CLASS

*P ≠ NP*: *P ⊂ NP*, *NP-COMPLETE ≡ NP - NP-HARD*

---

# NP-COMPLETE PROBLEMS

---

GRAPH COLORING<br/>
TRAVELING SALESMAN<br/>
KNAPSACK PROBLEM<br/>
BOOLEAN SATISFIABILITY<br/>
GRAPH ISOMORPHISM<br/>
SET COVER PROBLEM

---

# UNBOUNDED KNAPSACK PROBLEM
[HackerRank: unbounded-knapsack](http://www.hackerrank.com/challenges/unbounded-knapsack)

---

USE ANY ITEM FROM SET<br/>
UNBOUNDED - SAME ITEM CAN BE USED MOTE THAN ONCE<br/>
MAXIMIZE THE VALUE SUM BUT KEEP WEIGHT UP TO A LIMIT<br/>
SOLUTION: BOTTOM-UP DYNAMIC PROGRAMMING<br/>

PSEUDO POLYNOMIAL TIME (LIKE TESTING IF N IS PRIME) - EXPONENTIAL IN LENGTH (NUMBER OF DIGITS) OF INPUT; POLYNOMIAL IN NUMERIC VALUE OF INPUT

---

# SET COVER PROBLEM

---

FROM SET OF SETS SELECT SMALLEST NUMBER OF SETS COVERING UNION

GREEDY (CHOOSE ONE COVERING MOST)

---

# TRAVELING SALESMAN PROBLEM

---

**EXACT ALGORITHM:** HELD-KARP *O(N²2<sup>N</sup>))*<br/>
**APPROXIMATE ALGORITHM:**
* **CONSTRUCTIVE HEURISTICS** GREEDY (NN - 25% of optimal), MINIMUM SPANNING TREE BASED ALGORITHMS (CHRISTOFIDES ALGORITHM)
* **ITERATIVE IMPROVEMENT**
* **RANDOM IMPROVEMENT** ANT COLONY OPTIMIZATION

---

# PROCESS VERSUS THREAD
CONTEXT SWITCH

---

* **THREAD:** PART OF PROCESS, SHARED MEMORY, ONLY STACK AND REGISTERS SAVED, CTX SWITCH - SMALLER CACHE IMPACT
* **PROCESS:** INDEPENDENT, SEPARATE MEMORY (MAP), STATE / IPC (FILE HANDLES, DEVICE HANDLES, SOCKETS), CTX SWITCH - MEMORY MAP SWITCH, BIGGER CACHE IMPACT (SOME ARCHITECTURES MUST FLUSH), POSSIBLY STATE SWITCH, PAGING

---

# DEADLOCK vs. LIVELOCK

---

**TWO OR MORE PROCESSES WAIT FOR THE OTHERS TO RELEASE THE RESOURCE**

THE STATE DOES CHANGE BUT STILL NO PROGRESS<br/>
(COURTESY RELEASE)<br/>
AVOIDANCE BY RANDOMIZATION

---

# LOCKS

---

SLEEPLOCK - SPINLOCK

TEST-AND-SET - FETCH-AND-ADD - CMP-AND-SWAP

* **MUTEX:** 2 STATE
* **SEMAPHORE:** COUNTS NO OF UNITS OF RESOURCE AVAILABLE
* **MONITOR:** OBJECTS WHERE METHODS ARE MUTUALLY EXCLUSIVE

---

# DINING PHILOSOPHERS

---

* SEMAPHORES / MUTEXES (GRAB FIRST THEN SECOND; RISK: CAN STARVE)
* CENTRAL MONITOR/WAITER/CONDUCTOR (CAN EAT IF NEITHER NEIGHBOR EATS; RISK: REDUCED PARALLELISM)
* RESOURCE HIERARCHY (NUMBER FORKS; PICK AND PUT DOWN IN ORDER; RISK: LOW EFFICIENCY)

---

# LOCK (JAVA, PYTHON), CONCURRENT

---

`threading.Lock.acquire([blocking])`<br/>
`threading.Lock.release([blocking])`<br/>
`synchronized`<br/>
[java.util.concurrent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)

---

# SCHEDULING

---

* FIFO
* FIXED PRIORITY PREEMPTIVE (NOT COOPERATIVE)
* ROUND ROBIN (NO INTERRUPTS)
* MULTILEVEL FEEDBACK QUEUE: 1 - PREFERENCE TO SHORT OR I/O, 2 - MULTIPLE FIFO, 3 - STARTS AT END OF TOP FIFO, 4 - (NORMALLY) TO THE END OF QUEUE (VOLUNTARY), 5 - USES UP QUANTUM - PREEMPT AND LOWER QUEUE

---

# STRING (JAVA)

---

compareTo - lexi, ignoreCase, starts, endsWith, equals, indexOf, lastIndexOf, matches - reg. exp., replaceAll - reg. exp., replaceFirst - reg. exp., split, substring, toUpperCase, trim

---

# SORT AND BINARY SEARCH (JAVA)

---

`Arrays`<br/>
`Collections.sort(List[, Comparator])`<br/>
`int compare(T, T)`<br/>
`binarySearch(T[] array, T key)`

---

# LISTS (JAVA)

---

`Vector`<br/>
`Collections.synchronizedList(List)`<br/>
Where `List`: `ArrayList` or `LinkedList`

---

*-∞* (JAVA, PYTHON)

---

`Double.NEGATIVE_INFINITY`<br/>
`float('-inf')`

---

# SORT (PYTHON)

---

`sorted(..., key=lambda item: item[1], reverse=True)`

---

# TCP HANDSHAKE

---

OPEN
1. → SYN
2. ← SYN-ACK
3. → ACK

CLOSE
1. → FIN
2. ← ACK
3. ← FIN
4. → ACK

---

# COMBINATORIAL

---

* **PERMUTATION:** *N!* ← factorial<br/>
`itertools.permutations(iterable, None)`
* **VARIATION WITHOUT REPETITIONS:** *N! / (N - K)!*<br/>
`itertools.chain.from_iterable(map(lambda c: itertools.permutations(c), itertools.combinations(iterable, k)))`
* **VARIATION WITH REPETITIONS (EQUIVALENT GROUPS REPEAT):** *N<sup>K</sup>*<br/>
`itertools.product(iterable, repeat=k)`
* **COMBINATION:** *N-choose-K*<br/>
`itertools.combinations(iterable, k)`
