package com.csm.study.recursion;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
public class TestE04BubbleSort {
    @Test
    @DisplayName("递归实现冒泡排序测试")
    public void test() {
        int[] arr = {6, 5, 4, 2, 1, 3, 8, 7, 6};
        E04BubbleSort.sort(arr);
        Arrays.stream(arr).forEach(value -> {
            System.out.print(value+" ");
        });
    }
}
