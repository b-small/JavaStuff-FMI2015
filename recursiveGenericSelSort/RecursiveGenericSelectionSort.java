package recursiveGenericSelSort;

/**
 *
 * @author bas
 */
public class RecursiveGenericSelectionSort {

    public static <T extends Comparable<T>> void selectionSort(T[] array, int startIndex) {
        if (startIndex >= array.length - 1) {
            return;
        }

        int minIndex = startIndex;

        for (int i = startIndex + 1; i < array.length; i++) {
            if (array[i].compareTo(array[minIndex]) < 0) {
                minIndex = i;
            }
        }

        swap(array, startIndex, minIndex);
        selectionSort(array, startIndex + 1);
    }

    public static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
