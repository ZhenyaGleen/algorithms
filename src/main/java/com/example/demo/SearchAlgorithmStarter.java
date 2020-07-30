package com.example.demo;


import com.example.demo.search.BinarySearch;
import com.example.demo.search.SearchAlgorithm;

public class SearchAlgorithmStarter {

    public static void main(String[] args) {
        int[] sortedArray = {-3, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int searchNum = 0;

        SearchAlgorithm searchAlgorithm = new BinarySearch();
        int numIndex = searchAlgorithm.search(sortedArray, searchNum);

        if (numIndex < 0) {
            System.out.println("Element " + searchNum + " not found");
        } else {
            System.out.println("Element " + searchNum + " found at index " + numIndex);
        }
    }
}