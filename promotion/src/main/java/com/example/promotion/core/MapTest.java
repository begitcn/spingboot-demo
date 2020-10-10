package com.example.promotion.core;

import cn.hutool.core.lang.Console;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        String s = "qqwweerrs";
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                length++;
            } else {
                map.put(s.charAt(i), i);
            }
        }

        Console.log(length);
    }
}
