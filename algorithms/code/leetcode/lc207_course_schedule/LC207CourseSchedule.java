package leetcode.lc207_course_schedule;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule/
 * #medium
 */
public final class LC207CourseSchedule {
    private static class State {
        private int node;
        private Set<Integer> ancestors;

        State(final int nodeValue, final Set<Integer> ancestorsValue) {
            node = nodeValue;
            ancestors = ancestorsValue;
        }
    }

    private boolean dfs(final HashMap<Integer, Set<Integer>> graph, final int nodeValue,
            final HashSet<Integer> visited) {
        int node = nodeValue;
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(node, new HashSet<>()));
        while (!queue.isEmpty()) {
            State state = queue.poll();
            node = state.node;
            if (state.ancestors.contains(node)) {
                return false;
            }
            if (!visited.contains(node)) {
                visited.add(node);
                if (graph.containsKey(node)) {
                    for (int other : graph.get(node)) {
                        Set<Integer> ancestors = new HashSet<>(state.ancestors);
                        ancestors.add(node);
                        queue.add(new State(other, ancestors));
                    }
                }
            }
        }
        return true;
    }

    public boolean canFinish(final int numCourses, final int[][] prerequisites) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            Set<Integer> set;
            if (graph.containsKey(prerequisite[1])) {
                set = graph.get(prerequisite[1]);
            } else {
                set = new HashSet<>();
                graph.put(prerequisite[1], set);
            }
            set.add(prerequisite[0]);
        }
        HashSet<Integer> visited = new HashSet<>();
        for (Integer start : graph.keySet()) {
            if (!dfs(graph, start, visited)) {
                return false;
            }
        }
        return true;
    }
}
