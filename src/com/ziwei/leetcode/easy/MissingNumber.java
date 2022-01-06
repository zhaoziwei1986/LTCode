package com.ziwei.leetcode.easy;

import java.util.Arrays;

public class MissingNumber {

    public static void main(String[] args){
        int[] nums = {40,43,42,18,6,13,19,8,44,4,12,22,31,30,10,16,3,17,25,7,41,21,48,26,32,27,49,15,1,20,35,46,11,23,2,0,38,28,37,9,39,47,36,45,24,34,29,14,33};
        int missingNum = missingNumber1(nums);
    }

    public static int missingNumber(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static int missingNumber1(int[] nums){
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }
        return xor;
    }
}
