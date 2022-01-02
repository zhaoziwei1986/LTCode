package com.ziwei;

public class CoinChange {

    public static void main(String[] args) {
        int[] a = new int[]{3, 1, 2};
        System.out.println(coinChange(6, a));
    }

    public static int coinChange(int amount, int[] coinList) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int k : coinList) {
            for (int j = k; j <= amount; j++) {
                dp[j] += dp[j - k];
            }
        }

        return dp[amount];
    }

}
