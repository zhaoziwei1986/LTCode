package com.ziwei.leetcode.easy;

import java.util.Stack;

/**
 * 有效括号 leetcode——20
 */
public class IsValidString {

    public static void main(String[] args) {
        String s = "([)]";
        isValid(s);
    }

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        String l = "({[";
        String r = ")}]";
        for (char c : s.toCharArray()) {
            int left = l.indexOf(String.valueOf(c));
            int right = r.indexOf(String.valueOf(c));
            if (left >= 0) {
                stack.push(c);
            }
            if (right >= 0) {
                if (stack.isEmpty()) return false;
                int tmp = l.indexOf(String.valueOf(stack.pop()));
                if (right == tmp)
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
