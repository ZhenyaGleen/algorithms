package com.example.demo.sort;

import java.util.Arrays;
/**
 * Временная сложность O(n^2)
 * Память O(1)
 * Быстрый при сортировке массива в 10-20 элементов
 */
public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] array) {

        // опциональный индикатор того, сколько раз массив модифицировался
        int modificationArrayCount = 0;

        // опционально. Поле для индикации оптимизации алгоритма.
        // показывает сколько всего итараций сделано
        int iterCount = 0;

        // массив до сортировки
        System.out.println("Iteration " + 0 + " " + Arrays.toString(array));

        for (int i = 0; i < array.length - 1; i++) {

            // указатель на элемент, который будет вставлять
            int current = array[i + 1];

            // индекс предыдущего элемента массива
            int previous = i;

            // крутим цикл пока предидущий индекс больше или равен нулю (чтобы не вылезти за границу массива)
            // и предыдущий элемент больше текущего (двигаем элементы массива вправо, пока не дойдем до элемента, который
            // меньше или равен текущему)
            while (previous >= 0 && array[previous] > current) {

                // перемещаем элемент вправо
                array[previous + 1] = array[previous];

                // уменьшаем индекс "предыдущего" элемента, чтобы перемещать указатель влево
                // (пока не упремся в элемент <= текущему или в границу массива)
                previous--;

                // увеличиваем счетчик итераций
                iterCount++;

                // увеличиваем счетчик модификаций массива
                modificationArrayCount++;
            }

            // если текущий элемент не равен предыщему, то модифицируем массив
            if (array[previous + 1] != current) {

                // вставляем элемент в свое место в массиве
                array[previous + 1] = current;

                // увеличиваем счетчик модификаций массива
                modificationArrayCount++;
            }

            // увеличиваем счетчик итераций
            iterCount++;
            System.out.println("Iteration " + (i + 1) + " " + Arrays.toString(array));
        }
        System.out.println("Sum iter = " + iterCount);
        System.out.println("Array was modified = " + modificationArrayCount + " times");
    }
}