package com.ziwei.leetcode.easy;

import java.math.BigDecimal;

public class ClosestValue {

    int res = 0;
    double tmp = Double.MAX_VALUE;

    public static void main(String[] args){
        int tmp = 28%2;

    }

    public int closestValue(TreeNode treeNode, double target){
        return dfs(treeNode, target);
    }

    public int dfs(TreeNode treeNode, double target){
        if(treeNode == null) return 0;

        if(tmp > Math.abs(treeNode.val - target)){
            tmp = Math.abs(treeNode.val - target);
            res = treeNode.val;
        }
        dfs(treeNode.left, target);
        dfs(treeNode.right,target);

        return res;
    }
}

