package geeksforgeeks;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * http://qa.geeksforgeeks.org/7094
 */
public class GFG7094CourseRecommendation {
    private Map<String, List<String>> friendships = new HashMap<>();
    private Map<String, List<String>> attendances = new HashMap<>();

    public void mock(String[][] friendships, String[][] attendances) {
        this.friendships = new HashMap<>();
        for (String[] friendship : friendships) {
            List<String> other = Arrays.asList(friendship);
            String who = other.get(0);
            this.friendships.put(who, other.subList(1, other.size()));
        }
        this.attendances = new HashMap<>();
        for (String[] attendance : attendances) {
            List<String> other = Arrays.asList(attendance);
            String who = other.get(0);
            this.attendances.put(who, other.subList(1, other.size()));
        }
    }

    public List<String> getDirectFriendsForUser(String user) {
        List<String> result = friendships.get(user);
        if (null == result) {
            result = new ArrayList<>();
        }
        return result;
    }

    public List<String> getAttendedCoursesForUser(String user) {
        List<String> result = attendances.get(user);
        if (null == result) {
            result = new ArrayList<>();
        }
        return result;
    }

    private void populateDirectFriends(String user, Set<String> network) {
        network.addAll(getDirectFriendsForUser(user).stream().collect(Collectors.toList()));
    }

    private Set<String> populateSocialNetwork(String user) {
        Set<String> immediate = new HashSet<>();
        populateDirectFriends(user, immediate);
        Set<String> network = new HashSet<>();
        for (String friend : immediate) {
            populateDirectFriends(friend, network);
        }
        network.addAll(immediate);
        return network;
    }

    private class Popularity implements Comparable<Popularity> {
        public final String course;
        protected int popularity = 1;

        public void increase() {
            popularity++;
        }

        public Popularity(String course) {
            this.course = course;
        }

        @Override
        public int compareTo(Popularity other) {
            if (popularity < other.popularity) return -1;
            if (popularity > other.popularity) return 1;
            return 0;
        }
    }

    private Set<String> coursesAsSet(String user) {
        return getAttendedCoursesForUser(user).stream().collect(Collectors.toSet());
    }


    public List<String> getRankedCourses(String user) {
        Set<String> network = populateSocialNetwork(user);
        Set<String> own = coursesAsSet(user);
        Map<String, Popularity> courseToPopularity = new HashMap<>();
        List<Popularity> coursesByPopularity = new ArrayList<>();
        for (String person : network) {
            for (String course : getAttendedCoursesForUser(person)) {
                if (own.contains(course)) continue;
                if (courseToPopularity.containsKey(course)) {
                    courseToPopularity.get(course).increase();
                } else {
                    Popularity popularity = new Popularity(course);
                    courseToPopularity.put(course, popularity);
                    coursesByPopularity.add(popularity);
                }
            }
        }
        Collections.sort(coursesByPopularity, Collections.reverseOrder());
        return coursesByPopularity.stream().map(popularity -> popularity.course).collect(Collectors.toList());
    }

    @Test
    public void test_example() throws Exception {
        String[][] friendships = {
                {"Jack"/* knows */, "Jane", "John"},
                {"John"/* knows */, "Alice", "Jack", "Jane"},
                {"Alice"/* knows */, "Bob"},
                {"Bob"/* knows */, "Alice", "Jane"},
                {"Jane"/* knows */, "Jack", "John"},
                {"Loner"/* knows nobody */}
        };
        String[][] attendances = {
                {"Jack"/* attended */, "Science 1"},
                {"John"/* attended */, "Science 1", "Arts"},
                {"Alice"/* attended */, "Science 1", "Science 2", "Arts"},
                {"Bob"/* attended */, "Arts", "Sports"},
                {"Jane"/* attended */, "Science 1", "Science 2", "Arts", "Sports"},
                {"Loner"/* attended */, "Philosophy"}
        };
        mock(friendships, attendances);
        String[] expectedLoner = {};
        test(expectedLoner, getRankedCourses("Loner"));
        String[] expectedJack = {"Arts", "Science 2", "Sports"};
        test(expectedJack, getRankedCourses("Jack"));
        String[] expectedJane = {};
        test(expectedJane, getRankedCourses("Jane"));
    }

    private void test(String[] expected, List<String> result) throws Exception {
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }
}
