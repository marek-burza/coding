package geeksforgeeks.gfg7094_amazon_social_network_traversal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * http://qa.geeksforgeeks.org/7094
 */
public final class GFG7094CourseRecommendation {
    protected Map<String, List<String>> friendships = new HashMap<>();
    protected Map<String, List<String>> attendances = new HashMap<>();

    protected GFG7094CourseRecommendation() {
    }

    public List<String> getDirectFriendsForUser(final String user) {
        List<String> result = friendships.get(user);
        if (null == result) {
            result = new ArrayList<>();
        }
        return result;
    }

    public List<String> getAttendedCoursesForUser(final String user) {
        List<String> result = attendances.get(user);
        if (null == result) {
            result = new ArrayList<>();
        }
        return result;
    }

    private void populateDirectFriends(final String user, final Set<String> network) {
        network.addAll(getDirectFriendsForUser(user).stream().collect(Collectors.toList()));
    }

    private Set<String> populateSocialNetwork(final String user) {
        Set<String> immediate = new HashSet<>();
        populateDirectFriends(user, immediate);
        Set<String> network = new HashSet<>();
        for (String friend : immediate) {
            populateDirectFriends(friend, network);
        }
        network.addAll(immediate);
        return network;
    }

    private static class Popularity {
        public final String course;
        protected int popularity = 1;

        public void increase() {
            popularity++;
        }

        Popularity(final String courseValue) {
            course = courseValue;
        }
    }

    private static class PopularityComparator implements Comparator<Popularity>, Serializable {
        @Override
        public int compare(final Popularity item1, final Popularity item2) {
            if (item1.popularity < item2.popularity) {
                return -1;
            }
            if (item1.popularity > item2.popularity) {
                return 1;
            }
            return 0;
        }
    }

    private Set<String> coursesAsSet(final String user) {
        return getAttendedCoursesForUser(user).stream().collect(Collectors.toSet());
    }

    public List<String> getRankedCourses(final String user) {
        Set<String> network = populateSocialNetwork(user);
        Set<String> own = coursesAsSet(user);
        Map<String, Popularity> courseToPopularity = new HashMap<>();
        List<Popularity> coursesByPopularity = new ArrayList<>();
        for (String person : network) {
            for (String course : getAttendedCoursesForUser(person)) {
                if (own.contains(course)) {
                    continue;
                }
                if (courseToPopularity.containsKey(course)) {
                    courseToPopularity.get(course).increase();
                } else {
                    Popularity popularity = new Popularity(course);
                    courseToPopularity.put(course, popularity);
                    coursesByPopularity.add(popularity);
                }
            }
        }
        Collections.sort(coursesByPopularity, Collections.reverseOrder(new PopularityComparator()));
        return coursesByPopularity.stream().map(popularity -> popularity.course).collect(Collectors.toList());
    }
}
