package lc225

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MyStack - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	solution := &MyStack{}
	solution.Push(5)
	solution.Push(2)
	generic(t, solution.Top(), 2)
	solution.Pop()
	generic(t, solution.Top(), 5)
	if solution.Empty() {
		t.Errorf("MyStack - Expected a non-empty stack!")
	}
	solution.Pop()
	if !solution.Empty() {
		t.Errorf("MyStack - Expected an empty stack!")
	}
}
