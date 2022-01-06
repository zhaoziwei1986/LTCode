package com.ziwei.leetcode.easy;

public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode tmp = res;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                tmp.next = list2;
                list2 = list2.next;
            } else {
                tmp.next = list1;
                list1 = list1.next;
            }
            tmp = tmp.next;
        }

        tmp.next = list1 == null ? list2 : list1;
        return res.next;
    }
}
