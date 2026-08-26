// Package lc341 implements https://leetcode.com/problems/flatten-nested-list-iterator/
// #medium
package lc341

// NestedInteger Holds either a single integer or a list of nested integers
type NestedInteger struct {
	object any
}

// NewNestedInteger Wraps an integer or a list of nested integers
func NewNestedInteger(item any) *NestedInteger {
	return &NestedInteger{object: item}
}

// IsInteger Tells whether a single integer is held
func (nested *NestedInteger) IsInteger() bool {
	_, isInteger := nested.object.(int)
	return isInteger
}

// GetInteger Returns the held integer
func (nested *NestedInteger) GetInteger() int {
	return nested.object.(int)
}

// GetList Returns the held list of nested integers
func (nested *NestedInteger) GetList() []*NestedInteger {
	return nested.object.([]*NestedInteger)
}

type item struct {
	item *NestedInteger
	skip int
}

// NestedIterator Iterates over all the integers of a nested list
type NestedIterator struct {
	stack []*item
}

// NewNestedIterator Creates an iterator over the given nested list
func NewNestedIterator(nestedList []*NestedInteger) *NestedIterator {
	iterator := &NestedIterator{}
	iterator.stack = append(iterator.stack, &item{NewNestedInteger(nestedList), 0})
	return iterator
}

func objectify(nested *NestedInteger, skip int) *item {
	if nested.IsInteger() {
		return &item{NewNestedInteger(nested.GetInteger()), skip}
	}
	return &item{NewNestedInteger(nested.GetList()), skip}
}

func (iterator *NestedIterator) find() {
	for len(iterator.stack) != 0 && !iterator.stack[len(iterator.stack)-1].item.IsInteger() {
		top := iterator.stack[len(iterator.stack)-1]
		listed := top.item.GetList()
		if len(listed) <= top.skip {
			iterator.stack = iterator.stack[:len(iterator.stack)-1]
			continue
		}
		iterator.stack = append(iterator.stack, objectify(listed[top.skip], 0))
		top.skip++
	}
}

// Next Returns the following integer of the flattened list
func (iterator *NestedIterator) Next() int {
	// if !iterator.HasNext() { return 0 }
	top := iterator.stack[len(iterator.stack)-1]
	iterator.stack = iterator.stack[:len(iterator.stack)-1]
	return top.item.GetInteger()
}

// HasNext Tells whether there are any integers left
func (iterator *NestedIterator) HasNext() bool {
	iterator.find()
	return len(iterator.stack) != 0
}
