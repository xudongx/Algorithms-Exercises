package my.algs4.exercise;

public class Chap2_1 {
    public static void main(String[] args) {

    }
}

class SortBase {
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.println(item);
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void sort(Comparable[] a) {
    }
}

class SelectionSort extends SortBase {
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[minIdx])) minIdx = j;
            }
            exch(a, i, minIdx);
        }
    }
}

class InsertionSort extends SortBase {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int curIdx = i;
            for (int j = i - 1; j > 0; j--) {  // my implementation
                if (less(a[curIdx--], a[j]))
                    exch(a, curIdx, j);
            }
        }
    }

    public static void sort2(Comparable[] a) {  // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++) {  // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);  // full exchange is expensive, better solution below.
        }
    }

    public static void sort_better(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];
            int j = i;
            for (; j > 0 && less(temp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }
}

class ShellSort extends SortBase {
    public static void sort(Comparable[] a) {
        int[] seq = {1, 4, 13, 40, 121, 364, 1093};
        int N = a.length;
        int h = 1;
        for (int i = 0; i < seq.length && seq[i] < (N / 3); i++) {
            h = seq[i];
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void sort_book(Comparable[] a) {  // Sort a[] into increasing order.
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        while (h >= 1) {  // h-sort the array.
            for (int i = h; i < N; i++) {  // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }
}