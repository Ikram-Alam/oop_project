package com.portal.portal.dsa;

import java.util.Comparator;

/**
 * Custom implementation of Priority Queue using Max Heap
 * DSA Concept: Binary Heap (Max Heap)
 * Time Complexity:
 *   - Insert: O(log n)
 *   - Extract Max: O(log n)
 *   - Peek: O(1)
 * Space Complexity: O(n)
 */
public class CustomPriorityQueue<T> {
    
    private Object[] heap;
    private int size;
    private int capacity;
    private Comparator<T> comparator;
    
    private static final int INITIAL_CAPACITY = 10;
    
    public CustomPriorityQueue(Comparator<T> comparator) {
        this.capacity = INITIAL_CAPACITY;
        this.heap = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }
    
    // Add element to heap
    public void add(T element) {
        if (size == capacity) {
            resize();
        }
        
        heap[size] = element;
        heapifyUp(size);
        size++;
    }
    
    // Peek at maximum element without removing
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) heap[0];
    }
    
    // Heapify up (for insertion)
    @SuppressWarnings("unchecked")
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        
        while (index > 0 && comparator.compare((T) heap[index], (T) heap[parent]) > 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }
    
    // Heapify down (for deletion)
    @SuppressWarnings("unchecked")
    private void heapifyDown(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if (left < size && comparator.compare((T) heap[left], (T) heap[largest]) > 0) {
            largest = left;
        }
        
        if (right < size && comparator.compare((T) heap[right], (T) heap[largest]) > 0) {
            largest = right;
        }
        
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }
    
    // Swap two elements
    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // Resize array when capacity is reached
    private void resize() {
        capacity *= 2;
        Object[] newHeap = new Object[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    // Get all elements as array (unordered)
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(heap, 0, result, 0, size);
        return result;
    }
}
