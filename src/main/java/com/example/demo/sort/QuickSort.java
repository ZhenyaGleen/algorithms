package com.example.demo.sort;

import java.util.Arrays;

public class QuickSort implements SortAlgorithm {

    public enum PartitionScheme {
        HOARE,
        LOMUTO
    }

    private PartitionScheme partitionScheme;

    public QuickSort(PartitionScheme scheme) {
        this.partitionScheme = scheme;
    }

    @Override
    public void sort(int[] array) {

        int leftIndex = 0;
        int rightIndex = array.length - 1;

        if (partitionScheme.equals(PartitionScheme.HOARE)) {
            quickSortHoare(array, leftIndex, rightIndex);
        } else {
            quickSortLomuto(array, leftIndex, rightIndex);
        }

        System.out.println(Arrays.toString(array));
    }

    /**
     * Реализация быстрой сортировки с пивотом в середине массива (Хоара)
     * @param array сортируемый массив
     * @param left левый край сортируемого массива
     * @param right правый край сортируемого массива
     */
    private void quickSortHoare(int[] array, int left, int right) {
        if (array.length == 0) return;

        if (left >= right) return;

        // указатель на левый последний неотсортированный элемент
        int leftIndex = left;

        // указатель на правый последний неотсортированный элемент
        int rightIndex = right;

        // индекс среднего элемента
        int mid = (leftIndex + rightIndex) / 2;

        // опорный элемент вокруг которого будем двигать меньшие и большие элементы
        int pivot = array[mid];

        // крутим цикл пока указатели не сойдутся в одной точке
        while (leftIndex <= rightIndex) {

            // пока элемент по индексу меньше, чем пивот - перемещаем указатель вперед
            // цель - найти элемент, который больше пивота с левой стороны
            // цикл остановится с указателем на него
            // далее мы этот элемент переместим в правую часть массива от пивота
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }

            // пока элемент по индексу меньше, чем пивот - перемещаем указатель назад (вправо)
            // цель - найти элемент, который меньше пивота с правой стороны
            while (array[rightIndex] > pivot) {
                rightIndex--;
            }

            // если индексы не сошлись, то меняем местами
            // элемент из левой части, который больше пивота
            // и элемент из правой части, который меньше пивота
            if (leftIndex <= rightIndex) {
                int temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;

                // перемещаем указатели на следующие элементы
                leftIndex++;
                rightIndex--;
            }
        }
        // если левый край сортируемого подмассива меньше, чем
        // указатель на правый элемент от пивота,
        // то есть что у нас в подмассиве хотябы один элемент остался
        if (left < rightIndex) {

            // рекурсивно вызываем функцию на подмассиве слева от пивота
            quickSortHoare(array, left, rightIndex);
        }

        if (right > leftIndex) {

            // рекурсивно вызываем функцию на подмассиве справа от пивота
            quickSortHoare(array, leftIndex, right);
        }
    }

    /**
     * Реализация быстрой сортировки с пивотом в конце массива (Ломуто)
     * @param array сортируемый массив
     * @param left левый край сортируемого массива
     * @param right правый край сортируемого массива
     */
    private void quickSortLomuto(int[] array, int left, int right) {

        // выход из рекурсии
        if (left >= right) return;

        // вычисляем индекс пивота в подмассиве
        int pivotIndex = partition(array, left, right);

        // рекурсивно вызываем саму себя с подмассивом слева от пивота
        quickSortLomuto(array, left, pivotIndex - 1);

        // рекурсивно вызываем саму себя с подмассивом справа от пивота
        quickSortLomuto(array, pivotIndex + 1, right);
    }

    /**
     * Ищем место пивота в подмассиве и возвращаем его новый индекс
     * @param array сортируемый массив
     * @param left левый край подмассива
     * @param right правый край подмассива
     * @return возвращаем вычисленный индекс пивота в массиве
     */
    private int partition(int[] array, int left, int right) {

        // индекс пивота на начала поиска его места
        // у нас он в конце подмассива
        int pivotIndex = right;

        // указатель на индекс подмассива
        // по которому мы ложим элементы меньше пивота
        int pointer = left;

        // проходимся по нужному подмассиву от левого края до пивота
        for (int i = left; i < right; i++) {

            // если текущий элемент меньше, чем пивот,
            // пермещаем его в левую часть массива по индексу указателя,
            // где у нас хранятся элементы меньше пивота
            if (array[i] < array[pivotIndex]) {

                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;

                // перемещаем указатель вправо
                pointer++;
            }
        }

        // После прохода цикла выше у нас в подмассиве слева до индекса указателя
        // все элементы меньше, чем пивот.
        // Следовательно, пивот мы должны перенести по индексу указателя.
        // Таким убразом у нас слева от пивота будут элементы меньше него,
        // а справа остануться элементы больше него
        int temp = array[pivotIndex];
        array[pivotIndex] = array[pointer];
        array[pointer] = temp;

        // возвращаем новый индекс пивота
        return pointer;
    }
}