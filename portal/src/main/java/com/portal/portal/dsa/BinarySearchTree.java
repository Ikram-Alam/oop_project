package com.portal.portal.dsa;

import java.util.Comparator;

/**
 * Custom implementation of Binary Search Tree
 * DSA Concept: Binary Search Tree
 * Time Complexity:
 *   - Insert: O(log n) average, O(n) worst case
 *   - Search: O(log n) average, O(n) worst case
 *   - Delete: O(log n) average, O(n) worst case
 *   - Inorder Traversal: O(n)
 * Space Complexity: O(n)
 */
public class BinarySearchTree<T> {
    
    private class Node {
        T data;
        Node left;
        Node right;
        
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    private int size;
    private Comparator<T> comparator;
    
    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }
    
    // Insert element
    public void insert(T data) {
        root = insertRec(root, data);
        size++;
    }
    
    private Node insertRec(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }
        
        if (comparator.compare(data, node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (comparator.compare(data, node.data) > 0) {
            node.right = insertRec(node.right, data);
        }
        
        return node;
    }
    
    // Search for element
    public boolean search(T data) {
        return searchRec(root, data);
    }
    
    private boolean searchRec(Node node, T data) {
        if (node == null) {
            return false;
        }
        
        int cmp = comparator.compare(data, node.data);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRec(node.left, data);
        } else {
            return searchRec(node.right, data);
        }
    }
    
    // Inorder traversal (returns sorted list)
    public CustomLinkedList<T> inorderTraversal() {
        CustomLinkedList<T> result = new CustomLinkedList<>();
        inorderRec(root, result);
        return result;
    }
    
    private void inorderRec(Node node, CustomLinkedList<T> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.data);
            inorderRec(node.right, result);
        }
    }
    
    // Find minimum value
    public T findMin() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }
    
    // Find maximum value
    public T findMax() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}
