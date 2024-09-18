use std::collections::VecDeque;
use std::fs;
use std::path::PathBuf;

fn traverse(path: PathBuf) {
    let mut queue: VecDeque<PathBuf> = VecDeque::new();
    queue.push_back(path);
    while let Some(path) = queue.pop_front() {
        let mod_rs_path = format!("{}/mod.rs", path.to_str().unwrap());
        let mut mod_rs_content: Vec<String> = vec![];
        if let Ok(entries) = fs::read_dir(&path) {
            for entry in entries.flatten() {
                if let Ok(file_type) = entry.file_type() {
                    let entry_path = entry.path();
                    let entry_name = entry_path.file_name().unwrap().to_str().unwrap();
                    if entry_name.starts_with('.') || entry_name.contains('-') || entry_name.starts_with("rmeta") {
                        continue;
                    }
                    let entry_stem = entry_path.file_stem().unwrap().to_str().unwrap();
                    if file_type.is_dir() {
                        queue.push_back(entry_path.clone());
                        mod_rs_content.push(format!("pub mod {};\n", entry_stem));
                    } else if entry_name.ends_with("rs") & (entry_name != "mod.rs") {
                        mod_rs_content.push(format!("pub mod {};\n", entry_stem));
                    }
                }
            }
        }
        mod_rs_content.sort();
        let mut mod_rs_body = mod_rs_content.join("\n");
        if mod_rs_body.is_empty() {
            mod_rs_body = String::from("\n");
        }
        fs::write(mod_rs_path, mod_rs_body).unwrap();
    }
}

fn main() {
    traverse(PathBuf::from("algorithms/code"));
}
