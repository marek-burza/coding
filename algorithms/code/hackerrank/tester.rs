use std::fs::{read_to_string, remove_file, write};
use std::io::{self, Write};
use std::path::Path;
use std::process::Command;
use std::vec;

pub fn locate_test_file(origin: &str, prefix: &str, name: &str) -> String {
    let path = Path::new(&origin);
    let mut result = "".to_string();
    if let Some(parent) = path.parent() {
        let test_file_name = format!("{}{}.txt", prefix, name).to_string();
        result = parent.join(test_file_name).to_str().unwrap().to_string();
    }
    result
}

pub fn write_lines(origin: &str, name: &str, lines: &[String]) {
    let results_file_path = locate_test_file(origin, "results", name);
    let contents = lines.join("\n") + "\n";
    write(results_file_path, contents).ok();
}

pub fn read_lines(origin: &str, name: &str) -> Vec<String> {
    let input_file_path = locate_test_file(origin, "input", name);
    read_to_string(input_file_path)
        .unwrap()
        .lines()
        .map(String::from)
        .collect()
}

pub fn diff_check(origin: &str, name: &str) {
    let output_file_path = locate_test_file(origin, "output", name);
    let results_file_path = locate_test_file(origin, "results", name);
    let diff = Command::new("diff")
        .arg(output_file_path)
        .arg(results_file_path.clone())
        .output()
        .expect("Failed to execute diff!");
    remove_file(results_file_path).ok();
    let success = diff.status.success();
    io::stdout().write_all(&diff.stdout).unwrap();
    io::stderr().write_all(&diff.stderr).unwrap();
    assert!(success);
}

pub fn read_input(origin: &str, name: &str) -> vec::IntoIter<String> {
    read_lines(origin, name).into_iter()
}

pub fn read_input_improved(origin: &str, name: &str) -> Vec<Vec<String>> {
    read_lines(origin, name)
        .iter()
        .map(|line| line.split_whitespace().map(|s| s.to_string()).collect())
        .collect()
}

pub fn write_and_check_output(origin: &str, name: &str, results: &[String]) {
    write_lines(origin, name, results);
    diff_check(origin, name);
}
