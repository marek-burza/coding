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
    let input = read_input(origin, name);
    let t = input[0][0].parse::<i32>().unwrap();
    let mut results: Vec<String> = Vec::new();
    let mut offset = 1;
    for _ in 0..t {
        let b = input[offset][0].parse::<i32>().unwrap();
        let w = input[offset][1].parse::<i32>().unwrap();
        let bc = input[offset + 1][0].parse::<i32>().unwrap();
        let wc = input[offset + 1][1].parse::<i32>().unwrap();
        let z = input[offset + 1][2].parse::<i32>().unwrap();
        offset += 2;
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
