package com.ziwei.leetcode.easy;

public class FirstBadVersion {

    public static void main(String[] args){
        int b = 2126753390 / 2 + 1063376695 / 2;
        int a = firstBadVersion(5);
    }

    public static int firstBadVersion(int n) {
        int end = n;
        int start = 0;
        int res = 0;
        while(start <= end){
            int curVersion = start+(end-start)/2;
            if(isBadVersion(curVersion)){
                end = curVersion;
            } else {
                start = curVersion;
            }
            if(end - start <= 2){
                if(isBadVersion(end-1)) return end-1;
                else return end;
            }
        }
        return res;
    }

    public static boolean isBadVersion(int badVersion){
        if(badVersion >= 4) return true;
        else return false;
    }
}
