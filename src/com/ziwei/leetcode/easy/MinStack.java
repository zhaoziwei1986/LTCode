package com.ziwei.leetcode.easy;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> helper;
    private int min;

    public MinStack() {
        helper = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        if (min >= val) {
            helper.push(min);
            min = val;
        }
        helper.push(val);
    }

    public void pop() {
        if (helper.pop() == min) {
            min = helper.pop();
        }
    }

    public int top() {
        return (int) helper.peek();
    }

    public int getMin() {
        return min;
    }

}
