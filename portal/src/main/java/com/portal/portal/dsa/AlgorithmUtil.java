package com.portal.portal.dsa;

import java.util.Comparator;
import java.util.List;

/**
 * Collection of sorting and searching algorithms
 * DSA Concepts: Merge Sort
 */
public class AlgorithmUtil {
    
    /**
     * Merge Sort Algorithm
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        mergeSortHelper(list, 0, list.size() - 1, comparator);
    }
    
    private static <T> void mergeSortHelper(List<T> list, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortHelper(list, left, mid, comparator);
            mergeSortHelper(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T> void merge(List<T> list, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = list.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = list.get(mid + 1 + j);
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (comparator.compare((T) leftArray[i], (T) rightArray[j]) <= 0) {
                list.set(k, (T) leftArray[i]);
                i++;
            } else {
                list.set(k, (T) rightArray[j]);
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            list.set(k, (T) leftArray[i]);
            i++;
            k++;
        }
        
        while (j < n2) {
            list.set(k, (T) rightArray[j]);
            j++;
            k++;
        }
    }
}
