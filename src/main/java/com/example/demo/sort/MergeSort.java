package com.example.demo.sort;

import java.util.Arrays;

/**
 * Реализация с рекурсией
 * Временная сложность O(nlog n)
 * Память O(n) -> https://stackoverflow.com/questions/10342890/merge-sort-time-and-space-complexity
 * #:~:text=MergeSort%20time%20Complexity%20is%20O,complexity%20is%20O(nlgn).
 */
public class MergeSort implements SortAlgorithm {

    // опциональное поле для отображения общей дополнительной памяти за время работы всего алгоритма
    private int additionalMemory = 0;

    @Override
    public void sort(int[] array) {

        // левый край/индекс массива
        int left = 0;

        // правый край/индекс массива
        int right = array.length - 1;

        // вызов рекурсивной функции.
        mergeSort(array, left, right);
        // результат
        System.out.println(Arrays.toString(array));
        System.out.println("Additional memory = " + additionalMemory);
    }

    // Передаем сортируемый массив или подмассив и его правый и левый края
    private void mergeSort(int[] array, int left, int right) {

        // выход из рекурсии, если левый индекс стал больше или равен правому
        if (left >= right) return;

        // вычисляем середину массива, по которой будем делить массив на два подмассива
        int middle = (left + right) / 2;

        // передаем сортируемый массив и левый и правый крайние индексы первого (левого) подмассива
        // то есть уходим в рекурсию, функция вызывает сама себя с границами первого подмассива
        mergeSort(array, left, middle);

        // передаем сортируемый массив и левый и правый крайние индексы второго (правого) подмассива
        // то есть уходим в рекурсию, функция вызывает сама себя с границами второго подмассива
        mergeSort(array, middle + 1, right);

        // функция, которая объединит результат выполнения двух вышеопиисанных функций
        merge(array, left, middle, right);
    }
    /**
     * Функция для объединения и сортировки двух подмассивов.
     * @param array сортируемый массив
     * @param left крайний левый индекс левого подмассива
     * @param middle середина между левым и правым подмассивом
     * @param right крайний правый индекс правого подмассива
     */
    private void merge(int[] array, int left, int middle, int right) {

        // выделяем память под левый временный подмассив
        int[] leftArray = Arrays.copyOfRange(array, left, middle + 1);

        // выделяем память под правый временный подмассив
        // почему последний индекс + 1 смотрим документацию к методу copyOfRange
        int[] rightArray = Arrays.copyOfRange(array, middle + 1, right + 1);

        // опционально. Считаем сколько памяти (ячеек) выделется под временные массивы
        additionalMemory += (leftArray.length + rightArray.length);

        // указатель на последнюю ячейку левого временный массива
        int indexLeft = 0;

        // указатель на последнюю ячейку правого временный массива
        int indexRight = 0;

        // указатель на индекс последней ячейки текущего подмассива в основном массиве
        int mainArrayIndex = left;

        // пока индексы левого и правого подмассива меньше их длинны, то крутим цикл
        while (indexLeft < leftArray.length && indexRight < rightArray.length) {

            // если елемент в левом временном массиве по текущему indexLeft меньше,
            // чем в правом временном массиве по текущему indexRight ...
            if (leftArray[indexLeft] < rightArray[indexRight]) {

                // ... в главный сортируемый массив по текущему индексу mainArrayIndex
                // записываем елемент из левого временного массива по индексу indexLeft
                array[mainArrayIndex] = leftArray[indexLeft];

                // перемещаем указатель на следующий неотсортированный элемент в главном массиве
                mainArrayIndex++;

                // перемещаем указатель на следующий элемент в левом временном массиве
                // так как текущий мы уже записали в главный массив
                indexLeft++;
            } else {

                // ... в главный сортируемый массив по текущему индексу mainArrayIndex
                // записываем елемент из правого временного массива по индексу indexRight
                array[mainArrayIndex] = rightArray[indexRight];

                // перемещаем указатель на следующий неотсортированный элемент в главном массиве
                mainArrayIndex++;

                // перемещаем указатель на следующий элемент в правом временном массиве
                // так как текущий мы уже записали в главный массив
                indexRight++;
            }
        }

        // пока длинна левого временного массива больше, чем значение указателя на последний элемент,
        // то перемещаем все оставшиеся элементы из левого временного массива в главный массив.
        // Например в случае, если у нас временные массивы разной длинны и в цикле выше мы
        // переписали все элементы из наименьшего временного массива, но во втором отсортированном массиве
        // елементы остались
        while (leftArray.length > indexLeft) {
            array[mainArrayIndex] = leftArray[indexLeft];
            mainArrayIndex++;
            indexLeft++;
        }

        // пока длинна правого временного массива больше, чем значение указателя на последний элемент,
        // то перемещаем все оставшиеся элементы из правого временного массива в главный массив.
        while (rightArray.length > indexRight) {
            array[mainArrayIndex] = rightArray[indexRight];
            mainArrayIndex++;
            indexRight++;
        }
    }
}