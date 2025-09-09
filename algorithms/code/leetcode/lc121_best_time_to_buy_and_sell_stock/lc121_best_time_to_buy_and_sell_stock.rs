// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// #easy

pub struct Solution;

impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        if prices.len() <= 1 {
            return 0;
        }
        let mut mins = vec![0i32; prices.len()];
        mins[0] = prices[0];
        for i in 1..prices.len() {
            mins[i] = if prices[i] < mins[i - 1] {
                prices[i]
            } else {
                mins[i - 1]
            };
        }
        let mut profit = i32::MIN;
        let mut max = prices[prices.len() - 1];
        for i in (0..prices.len()).rev() {
            max = if prices[i] > max { prices[i] } else { max };
            let delta = max - mins[i];
            profit = if delta > profit { delta } else { profit };
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
    fn test_1() {
        assert_eq!(Solution::max_profit(vec![1]), 0);
    }

    #[test]
    fn test_example_1() {
        assert_eq!(Solution::max_profit(vec![7, 1, 5, 3, 6, 4]), 5);
    }

    #[test]
    fn test_example_2() {
        assert_eq!(Solution::max_profit(vec![7, 6, 4, 3, 1]), 0);
    }
}
