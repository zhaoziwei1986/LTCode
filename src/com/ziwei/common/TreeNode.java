package com.ziwei.common;

public class TreeNode {
    public int val;
    public TreeNode right;
    public TreeNode left;
    TreeNode() {};
    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
