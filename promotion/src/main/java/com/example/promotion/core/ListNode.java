package com.example.promotion.core;

import lombok.Data;

@Data
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
