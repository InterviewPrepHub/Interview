package com.interview.Interview.LeetCode_Implementations;

import com.interview.Interview.Pepcoding.Node.LLNode;

import java.util.PriorityQueue;

public class ImplementList {

    LLNode head;
    LLNode tail;

    public void add(int data) {
        LLNode new_node = new LLNode(data);

        if(tail == null) {
            head = new_node;
            tail = new_node;
        } else {
            tail.next = new_node;
            tail = new_node;
        }
    }

    public void print(LLNode node) {
        while (node.next != null) {
            System.out.print(node.data+ "->");
            node = node.next;
        }
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        ImplementList impl = new ImplementList();
        impl.add(1);
        impl.add(2);
        impl.add(3);
        impl.add(4);
        impl.add(5);
        impl.add(6);
        System.out.println("print linkedList");
        impl.print(impl.head);

        System.out.println();
        System.out.println("clone list");
        LLNode clonedHead = impl.cloneList(impl.head);
        impl.print(clonedHead);

        //reverse a linked list
        System.out.println();
        System.out.println("reverse linkedList");
        LLNode node = impl.reverseLinkedList(clonedHead);
        impl.print(node);

        //reverse Linked list by K groups
        System.out.println();
        LLNode cloneHead = impl.cloneList(impl.head);
        System.out.println("reverse list with 3 groups");
        LLNode n1 = impl.reverseLinkedListByKPosition(cloneHead, 3);
        impl.print(n1);

        System.out.println();
        impl.middleOfLinkedList(impl.head);

        LLNode clonedHead1 = impl.cloneList(impl.head);
        System.out.println("before deleting node : ");
        impl.print(clonedHead1);
        impl.deleteNode(clonedHead1, 6);
        System.out.println("after deleting node : ");
        impl.print(clonedHead1);

        ImplementList impl1 = new ImplementList();
        impl1.add(7);
        impl1.add(1);
        impl1.add(2);
        impl1.add(3);
        impl1.add(5);
        impl1.add(5);
        impl1.add(6);

        LLNode clonedHead2 = impl1.cloneList(impl1.head);
        System.out.println("print after you remove duplicate");
        impl1.findDuplicateNodes(impl1.head);
        impl1.print(impl1.head);


        impl.findNthNodefromEndOfLinkedList(clonedHead2, 4);

        //Rotate the linked list to the right by k places. -> circular list
        ImplementList impl2 = new ImplementList();
        impl2.add(7);
        impl2.add(1);
        impl2.add(2);
        impl2.add(3);
        impl2.add(5);
        impl2.add(8);
        impl2.add(6);
        LLNode clonedHead3 = impl1.cloneList(impl2.head);
        System.out.println("before rotating list");
        impl2.print(impl2.head);
        LLNode node1 = impl.rotateListByKPlaces(clonedHead3, 3);
        System.out.println("After rotating list by k places");
        impl2.print(node1);

        impl.intersectionOfTwoLinkedList();

        ImplementList impl3 = new ImplementList();
        impl3.add(1);
        impl3.add(2);
        impl3.add(3);
        impl3.add(3);
        impl3.add(2);
        impl3.add(1);
        //Check if a linked list is a palindrome.
        impl.palindromeLinkedList(impl3.head);

        LLNode node3 = impl.createCircularList();
        impl.detectCycle(node3);

        ImplementList impl4 = new ImplementList();  //531   1->3->5
        impl4.add(1);
        impl4.add(3);
        impl4.add(5);
        ImplementList impl5 = new ImplementList();  //642   2->4->6
        impl5.add(2);
        impl5.add(4);
        impl5.add(6);
        //Merge two sorted linked lists into one sorted linked list.

        LLNode[] list = new LLNode[2];
        list[0] = impl4.head;
        list[1] = impl5.head;
        impl.mergeTwoSortedLinkedList(list);

        //Add two numbers represented by linked lists, where each node contains a single digit.
        impl.addTwoNumbers(impl4.head, impl5.head);

        //Partition a linked list around a value x, such that all nodes less than x come before nodes greater than or equal to x.
        //1 → 4 → 3 → 2 → 5 → 2,  x = 3
        ImplementList impl6 = new ImplementList();  //642   2->4->6
        impl6.add(1);
        impl6.add(4);
        impl6.add(3);
        impl6.add(2);
        impl6.add(5);
        impl6.add(2);
        int x = 3;
        LLNode node4 = impl.partitionList(impl6.head, x);
        System.out.println();
        System.out.println("Partitioned list : ");
        impl6.print(node4);

        copyLLUsingRecursion(impl6.head);

    }


