package com.example.demo.sort;

import java.util.Arrays;

/**
 * Временная сложность O(n^2)
 * Память O(1)
 * Преимущество в малом колличестве модификаций массива
 */
public class SelectionSort implements SortAlgorithm {

    @Override
    public void sort(int[] array) {

        // опциональный индикатор того, сколько раз массив модифицировался
        int modificationArrayCount = 0;

        // поле для индикации оптимизации алгоритма.
        // показывает сколько всего итараций сделано
        int iterCount = 0;

        for (int i = 0; i < array.length; i++) {

            // предполагаем, что элемент по индексу i самый маленький
            int lowest = i;
            for (int j = i + 1; j < array.length; j++) {

                // если предполагаемый наименьший элемент не является наименьшим, то ...
                if (array[lowest] > array[j]) {

                    // ... обновляем индекс наименьшего элемента
                    lowest = j;
                }
                iterCount++;
            }

            // проверяем, что наименьший элемент изменился
            // если это так, то меняем местами наименьший элемент
            // и элемент по индексу i
            if (lowest != i) {
                int temp = array[lowest];
                array[lowest] = array[i];
                array[i] = temp;

                // при свопе мы два раза меняем содержимое массива
                modificationArrayCount += 2;
            }
            iterCount++;
            System.out.println("Iteration " + i + " " + Arrays.toString(array));
        }
        System.out.println("Sum iter = " + iterCount);
        System.out.println("Array was modified = " + modificationArrayCount + " times");
    }
}