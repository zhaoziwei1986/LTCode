package com.ziwei;

import sun.lwawt.macosx.CSystemTray;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(3, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(5, new ListNode(9, new ListNode(1)));

        ListNode sumNode = addTwoNumbers(l1, l2);

        while(l1 != null) {
            if(l1.next == null) {
                System.out.println(l1.val);
            } else {
                System.out.print(l1.val + ",");
            }
            l1 = l1.next;
        }

        while(l2 != null) {
            if(l2.next == null)
                System.out.println(l2.val);
            else
                System.out.print(l2.val + ",");

            l2 = l2.next;
        }

        while(sumNode != null) {
            if(sumNode.next == null)
                System.out.println(sumNode.val);
            else
                System.out.print(sumNode.val + ",");

            sumNode = sumNode.next;
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode root = new ListNode(0);
        ListNode cursor =  root;
        int carry = 0;

        while(l1 != null || l2 != null || carry !=0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return root.next;
    }
}
