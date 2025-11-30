package com.portal.portal.service;

import com.portal.portal.dsa.BinarySearchTree;
import com.portal.portal.dsa.CustomLinkedList;
import com.portal.portal.model.Result;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * Student Ranking Service using Binary Search Tree
 * DSA Concept: BST for efficient search and sorted traversal
 * Use Case: Ranking students based on marks with efficient search
 */
@Service
public class StudentRankingService {
    
    private BinarySearchTree<Result> rankingTree;
    
    public StudentRankingService() {
        // Comparator: Sort by marks (higher marks = higher rank)
        Comparator<Result> comparator = (r1, r2) -> {
            int marksCmp = Integer.compare(r1.getMarks(), r2.getMarks());
            if (marksCmp != 0) {
                return marksCmp;
            }
            // If marks are equal, sort by roll number
            return r1.getStudent().getRollNumber().compareTo(r2.getStudent().getRollNumber());
        };
        this.rankingTree = new BinarySearchTree<>(comparator);
    }
    
    /**
     * Insert result into ranking tree
     * Time Complexity: O(log n) average, O(n) worst case
     */
    public void insertResult(Result result) {
        rankingTree.insert(result);
    }
    
    /**
     * Get all results in sorted order (by marks)
     * Time Complexity: O(n) for inorder traversal
     */
    public CustomLinkedList<Result> getRankedResults() {
        return rankingTree.inorderTraversal();
    }
    
    /**
     * Search for a specific result
     * Time Complexity: O(log n) average
     */
    public boolean searchResult(Result result) {
        return rankingTree.search(result);
    }
    
    /**
     * Get lowest scoring result
     * Time Complexity: O(log n)
     */
    public Result getLowestScore() {
        return rankingTree.findMin();
    }
    
    /**
     * Get highest scoring result
     * Time Complexity: O(log n)
     */
    public Result getHighestScore() {
        return rankingTree.findMax();
    }
    
    /**
     * Get total number of results in tree
     */
    public int getTotalResults() {
        return rankingTree.size();
    }
    
    /**
     * Check if tree is empty
     */
    public boolean isEmpty() {
        return rankingTree.isEmpty();
    }
}
