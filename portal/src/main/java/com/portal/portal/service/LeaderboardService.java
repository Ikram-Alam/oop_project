package com.portal.portal.service;

import com.portal.portal.dsa.CustomPriorityQueue;
import com.portal.portal.dsa.AlgorithmUtil;
import com.portal.portal.model.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Leaderboard Service using Priority Queue (Max Heap)
 * DSA Concept: Binary Heap for efficient top-k queries
 * Use Case: Maintaining real-time leaderboard of top performers
 */
@Service
public class LeaderboardService {
    
    private CustomPriorityQueue<Result> topPerformers;
    private List<Result> allResults;
    
    public LeaderboardService() {
        // Max heap comparator (higher marks = higher priority)
        Comparator<Result> marksComparator = (r1, r2) -> Integer.compare(r1.getMarks(), r2.getMarks());
        this.topPerformers = new CustomPriorityQueue<>(marksComparator);
        this.allResults = new ArrayList<>();
    }
    
    /**
     * Add result to leaderboard
     * Time Complexity: O(log n) for heap insertion
     */
    public void addResult(Result result) {
        topPerformers.add(result);
        allResults.add(result);
    }
    
    /**
     * Get top performer (highest marks)
     * Time Complexity: O(1)
     */
    public Result getTopPerformer() {
        return topPerformers.peek();
    }
    
    /**
     * Get top N performers using Priority Queue
     * Time Complexity: O(n log n)
     */
    public List<Result> getTopNPerformers(int n) {
        List<Result> topN = new ArrayList<>();
        Object[] allPerformers = topPerformers.toArray();
        
        // Sort using merge sort for stability
        List<Result> sortedResults = new ArrayList<>();
        for (Object obj : allPerformers) {
            sortedResults.add((Result) obj);
        }
        
        AlgorithmUtil.mergeSort(sortedResults, (r1, r2) -> Integer.compare(r2.getMarks(), r1.getMarks()));
        
        int limit = Math.min(n, sortedResults.size());
        for (int i = 0; i < limit; i++) {
            topN.add(sortedResults.get(i));
        }
        
        return topN;
    }
    
    /**
     * Get average marks
     */
    public double getAverageMarks() {
        if (allResults.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Result result : allResults) {
            total += result.getMarks();
        }
        return (double) total / allResults.size();
    }
    
    /**
     * Get total number of results
     */
    public int getTotalResults() {
        return allResults.size();
    }
}
