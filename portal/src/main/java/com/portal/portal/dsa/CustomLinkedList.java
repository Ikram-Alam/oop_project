package com.portal.portal.dsa;

/**
 * Custom LinkedList Implementation
 * DSA Concept: Singly Linked List
 * Use Case: Dynamic list storage for questions, results, etc.
 */
public class CustomLinkedList<T> {
    
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Add element to end of list
     * Time Complexity: O(1)
     */
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    /**
     * Get element at specific index
     * Time Complexity: O(n)
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Get size of list
     * Time Complexity: O(1)
     */
    public int size() {
        return size;
    }
    
    /**
     * Check if list is empty
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Convert to array
     * Time Complexity: O(n)
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
}
