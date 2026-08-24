# Algorithms

---

## DYNAMIC PROGRAMMING

TOP-DOWN: MEMOIZATION<br/>
BOTTOM-UP: TABULATION (SUBPROBLEMS)

---

## HEAPS (functioning)

[qheap1](https://www.hackerrank.com/challenges/qheap1)
[min-stack](https://leetcode.com/problems/min-stack/)
[kth-largest-element-in-an-array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

_CHILD = INDEX × 2_<br/>
_PARENT = INDEX / 2_<br/>
(correct index by 1 for 0-based array; _O_ - WORST/AVG)<br/>

- BUILD: for each index from last parent to 0 (the root) traverse down picking the biggest/smallest child and correcting parent-child order
- INSERT: append to the end of the array and traverse up correcting parent-child order _O(log(n))_
- DELETE: overwrite node with last one and if less/greater than parent traverse up/down _O(log(n))_
- SEARCH: linear scan through the array _O(n)_

---

## HEAPS (usage)

**RUNNING MEDIAN** - GREATER (SMALLER) HALF OF THE NUMBERS IN MIN (MAX) HEAP\;
INSERT INCOMING INTO HEAP DEPENDING ON COMPARISON WITH CURRENT MEDIAN;
REBALANCE IF NECESSARY;
MEDIAN IS ONE OF ROOTS OR THEIR AVERAGE;
REMOVE OUTGOING SIMILAR

---

## GRAPHS

- **ADJACENCY MATRIX**<br/>
  PRO: LOOKUP TIME (MANY CONNECTIONS)<br/>
  CON: SIZE (ALL POSSIBLE CONNECTIONS)
- **ADJACENCY LIST**<br/>
  PRO: SIZE, SPEED (FEW CONNECTIONS), SPARSE
  **SPACE TRADE-OFF:** _X × E vs. N² / 8_<br/>
  (X - pointer size in bytes; matrix is packed - 8 booleans per byte)
- **OBJECTS AND POINTERS**

---

## QUICK SORT

- (**shuffle** first or sample for pivot - median as pivot helps; [LeetCode: shuffle-an-array](https://leetcode.com/problems/shuffle-an-array/))
- PICK PIVOT
- REORDER WITH RESPECT TO PIVOT
- APPLY TO ARRAYS SEPARATED BY PIVOT

UNSTABLE<br/>
WORST: _O(N²)_ **!!!**<br/>
BEST: _O(N × log(N))_<br/>
AVERAGE: _O(N × log(N))_

---

## MERGE SORT

- DIVIDE INTO SUBLISTS
- SORT SUBLISTS RECURSIVELY
- MERGE SUBLISTS

STABLE<br/>
WORST: _O(N × log(N))_ **!!!**<br/>
BEST: _O(N × log(N))_<br/>
AVERAGE: _O(N × log(N))_

---

## MINIMUM SPANNING TREE

EXAMPLE **PRIM'S ALGORITHM**

1. CREATE TREE WITH ONE RANDOM VERTEX
2. CREATE SET OF ALL EDGES
3. LOOP TILL EVERY EDGE USED: USE AN EDGE WITH SMALLEST WEIGHT CONNECTING VERTEX IN THE TREE TO VERTEX NOT YET IN THE TREE

_O((|E| + |V|) × log|V|)_ WITH BINARY HEAP AND ADJACENCY

---

## DIJKSTRA'S ALGORITHM (BUT FLOYD ON NEGATIVE)

SHORTEST PATH _O(|V|²)_:

1. FOREACH 0 IF INITIAL, _∞_ OTHERWISE (DISTANCE)
2. MARK ALL UNVISITED; INITIAL AS CURRENT
3. BREADTH FIRST; FOR EACH CHILD ITS DISTANCE AND OVERWRITE IF LESS
4. LOWEST DISTANCE UNVISITED NODE AS CURRENT
5. END WHEN ALL VISITED

_O((|E| + |V|) × log|V|)_ WITH A PRIORITY QUEUE (SELF-BALANCING BST OR BINARY HEAP)

---

## P vs. NP

- **P**: SOLUTION FOUND IN POLYNOMIAL TIME
- **NP**: SOLUTION VERIFIABLE IN POLYNOMIAL TIME
- **COMPLETE**: IF ANY PROBLEM IN THAT CLASS CAN BE REDUCED TO IT
- **HARD**: IF PROBLEM ALLOWS QUICKLY SOLVE ANY PROBLEM IN THE CLASS

_P ≠ NP_: _P ⊂ NP_, _NP-COMPLETE ≡ NP - NP-HARD_
