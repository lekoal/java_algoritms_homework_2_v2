package ru.geekbrains.java_algoritms_homework_2_v2;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private T[] temp;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        if (size >= list.length) throw new IllegalArgumentException("array size cant be larger then length");
        list[size] = item;
        size++;
    }

    public void add(int index, T item) {
        if (size >= list.length) throw new IllegalArgumentException("array size cant be larger then length");
        if (index < 0 || index > list.length - 1) throw new IllegalArgumentException("wrong index size. It cant be <= 0 or > array size");
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    private int index(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(T item) {
        return index(item);
    }

    public void remove(int index) {
        if (index < 0 || index > list.length - 1) throw new IllegalArgumentException("wrong index size. It cant be <= 0 or > array size");
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
    }

    public boolean remove(T item) {
        int i = index(item);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index > list.length - 1) throw new IllegalArgumentException("wrong index size. It cant be <= 0 or > array size");
        return list[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void selectionSort() {
        long startTime = System.currentTimeMillis(); // счётчик времени выполнения метода
        int iMin;
        for (int i = 0; i < size - 1; i++) {
            iMin = i;
            for (int j = i + 1; j < size; j++) {
                if (less(list[j], list[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Selection sort execution time: " + (stopTime - startTime) + "ms"); // вывод времени выполнения метода в консоль
    }

    public void insertionSort() {
        long startTime = System.currentTimeMillis();
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];
            while (j > 0 && less(key, list[j - 1])) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Insertion sort execution time: " + (stopTime - startTime) + "ms");
    }

    public void bubbleSort() {
        long startTime = System.currentTimeMillis();
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                }
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Bubble sort execution time: " + (stopTime - startTime) + "ms");
    }

    public void bubbleSortOptimized() {
        long startTime = System.currentTimeMillis();
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Bubble optimized sort execution time: " + (stopTime - startTime) + "ms");
    }

    public void arrClone(MyArrayList<Integer> arr1, MyArrayList<Integer> arr2) { // метод клонирования массива
        for (int i = 0; i < arr1.size() - 1; i++) {
            arr2.add(i, arr1.get(i));
        }
    }

    public void checkSize() { // метод проверки размера массива
        System.out.println("array size is: " + list.length);
    }

    public void enlargeArray() { // метод увеличения массива в 1,5 + 1 раз

        temp = (T[]) new Comparable[(int)(list.length * 1.5 + 1)];
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[i];
        }
        double capacity = (temp.length * 1.5 + 1) - temp.length;
        for (int i = temp.length; i < (int) capacity; i++) {
            size++;
            temp[i] = null;
        }
        list = temp;
    }

}
