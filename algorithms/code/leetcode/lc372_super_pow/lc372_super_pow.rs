// https://leetcode.com/problems/super-pow/
// #medium

pub struct Solution;

impl Solution {
    const MODULO_1337: i32 = 1337;

    fn find_power_loop(value: i32) -> Vec<i32> {
        let mut modulos = vec![];
        let mut lut = [false; Self::MODULO_1337 as usize];
        let mut modulo = value;
        while !lut[modulo as usize] {
            lut[modulo as usize] = true;
            modulos.push(modulo);
            modulo = (modulo * value) % Self::MODULO_1337;
        }
        modulos
    }

    fn modulo(dividend: &[i32], divisor: i32) -> i32 {
        let mut modulo = 0;
        for dividend_i in dividend {
            modulo = (modulo * 10 + dividend_i) % divisor;
        }
        modulo
    }

    pub fn super_pow(a: i32, b: Vec<i32>) -> i32 {
        // Assume: a = (1337 * n + m) where 0 <= m < 1337
        // Then: a^b mod 1337 = (1337 * n + m)^n mod 1337 == m^b mod 1337
        // This multiplication will cycle through certain "digits" of base 1337
        // You can search for the loop by iterating
        let m = a % Self::MODULO_1337;
        let modulos = Self::find_power_loop(m);
        // Get rid of loops from the power
        let length = modulos.len() as i32;
        let mut index = Self::modulo(&b, length);
        // Look-up the power modulo
        index = (index - 1 + length) % length;
        modulos[index as usize]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_23() {
        assert_eq!(Solution::super_pow(2, vec![3]), 8);
    }

    #[test]
    fn test_210() {
        assert_eq!(Solution::super_pow(2, vec![1, 0]), 1024);
    }
}
