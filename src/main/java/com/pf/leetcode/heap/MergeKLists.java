package com.pf.leetcode.heap;

import com.pf.leetcode.entity.ListNode;

/**
 * @author pf
 * @date 2020-04-26 13:37
 **/
public class MergeKLists {

    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();
        //[[1,4,5],[1,3,4],[2,6]]
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(5);
        node11.next=node12;
        node12.next=node13;

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next=node22;
        node22.next=node23;

        ListNode node31 = new ListNode(2);
        ListNode node32 = new ListNode(6);
        node31.next=node32;
        mergeKLists.mergeKLists(new ListNode[]{node11, node21, node31});
    }
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0){

            return null;

        }

        ListNode[] heap = new ListNode[lists.length];

        int count = 0;

        for(int i = 0; i < lists.length; i++){

            if(lists[i] != null){

                heap[count++] = lists[i];

            }

        }

        ListNode first = null;

        buildHeap(heap, count);

        ListNode pre = null;

        while(count > 0){

            ListNode temp = heap[0];

            System.out.println(temp.val);

            if (temp.val == -8){

                System.out.println("=");

            }

            if(first == null){

                first = temp;

                pre = first;

            } else {

                pre.next = temp;

                pre = temp;

            }

            count = replace(heap, count);

        }

        return first;

    }

    void buildHeap(ListNode[] heap, int count){

        for(int i = (count - 2) / 2; i >= 0; i--){

            heapify(i, count, heap);

        }

    }

    int replace(ListNode[] heap, int count){

        if(heap[0].next == null){

            count--;

            swap(heap, 0, count);

        } else {

            heap[0] = heap[0].next;

        }

        if(count == 0){

            return 0;

        }

        heapify(0, count, heap);

        return count;

    }

    private void heapify(int pos, int count, ListNode[] heap){

        while(true){

            int max = pos;

            if(pos * 2 + 1 < count && heap[pos * 2 + 1].val < heap[max].val){

                max = pos * 2 + 1;

            }

            if(pos * 2 + 2 < count && heap[pos * 2 + 2].val < heap[max].val){

                max = pos * 2 + 2;

            }

            if(pos == max){

                break;

            } else {

                swap(heap, pos, max);

                pos = max;

            }

        }

    }

    private void swap(ListNode[] heap, int pos, int max){

        ListNode temp = heap[pos];

        heap[pos] = heap[max];

        heap[max] = temp;

    }
}
