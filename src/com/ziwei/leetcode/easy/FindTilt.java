package com.ziwei.leetcode.easy;

public class FindTilt {

    public static void main(String[] args) {

    }
    
    int res = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode treeNode) {
        if (treeNode == null) return 0;

        int leftValue = dfs(treeNode.left);
        int rightValue = dfs(treeNode.right);

        res += Math.abs(leftValue - rightValue);

        return leftValue + rightValue + treeNode.val;
    }
}


