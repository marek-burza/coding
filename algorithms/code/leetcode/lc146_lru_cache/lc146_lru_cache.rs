// https://leetcode.com/problems/lru-cache/
// #medium

use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;

struct LRUNode {
    key: i32,
    value: i32,
    preceding: Option<Rc<RefCell<LRUNode>>>,
    following: Option<Rc<RefCell<LRUNode>>>,
}

pub struct LRUCache {
    capacity: i32,
    lut: HashMap<i32, Rc<RefCell<LRUNode>>>,
    head: Rc<RefCell<LRUNode>>,
    tail: Rc<RefCell<LRUNode>>,
}

impl LRUCache {
    pub fn new(capacity: i32) -> Self {
        let head = Rc::new(RefCell::new(LRUNode {
            key: 0,
            value: 0,
            preceding: None,
            following: None,
        }));
        let tail = Rc::new(RefCell::new(LRUNode {
            key: 0,
            value: 0,
            preceding: None,
            following: None,
        }));
        head.as_ref().borrow_mut().following = Some(tail.clone());
        tail.as_ref().borrow_mut().preceding = Some(head.clone());
        Self {
            capacity,
            lut: HashMap::new(),
            head,
            tail,
        }
    }

    pub fn get(&mut self, key: i32) -> i32 {
        let mut value = -1;
        if self.lut.contains_key(&key) {
            value = self.remove(key);
            self.insert(key, value);
        }
        value
    }

    pub fn put(&mut self, key: i32, value: i32) {
        if self.lut.contains_key(&key) {
            self.remove(key);
        } else {
            while self.lut.len() as i32 >= self.capacity {
                let key = self
                    .tail
                    .as_ref()
                    .borrow_mut()
                    .preceding
                    .as_ref()
                    .unwrap()
                    .borrow_mut()
                    .key;
                self.remove(key);
            }
        }
        self.insert(key, value);
    }

    fn insert(&mut self, key: i32, value: i32) {
        let mut node = LRUNode {
            key,
            value,
            preceding: None,
            following: None,
        };
        node.preceding = Some(self.head.clone());
        node.following = self.head.clone().borrow().following.clone();
        let node = Rc::new(RefCell::new(node));
        node.as_ref()
            .borrow_mut()
            .preceding
            .as_ref()
            .unwrap()
            .borrow_mut()
            .following = Some(node.clone());
        node.as_ref()
            .borrow_mut()
            .following
            .as_ref()
            .unwrap()
            .borrow_mut()
            .preceding = Some(node.clone());
        self.lut.insert(key, node);
    }

    fn remove(&mut self, key: i32) -> i32 {
        let node = self.lut.remove(&key).unwrap();
        let node = node.borrow();
        node.preceding.as_ref().unwrap().borrow_mut().following = node.following.clone();
        node.following.as_ref().unwrap().borrow_mut().preceding = node.preceding.clone();
        node.value
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut cache = LRUCache::new(2);
        cache.put(2, 1);
        cache.put(1, 1);
        assert_eq!(cache.get(2), 1);
        cache.put(4, 1);
        assert_eq!(cache.get(1), -1);
        assert_eq!(cache.get(2), 1);
    }

    #[test]
    fn test_repeated_put_same() {
        let mut cache = LRUCache::new(2);
        cache.put(1, 1);
        cache.put(1, 1);
        assert_eq!(cache.get(1), 1);
    }
}
