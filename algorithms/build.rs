use std::collections::VecDeque;
use std::fs;
use std::path::PathBuf;

fn traverse(path: PathBuf) {
    let mut queue: VecDeque<PathBuf> = VecDeque::new();
    queue.push_back(path);
    while let Some(path) = queue.pop_front() {
        let mod_rs_path = format!("{}/mod.rs", path.to_str().unwrap());
        let mut mod_rs_content = String::new();
        if let Ok(entries) = fs::read_dir(&path) {
            for entry in entries.flatten() {
                if let Ok(file_type) = entry.file_type() {
                    let entry_path = entry.path();
                    let entry_name = entry_path.file_name().unwrap().to_str().unwrap();
                    let entry_stem = entry_path.file_stem().unwrap().to_str().unwrap();
                    if file_type.is_dir() {
                        queue.push_back(entry_path.clone());
                        mod_rs_content.push_str(&format!("pub mod {};\n", entry_stem));
                    } else if entry_name.ends_with("rs") & (entry_name != "mod.rs") {
                        mod_rs_content.push_str(&format!("pub mod {};\n", entry_stem));
                    }
                }
            }
        }
        // println!("{:?}\n{:?}", mod_rs_path, mod_rs_content);
        fs::write(&mod_rs_path, mod_rs_content).unwrap();
    }
}

fn main() {
    traverse(PathBuf::from("code"));
}