    private static void copyLLUsingRecursion(LLNode head) {

    }

    private LLNode createCircularList() {

        LLNode n1 = new LLNode(1);
        n1.next = new LLNode(2);
        n1.next.next = new LLNode(3);
        n1.next.next.next = new LLNode(4);
        n1.next.next.next.next = new LLNode(5);
        n1.next.next.next.next.next = n1.next;
        return n1;
    }

    //531 => 1->3->5
    //642 => 2->4->6
    private void addTwoNumbers(LLNode l1, LLNode l2) {
        LLNode dummy = new LLNode(0); // dummy head to build result list
        LLNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int val1 = (l1 != null) ? l1.data : 0;
            int val2 = (l2 != null) ? l2.data : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new LLNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Print or return dummy.next (head of result list)
        System.out.print("Print sum : ");
        print(dummy.next); // assuming you have a print method for LLNode
    }

    private LLNode reverseLinkedListByKPosition(LLNode head, int k) {

        LLNode current = head;
        int x = k;
        LLNode p1 = null, p2 = null, p3 = current;

        while (p3!= null & x>0) {
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
            p2.next = p1;
            x--;
        }

        if(p3!=null) {
            head.next = reverseLinkedListByKPosition(p3, k);
        }

        return p2;
    }

    private LLNode cloneList(LLNode head) {
        if (head == null) return null;

        LLNode cloneHead = new LLNode(head.data);
        LLNode current = head.next;
        LLNode cloneCurrent = cloneHead;

        while (current != null) {
            cloneCurrent.next = new LLNode(current.data);
            cloneCurrent = cloneCurrent.next;
            current = current.next;
        }
        return cloneHead;
    }

