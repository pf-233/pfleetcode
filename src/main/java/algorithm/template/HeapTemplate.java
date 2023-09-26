package algorithm.template;

import java.util.*;

public class HeapTemplate {
    private int[] a; // start store data from index 1
    private int n;  // the max store data size
    private int count; // the counts already stored in the heap
    
    public static void main(String[] args) {
        StringBuilder a;
        ArrayList b = new ArrayList<>(Integer.MAX_VALUE - 8);
        b.add(b);
        HeapTemplate heap = new HeapTemplate(5);
        heap.insert(3);
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.insert(1);
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.insert(5);
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.insert(4);
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.insert(2);
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.removeMax();
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.removeMax();
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.removeMax();
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.removeMax();
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
        heap.removeMax();
        Arrays.stream(heap.a).forEach(e -> System.out.print(e + ","));
        System.out.println();
    }

    public HeapTemplate(int capacity) {
        a = new int[capacity];
        Arrays.fill(a, -1);
        n = capacity;
        count = 0;
    }

    // i: left child i * 2 + 1,  right child i * 2 + 2
    // left child k: k / 2 == > i
    // right child k : (k - 1) / 2 ==> i
    public void insert(int data) {
        if (count >= n) return; // heap is get to capability
        a[count] = data;
        int i = count++;
        while ((i - 1) / 2 >= 0 && a[i] > a[(i - 1) / 2]) { // from down layer to top layer to heapify
            swap(a, i, (i - 1) / 2); // swap() swap these index data
            i = (i - 1) / 2;
        }
    }

    public int removeMax() {
        if (count == 0) return -1; // the heap is empty
        int ans = a[0];
        a[0] = a[--count];
        heapify(a, count - 1, 0);
        return ans;
    }

    /*
     * from top layer to down layer to heapify
     * nums is the array
     * n is the end index
     * i is the start heapify index
     */
    private static void heapify(int[] nums, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 + 1 <= n && nums[i] < nums[i * 2 + 1]) maxPos = i * 2 + 1;
            if (i * 2 + 2 <= n && nums[maxPos] < nums[i * 2 + 2]) maxPos = i * 2 + 2;
            if (maxPos == i) break;
            swap(nums, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j]; // i1 = i ^ j
        nums[j] ^= nums[i]; // j1 = j ^ i1 = j ^ i ^ j = i
        nums[i] ^= nums[j]; // i2 = i1 ^ j1 = i ^ j ^ i = j
    }
}
