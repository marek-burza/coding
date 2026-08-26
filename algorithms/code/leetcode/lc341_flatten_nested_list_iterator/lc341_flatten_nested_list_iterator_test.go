package lc341

import "testing"

func generic(t *testing.T, used []*NestedInteger, expected []int) {
	nested := NewNestedIterator(used)
	for _, value := range expected {
		if !nested.HasNext() {
			t.Errorf("NestedIterator - Expected %v, got nothing!", value)
			return
		}
		result := nested.Next()
		if value != result {
			t.Errorf("NestedIterator - Expected %v, got %v!", value, result)
		}
	}
	if nested.HasNext() {
		t.Errorf("NestedIterator - Expected nothing more!")
	}
}

func TestExample1(t *testing.T) {
	var list11A []*NestedInteger
	list11A = append(list11A, NewNestedInteger(1))
	list11A = append(list11A, NewNestedInteger(1))
	var list11B []*NestedInteger
	list11B = append(list11B, NewNestedInteger(1))
	list11B = append(list11B, NewNestedInteger(1))
	var listTop []*NestedInteger
	listTop = append(listTop, NewNestedInteger(list11A))
	listTop = append(listTop, NewNestedInteger(2))
	listTop = append(listTop, NewNestedInteger(list11B))
	expected := []int{1, 1, 2, 1, 1}
	generic(t, listTop, expected)
}

func TestExample2(t *testing.T) {
	var listTop []*NestedInteger
	listTop = append(listTop, NewNestedInteger(1))
	var list4 []*NestedInteger
	list4 = append(list4, NewNestedInteger(4))
	var list6 []*NestedInteger
	list6 = append(list6, NewNestedInteger(6))
	list4 = append(list4, NewNestedInteger(list6))
	listTop = append(listTop, NewNestedInteger(list4))
	expected := []int{1, 4, 6}
	generic(t, listTop, expected)
}
