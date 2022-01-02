package com.ziwei.leetcode.easy;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {1, 0, 3, 2, 4, 0, 6, 12};
        moveZeroes1(nums);
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length == 0) return;
        int zeroCount = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                zeroCount++;
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[nums.length - zeroCount] = 0;
            }
        }
    }

    public static void moveZeroes1(int[] nums) {
        if (nums.length == 0) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
//                int tmp = nums[i];
//                nums[i] = nums[j];
//                nums[j] = tmp;
                swap(nums, i, j);
                j++;
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
