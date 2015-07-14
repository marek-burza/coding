package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/lru-cache/
 */
public class LC146LRUCache {
    private class Item {
        public int key;
        public int value;

        public Item(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<Item> list = new ArrayList<>();
    private HashMap<Integer, Item> map = new HashMap<>();
    private int capacity;

    public LC146LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Item item = map.get(key);
        if (item == null) {
            return -1;
        } else {
            bump(item);
            return item.value;
        }
    }

    public void set(int key, int value) {
        Item item = map.get(key);
        if (item == null) {
            if (list.size() >= capacity) {
                reuse(key, value);
            } else {
                append(key, value);
            }
        } else {
            replace(item, value);
        }
    }

    private void replace(Item item, int value) {
        item.value = value;
        bump(item);
    }

    private void reuse(int key, int value) {
        Item item = list.get(list.size() - 1);
        map.remove(item.key);
        item.key = key;
        item.value = value;
        map.put(key, item);
        bump(item);
    }

    private void append(int key, int value) {
        Item item = new Item(key, value);
        list.add(0, item);
        map.put(key, item);
    }

    private void bump(Item item) {
        list.remove(item);
        list.add(0, item);
    }

    public static void main(String[] arguments) {
        LC146LRUCache cache = new LC146LRUCache(2);
        cache.set(2, 1);
        cache.set(1, 1);
        System.out.println(cache.get(2));
        cache.set(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
