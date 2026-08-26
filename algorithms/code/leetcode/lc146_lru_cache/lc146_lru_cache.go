// Package lc146 implements https://leetcode.com/problems/lru-cache/
// #medium
package lc146

type node struct {
	key       int
	value     int
	preceding *node
	following *node
}

// LRUCache Defines a least recently used cache of a fixed capacity
type LRUCache struct {
	capacity int
	lut      map[int]*node
	head     *node
	tail     *node
}

// NewLRUCache Creates a cache holding up to the given number of entries
func NewLRUCache(capacity int) *LRUCache {
	lru := &LRUCache{
		capacity: capacity,
		lut:      make(map[int]*node),
		head:     &node{},
		tail:     &node{},
	}
	lru.head.following = lru.tail
	lru.tail.preceding = lru.head
	return lru
}

// Get Returns the value stored under the key or -1 when there is none
func (lru *LRUCache) Get(key int) int {
	value := -1
	if _, found := lru.lut[key]; found {
		value = lru.Remove(key)
		lru.Insert(key, value)
	}
	return value
}

// Put Stores the value under the key, evicting the least recently used entry
func (lru *LRUCache) Put(key int, value int) {
	if _, found := lru.lut[key]; found {
		lru.Remove(key)
	} else if len(lru.lut) >= lru.capacity {
		lru.Remove(lru.tail.preceding.key)
	}
	lru.Insert(key, value)
}

// Insert Adds the key and the value in front of all the other entries
func (lru *LRUCache) Insert(key int, value int) {
	current := &node{key: key, value: value}
	current.preceding = lru.head
	current.following = lru.head.following
	current.preceding.following = current
	current.following.preceding = current
	lru.lut[key] = current
}

// Remove Drops the entry stored under the key and returns its value
func (lru *LRUCache) Remove(key int) int {
	current := lru.lut[key]
	delete(lru.lut, key)
	current.preceding.following = current.following
	current.following.preceding = current.preceding
	return current.value
}
