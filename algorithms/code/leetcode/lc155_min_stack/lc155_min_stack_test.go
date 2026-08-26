package lc155

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MinStack - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	solution := &MinStack{}
	solution.Pop()
	generic(t, solution.Top(), -1)
	generic(t, solution.GetMin(), -1)
	solution.Push(5)
	generic(t, solution.GetMin(), 5)
	solution.Push(4)
	generic(t, solution.GetMin(), 4)
	solution.Pop()
	generic(t, solution.GetMin(), 5)
	solution.Push(3)
	generic(t, solution.GetMin(), 3)
	solution.Top()
	generic(t, solution.GetMin(), 3)
	solution.Push(2)
	generic(t, solution.GetMin(), 2)
	solution.Push(1)
	generic(t, solution.GetMin(), 1)
}
