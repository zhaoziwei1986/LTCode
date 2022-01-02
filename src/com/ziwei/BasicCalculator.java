package com.ziwei;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculator {

    public static void main(String[] args) {
        String str = "-3+ 2*5 - 118";
        System.out.println(calculator(str));
    }

    public static int calculator(String str) {

        if (str.isEmpty())
            System.out.println("This is an null String");

        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        str = str.replaceAll(" ", "");
        int strLen = str.length();

        for (int i = 0; i < strLen; i++) {
            if (Character.isDigit(str.charAt(i))) {
                num = num * 10 + str.charAt(i) - '0';
            }

            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ' ' || i == strLen - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = str.charAt(i);
                num = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
