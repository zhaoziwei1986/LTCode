package com.ziwei.leetcode.easy;

public class PerfectNumber {

    public static void main(String[] args){
        int num = 1;
        boolean a = checkPerfectNumber(num);
    }

    public static boolean checkPerfectNumber(int num){
        int sum = 1;
        for (int i = 2; i < num / i; i++) {
            if(num % i == 0){
                sum += (i+num / i);
            }
        }
        return sum == num;
    }
}
