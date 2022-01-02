package com.ziwei.leetcode.easy;

public class AddString {

    public static void main(String[] args) {
        String num1 = "223";
        String num2 = "89";

        String res = addString(num1, num2);
    }

    /**
     * 字符串非负整数相加
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addString(String num1, String num2) {
        StringBuffer res = new StringBuffer();
        int m = num1.length() - 1, n = num2.length() - 1, carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            int i = m >= 0 ? num1.charAt(m) - '0' : 0;
            int j = n >= 0 ? num2.charAt(n) - '0' : 0;
            int result = i + j + carry;
            res.append(result % 10);
            carry = result / 10;
            m--;
            n--;
        }

        res.reverse();
        return res.toString();
    }
}
