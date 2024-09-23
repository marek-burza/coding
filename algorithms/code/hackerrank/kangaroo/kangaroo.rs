// https://www.hackerrank.com/challenges/kangaroo

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn kangaroo(x1: i32, v1: i32, x2: i32, v2: i32) -> String {
    if v1 == v2 {
        if x1 == x2 {
            return "YES".to_string();
        }
        return "NO".to_string();
    }
    if (x2 - x1) % (v2 - v1) == 0 && ((x2 - x1) as f32 / ((v2 - v1) as f32)) < 0.0 {
        return "YES".to_string();
    }
    "NO".to_string()
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let mut results: Vec<String> = Vec::new();
    let first_line: Vec<String> = input
        .next()
        .unwrap()
        .split(' ')
        .map(|s| s.to_string())
        .collect();
    let x1 = first_line[0].trim().parse::<i32>().unwrap();
    let v1 = first_line[1].trim().parse::<i32>().unwrap();
    let x2 = first_line[2].trim().parse::<i32>().unwrap();
    let v2 = first_line[3].trim().parse::<i32>().unwrap();
    results.push(kangaroo(x1, v1, x2, v2));
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_0() {
        main_tested("_example_0");
    }

    #[test]
    fn test_example_1() {
        main_tested("_example_1");
    }

    #[test]
    fn test_same() {
        assert_eq!("YES", kangaroo(1, 2, 1, 2));
    }

    #[test]
    fn test00() {
        main_tested("00");
    }

    #[test]
    fn test01() {
        main_tested("01");
    }

    #[test]
    fn test02() {
        main_tested("02");
    }

    #[test]
    fn test03() {
        main_tested("03");
    }

    #[test]
    fn test04() {
        main_tested("04");
    }

    #[test]
    fn test05() {
        main_tested("05");
    }

    #[test]
    fn test06() {
        main_tested("06");
    }

    #[test]
    fn test07() {
        main_tested("07");
    }

    #[test]
    fn test08() {
        main_tested("08");
    }

    #[test]
    fn test09() {
        main_tested("09");
    }

    #[test]
    fn test10() {
        main_tested("10");
    }

    #[test]
    fn test11() {
        main_tested("11");
    }

    #[test]
    fn test12() {
        main_tested("12");
    }

    #[test]
    fn test13() {
        main_tested("13");
    }

    #[test]
    fn test14() {
        main_tested("14");
    }

    #[test]
    fn test15() {
        main_tested("15");
    }

    #[test]
    fn test16() {
        main_tested("16");
    }

    #[test]
    fn test17() {
        main_tested("17");
    }

    #[test]
    fn test18() {
        main_tested("18");
    }

    #[test]
    fn test19() {
        main_tested("19");
    }

    #[test]
    fn test20() {
        main_tested("20");
    }

    #[test]
    fn test21() {
        main_tested("21");
    }

    #[test]
    fn test22() {
        main_tested("22");
    }

    #[test]
    fn test23() {
        main_tested("23");
    }

    #[test]
    fn test24() {
        main_tested("24");
    }

    #[test]
    fn test25() {
        main_tested("25");
    }

    #[test]
    fn test26() {
        main_tested("26");
    }

    #[test]
    fn test27() {
        main_tested("27");
    }

    #[test]
    fn test28() {
        main_tested("28");
    }

    #[test]
    fn test29() {
        main_tested("29");
    }
}
