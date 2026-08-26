package lc232

import "testing"

func TestSomething(t *testing.T) {
	queue := &MyQueue{}
	for i := range 6 {
		queue.Push(i)
	}
	for i := range 6 {
		if queue.Empty() {
			t.Errorf("MyQueue - Expected a non-empty queue!")
		}
		if i != queue.Peek() {
			t.Errorf("MyQueue - Expected %v, got %v!", i, queue.Peek())
		}
		queue.Pop()
	}
	if !queue.Empty() {
		t.Errorf("MyQueue - Expected an empty queue!")
	}
}
