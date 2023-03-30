package algorithm.template;

import java.util.Arrays;

public class SortTemplate {

    public static void main(String[] args) {
        int[] nums = new int[]{465,6435,1231,73,213};
        selectionSort(nums);
        Arrays.stream(nums).forEach(e -> System.out.println(e));
    }

    public static void BubbleSort(int[] nums) {
        // we max need to loop nums.length times
        for (int i = 0; i < nums.length; i++) {
            int swapCount = 0;
            // in each loop we only change the adjacent pair. In fact the max value will move to the end in first time.
            for (int j = 1; j < nums.length; j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                    swapCount++;
                }
            }
            //  If no swap is needed, the array is ordered.
            if (swapCount == 0) {
                break;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minimumIndex] > nums[j]) {
                    minimumIndex = j;
                }
            }
            if (minimumIndex > i)
                swap(nums, i, minimumIndex);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j]; // i1 = i ^ j
        nums[j] ^= nums[i]; // j1 = j ^ i1 = j ^ i ^ j = i
        nums[i] ^= nums[j]; // i2 = i1 ^ j1 = i ^ j ^ i = j
    }

    public static void InsertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > key; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = key;
        }
    }

    public static void shellSort(int[] nums) {
        int increment = nums.length;
        // execute al least once
        do {
            // 3 is up to you, + 1 is ensure we can sorted the array.
            increment = increment / 3 + 1;
            // start from [0, increment - 1]
            for (int i = 0; i < increment; i++) {
                // Insertion Sort subsequence
                for (int j = i + increment; j < nums.length; j += increment) {
                    int key = nums[j];
                    int k = j;
                    while (k > i && nums[k] < nums[k - increment]) {
                        nums[k] = nums[k - increment];
                        k -= increment;
                    }
                    if (k != j) {
                        nums[k] = key;
                    }
                }
            }
        }
        while (increment > 1);
    }
}
