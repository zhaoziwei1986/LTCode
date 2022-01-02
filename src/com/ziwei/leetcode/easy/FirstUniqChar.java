package com.ziwei.leetcode.easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FirstUniqChar {

    public static void main(String[] args) {
        String s = "aadadaad";

        firstUniqChar1(s);
    }

    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map != null && !map.containsValue(s.charAt(i))) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1)
                return i;
        }

        return -1;
    }

    public static int firstUniqChar1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<Pair>();
        for (int i = 0; i < s.length(); ++i) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                queue.offer(new Pair(s.charAt(i), i));
            } else {
                map.put(s.charAt(i), -1);
                while (!queue.isEmpty() && map.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }

        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

}

class Pair {
    public char ch;
    public int pos;

    Pair(char ch, int pos) {
        this.ch = ch;
        this.pos = pos;
    }
}
