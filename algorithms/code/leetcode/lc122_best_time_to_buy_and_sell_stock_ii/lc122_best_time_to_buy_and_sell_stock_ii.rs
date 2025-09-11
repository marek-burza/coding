// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// #medium

pub struct Solution;

impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        if prices.is_empty() {
            return 0;
        }
        let mut profit = 0;
        let mut previous = prices[0];
        for value in prices {
            if value > previous {
                profit += value - previous;
            }
            previous = value;
        }
        profit
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert_eq!(Solution::max_profit(vec![]), 0);
    }

    #[test]
    fn test_example() {
        assert_eq!(
            Solution::max_profit(vec![1, 2, 1, 3, 2, 5, 0, 10, 9, 6, 3]),
            16,
        );
    }
}
