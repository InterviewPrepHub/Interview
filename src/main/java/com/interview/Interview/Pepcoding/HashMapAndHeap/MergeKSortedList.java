package com.interview.Interview.Pepcoding.HashMapAndHeap;

import java.util.Iterator;
import java.util.PriorityQueue;

/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6


Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 */
public class MergeKSortedList {

    public static void main(String[] args) {

        ListNode[] listNodes = new ListNode[3];

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        listNodes[0] = l1;
        listNodes[1] = l2;
        listNodes[2] = l3;

        MergeKSortedList m = new MergeKSortedList();
        ListNode res = m.mergeKLists(listNodes);

        while (res!= null) {
            System.out.println(res.val);
            res = res.next;
        }


    }

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        /*for (ListNode head : lists) {
            while (head!=null) {
                minHeap.add(head.val);
                head = head.next;
            }
        }*/

        for (int i = 0; i < lists.length; i++) {
            while (lists[i]!=null) {
                minHeap.add(lists[i].val);
                lists[i] = lists[i].next;
            }
        }

        ListNode dummy = new ListNode();
        ListNode head = dummy;

        while (!minHeap.isEmpty()) {
            head.next = new ListNode(minHeap.remove());
            head = head.next;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
