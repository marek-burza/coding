// https://leetcode.com/problems/gas-station/
// #medium

pub struct Solution;

impl Solution {
    pub fn can_complete_circuit(gas: Vec<i32>, cost: Vec<i32>) -> i32 {
        let mut minimum = i32::MAX;
        let mut gauge = 0;
        let mut index = 0;
        for i in 0..=gas.len() {
            index = i % gas.len();
            gauge += gas[index] - cost[index];
            if gauge < minimum {
                minimum = gauge;
            }
        }
        let mut i = 0;
        while minimum < 0 && i < gas.len() {
            index = gas.len() - i - 1;
            minimum += gas[index] - cost[index];
            i += 1;
        }
        if minimum >= 0
        /* && i < gas.length */
        {
            index as i32
        } else {
            -1
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let gas = vec![99, 99, 99, 104];
        let cost = vec![100, 100, 100, 100];
        assert_eq!(Solution::can_complete_circuit(gas, cost), 3);
    }

    #[test]
    fn test_other() {
        let gas = vec![1, 2, 3, 4, 5];
        let cost = vec![3, 4, 5, 1, 2];
        assert_eq!(Solution::can_complete_circuit(gas, cost), 3);
    }

    #[test]
    fn test_another() {
        let gas = vec![2, 3, 4];
        let cost = vec![3, 4, 3];
        assert_eq!(Solution::can_complete_circuit(gas, cost), -1);
    }
}