    /*
        Given a linked list and a value x, rearrange the list so that:
        All nodes with values less than x come before nodes with values greater than or equal to x.
        Preserve the original relative order of the nodes in each partition.

        Input:  1 → 4 → 3 → 2 → 5 → 2,  x = 3

        Output: 1 → 2 → 2 → 4 → 3 → 5
     */
    private LLNode partitionList(LLNode head, int x) {

        //create two dummy node for before and after
        LLNode d1 = new LLNode(0);
        LLNode d2 = new LLNode(0);

        LLNode before = d1; //will be the head of the list containing nodes < x
        LLNode after = d2;  //will be the head of the list containing nodes ≥ x

        /*
            Traverse the original list:
	        If a node’s value < x, append it to the “before” list.
	        Otherwise, append it to the “after” list.
         */
        while(head!=null) {
            if(head.data < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null;
        before.next = d2.next;

        return d1.next;
    }

    //7 -> 1 -> 2 -> 3 -> 5 -> 5 -> 6
    // k = 3
    // 5-> 5 -> 6 -> 7 -> 1 -> 2 -> 3
    private LLNode rotateListByKPlaces(LLNode head, int k) {
         int x = k; //3

         //find the xth node from end of linked list
        LLNode ptr1 = head; //7
        LLNode ptr2 = head; //7

        while(x!=0) {
            ptr1 = ptr1.next; //1,2,3
            x--;              //2,1,0
        }

        //ptr1 = 7, ptr2 = 3
        LLNode newHead = ptr1.next; //5
        ptr1.next = null;

        head = newHead;
        LLNode curr = head;
        while(curr.next!=null) {
            curr = curr.next;
        }

        curr.next = ptr2;

        return head;
    }

    //1->2->3->3->2->1
    //1->2->3->2->1
    private void palindromeLinkedList(LLNode node) {

        //find the middle of the node

        /*
              ----------------------------------------------
              |  List len |   fast ends at  |  slow ends at |
              |  odd      |   last node     |  middle node  |
              |  even     |   null          |  right-middle |
              ----------------------------------------------
         */

        LLNode slow = node; //7
        LLNode fast = node; //7

        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //odd
        if(fast!=null) {
            System.out.println("odd linked list");  //

            LLNode seconfHalf = reverseLinkedList(slow.next); // 1->2
            while(seconfHalf!=null) {
                if (node.data == seconfHalf.data) {
                    node = node.next;
                    seconfHalf = seconfHalf.next;
                    if(seconfHalf == null) {
                        System.out.println("odd list is a palindromic");
                    }
                } else {
                    System.out.println("odd list is not palindromic");
                    break;
                }
            }
        }
        //even
        else {
            System.out.println("even linked list");
            LLNode seconfHalf = reverseLinkedList(slow);
            while(seconfHalf!=null) {
                if(node.data == seconfHalf.data) {
                    node = node.next;
                    seconfHalf = seconfHalf.next;
                    if(seconfHalf == null) {
                        System.out.println("even list is a palindromic");
                    }
                } else {
                    System.out.println("even list is not palindrome");
                    break;
                }
            }
        }
    }

    private void intersectionOfTwoLinkedList() {
    }

    //7 -> 1 -> 2 -> 3 -> 5 -> 5 -> 6
    private void findNthNodefromEndOfLinkedList(LLNode head, int k) {
        LLNode ptr1 = head; //7
        LLNode ptr2 = head; //7

        int x = 0;

        while(x!=k-1) {
            ptr1 = ptr1.next; //1, 2
            x++; //1,2
        }

        //ptr1 = 2 & ptr2 = 7
        while(ptr1.next!=null) {
            ptr1 = ptr1.next;   //3,5,5,6
            ptr2 = ptr2.next;   //1,2,3,5
        }

        System.out.println("Node at "+k+"the position : "+ptr2.data);

    }

    //this is similar to merge two or k lists
    private void mergeTwoSortedLinkedList(LLNode[] list) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (LLNode head : list) {
           while(head!=null) {
               pq.add(head.data);
               head = head.next;
           }
        }
    }

    private void findDuplicateNodes(LLNode head) {
        LLNode current = head;

        while (current!=null || current.next!=null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
                break;
            } else {
                current = current.next;
            }
        }
    }

    private void deleteNode(LLNode head, int data) {
        LLNode delete_node = new LLNode(data);

        //if head node itself is to be deleted
        if(head.data == data) {
            head = head.next;
            return;
        }

        LLNode current = head.next; //2
        LLNode prev = head;

        while(current!= null || current.next!= null) {
            if(current.data == data) {
                prev.next = current.next;   //1->3
                break;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    private void middleOfLinkedList(LLNode node) {
        LLNode slow=head, fast=head;
        while (fast!=null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("middle node : "+slow.data);
    }

    private void detectCycle(LLNode head) {

        LLNode slow = head;
        LLNode fast = head;

        while(slow!=null && fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                System.out.println("cycle detected");
                LLNode node = removeCycle(slow, head);
                System.out.println("List after removing cycle");
                print(node);
            } else {
                System.out.println("No cycle detected");
            }
        }

    }

    private LLNode removeCycle(LLNode slow, LLNode head) {
        LLNode n1 = head;

        while(slow!=null && n1 != null) {
            if(n1.next == slow.next){
                slow.next = null;
                break;
            } else {
                slow = slow.next;
                n1 = n1.next;
            }
        }

        return head;
    }

    private LLNode reverseLinkedList(LLNode head) {

        LLNode node = head;
        LLNode p1 = null, p2 = null, p3 = node;

        while(p3!=null) {
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
            p2.next = p1;
        }

        node = p2;
        return node;
    }
}
