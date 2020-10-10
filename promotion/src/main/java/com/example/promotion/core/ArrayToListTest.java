package com.example.promotion.core;




import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.List;

public class ArrayToListTest {
    public static void main(String[] args) {
        String[] array = {"abc", "qwe", "poi"};
        List<String> list = Arrays.asList(array);
        Console.log(list);
        array[2] = "test";
        Console.log(array);
        Console.log(list);
    }
}
