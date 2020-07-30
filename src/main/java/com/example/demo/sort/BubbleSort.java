package com.example.demo.sort;

import java.util.Arrays;

/**
 * Временная сложность O(n^2)
 * Память O(1)
*/
public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] array) {

        // опциональный индикатор того, сколько раз массив модифицировался
        int modificationArrayCount = 0;

        // поле для индикации оптимизации алгоритма.
        // показывает сколько всего итараций сделано
        int iterCount = 0;

        // флаг указывающий произошел swap после последней итерации
        // или нет. Если нет, то выходим из while,
        // если да, то повторяем цикл, пока не отсортируем (пока swap'a не будет)
        boolean isSorted = false;

        // поле для оптимизаци алгоритма. Индицирует сколько элементов отсортировано,
        // чтобы отсортированные элементы не сравнивать в следующих итерациях
        // опциональная переменная
        int sortedItems = 0;

        while (!isSorted) {

            // предполагаем, что массив отсортирован
            isSorted = true;

            for (int j = 1; j < array.length - sortedItems; j++) {

                // проверяем больше ли предыидущий элемент, чем текущий
                // если да, то меняем их местами
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    // меняем флаг на false, массив оказался не отсортированным
                    isSorted = false;
                    // при свопе мы два раза меняем содержимое массива
                    modificationArrayCount += 2;
                }
                // увеличиваем кол-во итераций на единицу
                iterCount++;
            }

            // первы проход по массиву завершен и наибольший элемент
            // находится в хвосте массива.
            // увеличиваем это переменную на единицу, чтобы
            // уменьшить кол-во следующих итераций в цикле for на + единицу,
            // чтобы не пробегаться в следующие разы по сортированной части массива
            sortedItems++;

            // увеличиваем кол-во итераций на единицу
            iterCount++;

            // опционально, для визуализации проиходящего в массиве
            // после одного полного прохода по массиву
            System.out.println("Iteration " + sortedItems + " " + Arrays.toString(array));
        }
        // сумма всех итераций в алгоритме
        System.out.println("Sum iter = " + iterCount);
        // сумма модификаций массива
        System.out.println("Array was modified = " + modificationArrayCount + " times");
    }
}