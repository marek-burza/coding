// https://www.hackerrank.com/challenges/taum-and-bday

use crate::hackerrank::tester::{read_input, write_and_check_output};

pub fn taum_bday(b: i32, w: i32, bc: i32, wc: i32, z: i32) -> i64 {
    let x_adjusted = if bc <= wc + z {
        bc as i64
    } else {
        (wc + z) as i64
    };
    let y_adjusted = if wc <= bc + z {
        wc as i64
    } else {
        (bc + z) as i64
    };
    b as i64 * x_adjusted + w as i64 * y_adjusted
}

pub fn main_tested(name: &str) {
    let origin = file!();
    let mut input = read_input(origin, name);
    let t = input.next().unwrap().trim().parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    for _ in 0..t {
        let first_line: Vec<i32> = input
            .next()
            .unwrap()
            .trim_end()
            .split(' ')
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
        let b = first_line[0];
        let w = first_line[1];
        let second_line: Vec<i32> = input
            .next()
            .unwrap()
            .trim_end()
            .split(' ')
            .map(|s| s.to_string().parse::<i32>().unwrap())
            .collect();
        let bc = second_line[0];
        let wc = second_line[1];
        let z = second_line[2];
        results.push(taum_bday(b, w, bc, wc, z).to_string());
    }
    write_and_check_output(origin, name, &results);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        main_tested("_example");
    }

    #[test]
    fn test05() {
        main_tested("05");
    }
}
