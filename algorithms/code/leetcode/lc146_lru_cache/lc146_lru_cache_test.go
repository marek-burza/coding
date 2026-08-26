package lc146

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("LRUCache - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	lru := NewLRUCache(2)
	lru.Put(1, 1)
	lru.Put(2, 2)
	generic(t, lru.Get(1), 1)
	lru.Put(3, 3)
	generic(t, lru.Get(2), -1)
	lru.Put(4, 4)
	generic(t, lru.Get(1), -1)
	generic(t, lru.Get(3), 3)
	generic(t, lru.Get(4), 4)
}

func TestRepeatedPutSame(t *testing.T) {
	lru := NewLRUCache(1)
	lru.Put(1, 1)
	lru.Put(1, 1)
	generic(t, lru.Get(1), 1)
}
