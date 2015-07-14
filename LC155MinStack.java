package leetcode;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class LC155MinStack {
    private Stack<Integer> stack = new Stack<>();
    private PriorityQueue<Integer> heap = new PriorityQueue<>();

    public void push(int x) {
        stack.push(x);
        heap.add(x);
    }

    public void pop() {
        heap.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return heap.peek();
    }

    public static void main(String[] arguments) {
        LC155MinStack solution = new LC155MinStack();
        solution.push(5);
        System.out.println(solution.getMin());
        solution.push(4);
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
        solution.push(3);
        System.out.println(solution.getMin());
        solution.top();
        System.out.println(solution.getMin());
        solution.push(2);
        System.out.println(solution.getMin());
        solution.push(1);
        System.out.println(solution.getMin());
    }
}
