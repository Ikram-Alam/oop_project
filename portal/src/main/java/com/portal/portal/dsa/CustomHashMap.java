package com.portal.portal.dsa;

/**
 * Custom implementation of HashMap using Chaining for collision resolution
 * DSA Concept: Hash Table with Chaining
 * Time Complexity:
 *   - Put: O(1) average, O(n) worst case
 *   - Get: O(1) average, O(n) worst case
 *   - Remove: O(1) average, O(n) worst case
 * Space Complexity: O(n + m) where n is entries and m is bucket size
 */
public class CustomHashMap<K, V> {
    
    @SuppressWarnings("hiding")
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    
    private Entry<K, V>[] buckets;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.capacity = INITIAL_CAPACITY;
        this.buckets = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }
    
    // Hash function
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }
    
    // Put key-value pair
    public void put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key);
        Entry<K, V> entry = buckets[index];
        
        // Check if key already exists
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        
        // Add new entry at the beginning of the chain
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }
    
    // Get value by key
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = buckets[index];
        
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }
    
    // Get all keys
    public CustomLinkedList<K> keySet() {
        CustomLinkedList<K> keys = new CustomLinkedList<>();
        for (Entry<K, V> bucket : buckets) {
            Entry<K, V> entry = bucket;
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }
    
    // Resize hash map when load factor is exceeded
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Entry<K, V>[] oldBuckets = buckets;
        buckets = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
        
        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> entry = bucket;
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}
