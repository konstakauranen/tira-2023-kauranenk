package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      for (int index = fromIndex + 1; index < toIndex; index++) {
         T currentValue = array[index];
         int compareIndex = index-1;
         while (compareIndex >= fromIndex && array[compareIndex].compareTo(currentValue) > 0) {
            swap(array, compareIndex, compareIndex + 1);
            compareIndex--;
         }
         array[compareIndex+1] = currentValue;
      }
      
   }

   public static <T> void swap(T[] array, int first, int second) {
      T temporary = array[first];
      array[first] = array[second];
      array[second] = temporary;
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      insertionSort(array, 0, array.length, comparator);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      for (int index = fromIndex + 1; index < toIndex; index++) {
         T currentValue = array[index];
         int compareIndex = index-1;
         while (compareIndex >= fromIndex && comparator.compare(array[compareIndex], currentValue) > 0) {
            swap(array, compareIndex, compareIndex + 1);
            compareIndex--;
         }
         array[compareIndex+1] = currentValue;
      }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      // TODO: Student, implement this.
      reverse(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      // TODO: Student, implement this.
      int index2 = toIndex -1;
      while (index2 > fromIndex) {
         swap(array, fromIndex, index2);
         index2--;
         fromIndex++;
      }
   }




   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      int low = fromIndex;
      int high = toIndex - 1;
      int middle = 0;

      while (low <= high) {
         middle = low + (high - low) / 2;
         if (fromArray[middle].compareTo(aValue) == 0) {
            return middle;
         }

         if (fromArray[middle].compareTo(aValue) > 0) {
            high = middle - 1;
         } else {
            low = middle + 1;
         }
      }

      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      int low = fromIndex;
      int high = toIndex -1;
      int middle = 0;

      while (low <= high) {
         middle = low + (high - low) / 2;
         if (comparator.compare(fromArray[middle], aValue) == 0) {
            return middle;
         }
         if (comparator.compare(fromArray[middle], aValue) > 0) {
            high = middle -1;
         } else {
            low = middle + 1;
         }
      }

      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      quickSort(array, 0, array.length - 1);
   }

   public static <E extends Comparable<E>> void fastSort(E[] array, int fromIndex, int toIndex) {
      quickSort(array, fromIndex, toIndex-1);
   }

   public static <E extends Comparable<E>> void quickSort(E[] array, int fromIndex, int toIndex) {
      
      if (fromIndex < toIndex) {
         int partitionIndex = comparablePartition(array, fromIndex, toIndex);
         quickSort(array, fromIndex, partitionIndex - 1);
         quickSort(array, partitionIndex + 1, toIndex);

      }
   }
  
  public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      quickSort(array, 0, array.length - 1, comparator);
   }
  
  public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      quickSort(array, fromIndex, toIndex-1, comparator);
   }

  public static <E> void quickSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      
      if (fromIndex < toIndex) {
          int partitionIndex = partition(array, fromIndex, toIndex, comparator);
          quickSort(array, fromIndex, partitionIndex - 1, comparator);
          quickSort(array, partitionIndex + 1, toIndex, comparator);
      }
   }
  
  private static <E> int partition(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
   
      E pivot = array[toIndex];
      int index1 = fromIndex - 1;
  
      for (int index2 = fromIndex; index2 < toIndex; index2++) {
          if (comparator.compare(array[index2], pivot) <= 0) {
              index1++;
              swap(array, index1, index2);
          }
      }
  
      swap(array, index1 + 1, toIndex);

      return index1 + 1;
   }

   private static <E extends Comparable<E>> int comparablePartition(E[] array, int fromIndex, int toIndex) {

      E pivot = array[toIndex];
      int index1 = fromIndex -1;

      for (int index2 = fromIndex; index2 < toIndex; index2++) {
         if (array[index2].compareTo(pivot) <= 0) {
            index1++;
            swap(array, index1, index2);
         }
      }

      swap(array, index1 + 1, toIndex);

      return index1 +1;
   }


}
