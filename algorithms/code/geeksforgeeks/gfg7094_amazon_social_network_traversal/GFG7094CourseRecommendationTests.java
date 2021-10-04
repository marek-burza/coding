package geeksforgeeks.gfg7094_amazon_social_network_traversal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * http://qa.geeksforgeeks.org/7094
 */
public class GFG7094CourseRecommendationTests {
    public static GFG7094CourseRecommendation mock(final String[][] friendships, final String[][] attendances) {
        GFG7094CourseRecommendation instance;
        instance = new GFG7094CourseRecommendation();
        instance.friendships = new HashMap<>();
        for (String[] friendship : friendships) {
            List<String> other = Arrays.asList(friendship);
            String who = other.get(0);
            instance.friendships.put(who, other.subList(1, other.size()));
        }
        instance.attendances = new HashMap<>();
        for (String[] attendance : attendances) {
            List<String> other = Arrays.asList(attendance);
            String who = other.get(0);
            instance.attendances.put(who, other.subList(1, other.size()));
        }
        return instance;
    }

    private static final String[][] FRIENDSHIPS = { { "Jack"/* knows */, "Jane", "John" },
            { "John"/* knows */, "Alice", "Jack", "Jane" }, { "Alice"/* knows */, "Bob" },
            { "Bob"/* knows */, "Alice", "Jane" }, { "Jane"/* knows */, "Jack", "John" },
            { "Loner"/* knows nobody */ } };
    private static final String[][] ATTENDANCES = { { "Jack"/* attended */, "Science 1" },
            { "John"/* attended */, "Science 1", "Arts" }, { "Alice"/* attended */, "Science 1", "Science 2", "Arts" },
            { "Bob"/* attended */, "Arts", "Sports", "Music" },
            { "Jane"/* attended */, "Science 1", "Science 2", "Arts", "Sports" },
            { "Loner"/* attended */, "Philosophy" } };

    @Test
    public void testLoner() throws Exception {
        GFG7094CourseRecommendation instance;
        instance = mock(FRIENDSHIPS, ATTENDANCES);
        String[] expectedLoner = {};
        test(expectedLoner, instance.getRankedCourses("Loner"));
    }

    @Test
    public void testJack() throws Exception {
        GFG7094CourseRecommendation instance;
        instance = mock(FRIENDSHIPS, ATTENDANCES);
        String[] expectedJack = { "Arts", "Science 2", "Sports" };
        test(expectedJack, instance.getRankedCourses("Jack"));
    }

    @Test
    public void testJane() throws Exception {
        GFG7094CourseRecommendation instance;
        instance = mock(FRIENDSHIPS, ATTENDANCES);
        String[] expectedJane = {};
        test(expectedJane, instance.getRankedCourses("Jane"));
    }

    @Test
    public void testLeftovers() throws Exception {
        GFG7094CourseRecommendation instance;
        instance = mock(FRIENDSHIPS, ATTENDANCES);
        assertEquals(0, instance.getAttendedCoursesForUser("").size());
        assertEquals(0, instance.getDirectFriendsForUser("").size());
        String[][] friendships = { { "Student1"/* knows */, "Student2", "Student3" },
                { "Student2"/* knows */, "Student1", "Student3" }, { "Student3"/* knows */, "Student1", "Student2" } };
        String[][] attendances = { { "Student2"/* attended */, "Course1" }, { "Student2"/* attended */, "Course2" },
                { "Student3"/* attended */, "Course3" } };
        instance = mock(friendships, attendances);
        assertEquals(2, instance.getRankedCourses("Student1").size());
    }

    private void test(final String[] expected, final List<String> result) throws Exception {
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }
}
