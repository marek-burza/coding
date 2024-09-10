// https://web.archive.org/web/20180317031648/http://qa.geeksforgeeks.org/7094/amazon-social-network-traversal

use std::collections::{HashMap, HashSet};

pub struct Network {
    friendships: HashMap<String, Vec<String>>,
    attendances: HashMap<String, Vec<String>>,
}

impl Network {
    pub fn get_direct_friends_for_user(&self, user: &String) -> Vec<String> {
        self.friendships.get(user).cloned().unwrap_or_default()
    }

    pub fn get_attended_courses_for_user(&self, user: &String) -> Vec<String> {
        self.attendances.get(user).cloned().unwrap_or_default()
    }

    pub fn get_friends_of_friends(&self, user: &String) -> Vec<String> {
        let friends = self.get_direct_friends_for_user(user);
        let mut circle_set = HashSet::new();
        for friend in friends {
            circle_set.insert(friend.clone());
            for second in self.get_direct_friends_for_user(&friend) {
                circle_set.insert(second.clone());
            }
        }
        circle_set.remove(user);
        Vec::from_iter(circle_set)
    }

    fn count_circle_courses_without_own(
        &self,
        circle: Vec<String>,
        own: HashSet<String>,
    ) -> HashMap<String, u32> {
        let mut counted_courses = HashMap::new();
        for user in circle {
            for course in self.get_attended_courses_for_user(&user) {
                if own.contains(&course) {
                    continue;
                }
                let count = counted_courses.entry(course.clone()).or_insert(0u32);
                *count += 1;
            }
        }
        counted_courses
    }

    fn order_courses_by_count(counted_courses: HashMap<String, u32>) -> Vec<String> {
        let mut courses: Vec<(String, u32)> = counted_courses.into_iter().collect();
        courses.sort_by(|a, b| b.1.cmp(&a.1));
        courses.into_iter().map(|(course, _count)| course).collect()
    }

    pub fn get_ranked_courses(&self, user: &String) -> Vec<String> {
        let circle = self.get_friends_of_friends(user);
        let own: HashSet<String> = self
            .get_attended_courses_for_user(user)
            .into_iter()
            .collect();
        let counted_courses = self.count_circle_courses_without_own(circle, own);
        Network::order_courses_by_count(counted_courses)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn convert_map(input: HashMap<&str, Vec<&str>>) -> HashMap<String, Vec<String>> {
        input
            .into_iter()
            .map(|(k, v)| {
                (
                    k.to_string(),
                    v.into_iter().map(|s| s.to_string()).collect(),
                )
            })
            .collect()
    }

    fn create_network() -> Network {
        Network {
            friendships: convert_map(
                [
                    ("Jack", vec!["Jane", "John"]),
                    ("John", vec!["Alice", "Jack", "Jane"]),
                    ("Alice", vec!["Bob"]),
                    ("Bob", vec!["Alice", "Jane"]),
                    ("Jane", vec!["Jack", "John"]),
                    ("Loner", vec![]),
                ]
                .into(),
            ),
            attendances: convert_map(
                [
                    ("Jack", vec!["Science 1"]),
                    ("John", vec!["Science 1", "Arts"]),
                    ("Alice", vec!["Science 1", "Science 2", "Arts"]),
                    ("Bob", vec!["Arts", "Sports"]),
                    ("Jane", vec!["Science 1", "Science 2", "Arts", "Sports"]),
                    ("Loner", vec!["Philosophy"]),
                ]
                .into(),
            ),
        }
    }

    fn create_other() -> Network {
        Network {
            friendships: convert_map(
                [
                    ("Student1", vec!["Student2", "Student3"]),
                    ("Student2", vec!["Student1", "Student3"]),
                    ("Student3", vec!["Student1", "Student2"]),
                ]
                .into(),
            ),
            attendances: convert_map(
                [
                    ("Student1", vec!["Course1"]),
                    ("Student2", vec!["Course2"]),
                    ("Student3", vec!["Course3"]),
                ]
                .into(),
            ),
        }
    }

    fn generic_test(result: Vec<String>, expected: Vec<String>) {
        assert_eq!(result.len(), expected.len());
        for i in 0..expected.len() {
            assert_eq!(result[i], expected[i]);
        }
    }

    #[test]
    fn test_loner() {
        let network = create_network();
        let expected = vec![];
        let result = network.get_ranked_courses(&"Loner".to_string());
        generic_test(result, expected);
    }

    #[test]
    fn test_jack() {
        let network = create_network();
        let expected = vec![
            "Arts".to_string(),
            "Science 2".to_string(),
            "Sports".to_string(),
        ];
        let result = network.get_ranked_courses(&"Jack".to_string());
        generic_test(result, expected);
    }

    #[test]
    fn test_jane() {
        let network = create_network();
        let expected = vec![];
        let result = network.get_ranked_courses(&"Jane".to_string());
        generic_test(result, expected);
    }

    #[test]
    fn test_leftovers() {
        let network = create_network();
        let other = create_other();
        let result1: Vec<String> = network.get_attended_courses_for_user(&"".to_string());
        assert_eq!(result1.len(), 0);
        let result2: Vec<String> = network.get_direct_friends_for_user(&"".to_string());
        assert_eq!(result2.len(), 0);
        let result3: Vec<String> = other.get_ranked_courses(&"Student1".to_string());
        assert_eq!(result3.len(), 2);
    }
}
