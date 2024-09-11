// https://leetcode.com/problems/container-with-most-water/

pub struct Solution;

impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut maximum = 0;
        let mut low = 0;
        let mut high = height.len() - 1;
        while low < high {
            let top = height[low].min(height[high]);
            maximum = maximum.max(top * (high as i32 - low as i32));
            if height[low] <= height[high] {
                low += 1;
            } else {
                high -= 1;
            }
        }
        maximum
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1_2_1() {
        let test = vec![1, 2, 1];
        assert_eq!(Solution::max_area(test), 2);
    }

    #[test]
    fn test_1_3_5_2() {
        let test = vec![1, 3, 5, 2];
        assert_eq!(Solution::max_area(test), 4);
    }

    #[test]
    fn test_oversized() {
        let mut test = vec![];
        for i in 0..15000 {
            test.push(i + 1);
        }
        assert_eq!(Solution::max_area(test), 56250000);
    }

    #[test]
    fn test_huh() {
        let test = vec![1, 2, 1, 15, 15, 1, 2, 1];
        assert_eq!(Solution::max_area(test), 15);
    }
}
