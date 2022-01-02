package com.ziwei;

import com.ziwei.common.TreeNode;

import java.util.*;

public class LeetCode {

    public static void main(String[] args) {
        int i = 1;

        System.out.println(i / 2);

        int coins[] = new int[]{1, 2, 3, 6};
        // System.out.println(coinChange(coins, 5));

        int height[] = new int[]{0, 1, 0, 3, 5, 2, 1, 0, 8};
        //        System.out.println(trap(height));

        String s = "dvdfdewsgdedevs";
        //        System.out.println(lengthOfLongestSubstringSet(s));

        int[] nums = new int[]{1, 2, 3, 3, 4, 4, 5, 5, 6};
        System.out.println(isPossibleDivide(nums, 3));

        int island[][] = {{1, 1, 0, 0, 0}, {0, 0, 1, 0, 1}, {0, 0, 1, 0, 1}};
        //        System.out.println(numIslands(island));

        int grid[][] = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        //        closedIsland(grid);

        int edges[][] = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {0, 4}};
        //        validTree(6, edges);

        int arr[] = {3, 2, 4, 5, 1, 6};
        //        System.out.println(process(arr, 0, arr.length-1));

        int array[] = {1, 2, 3};
        pivotIndex(array);
    }

    public static TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null) {
            return new TreeNode[]{null, null};
        } else {
            TreeNode[] bns;
            if (root.val <= V) {
                bns = splitBST(root.right, V);
                root.right = bns[0];
                bns[0] = root;
            } else {
                bns = splitBST(root.left, V);
                root.left = bns[1];
                bns[1] = root;
            }
            return bns;
        }
    }

    /**
     * 零钱兑换 -- 组成amount金额最小的硬币个数
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {

                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 接雨水问题
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int rightMax[] = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }

    /**
     * 最大不重复字符串 -- zhaoziwei
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        char chr = s.charAt(0);
        String str = String.valueOf(chr);
        int maxLength = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != chr && !str.contains(String.valueOf(s.charAt(i)))) {
                str += String.valueOf(s.charAt(i));

                if (maxLength < str.length()) {
                    maxLength = str.length();
                }
            } else {
                chr = s.charAt(i);
                str = str.substring(str.indexOf(chr) + 1) + String.valueOf(s.charAt(i));
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstringSet(String s) {

        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        int rk = -1, ans = 0;

        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }

            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                rk++;
            }

            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 划分数组为连续数字的数组
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean isPossibleDivide(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            count++;
            map.put(num, count);
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 0) {
                continue;
            }
            for (int j = 0; j < k; j++) {
                Integer count = map.get(nums[i] + j);
                if (count == null || count == 0) {
                    return false;
                }
                count--;
                map.put(nums[i] + j, count);
            }
        }
        return true;
    }

    /**
     * 岛屿数量
     *
     * @param grid
     * @return
     */
    public static int numIslands(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 统计岛屿数量
                if (grid[i][j] == 1) {
                    result++;
                }
                // 遍历
                helper(i, j, grid);
            }
        }
        return result;
    }

    private static void helper(int row, int col, int[][] grid) {
        // 终止条件
        if (row >= grid.length
                || col >= grid[0].length
                || row < 0
                || col < 0
                || grid[row][col] == 2
                || grid[row][col] == 0) {
            return;
        }
        // 涂色
        if (grid[row][col] == 1) {
            grid[row][col] = 2;
        }
        // 遍历
        helper(row + 1, col, grid);
        helper(row, col + 1, grid);
        helper(row - 1, col, grid);
        helper(row, col - 1, grid);
    }

    /**
     * 封闭岛屿数量
     *
     * @param grid
     * @return
     */
    public static int closedIsland(int[][] grid) {
        int result = 0;

        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && dfs(i, j, grid)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean dfs(int row, int col, int[][] grid) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        if (grid[row][col] != 0) {
            return true;
        }
        grid[row][col] = 2;

        boolean i1 = dfs(row + 1, col, grid);
        boolean i2 = dfs(row, col + 1, grid);
        boolean i3 = dfs(row - 1, col, grid);
        boolean i4 = dfs(row, col - 1, grid);

        return i1 && i2 && i3 && i4;
    }

    /**
     * 以图判树
     *
     * @param n
     * @param edges
     * @return
     */
    private static boolean validTree(int n, int[][] edges) {
        int graph[][] = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean visited[] = new boolean[n];

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            visited[cur] = true;
            for (int i = 0; i < n; i++) {
                if (graph[cur][i] == 1) {
                    if (visited[i]) {
                        return false;
                    }

                    visited[i] = true;
                    graph[cur][i] = 0;
                    graph[i][cur] = 0;
                    queue.add(i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);

        int max = Math.max(leftMax, rightMax);

        return max;
    }

    public static int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
        }

        return -1;
    }
    

}
