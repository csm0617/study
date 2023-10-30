package com.csm.study.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestE03BinarySearch {
    @Test
    @DisplayName("测试 递归版本的二分查找")
    public  void test(){
        int[] a={7,9,13,26,30,34,36,38,41,45,47};
        System.out.println(0== E03BinarySearch.search(a,7));
        System.out.println(4== E03BinarySearch.search(a,30));
        System.out.println(5== E03BinarySearch.search(a,30));
    }
}
