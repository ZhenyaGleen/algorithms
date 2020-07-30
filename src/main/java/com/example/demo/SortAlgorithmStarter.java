package com.example.demo;


import com.example.demo.sort.QuickSort;
import com.example.demo.sort.SortAlgorithm;

public class SortAlgorithmStarter {

    public static void main(String[] args) {
        int[] unsortedArray = {8, 3, 4, 2, 7, 1, 9, 0, 0, 9, 7, 9, 9, 0};
        int[] sortedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        SortAlgorithm sortAlgorithm = new QuickSort(QuickSort.PartitionScheme.LOMUTO);
        sortAlgorithm.sort(unsortedArray);

    }
}