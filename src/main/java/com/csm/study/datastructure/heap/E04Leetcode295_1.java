package com.csm.study.datastructure.heap;

/**
 * 求数据流的中位数
 */
public class E04Leetcode295_1 {


    private Heap left = new Heap(10, true);//大顶堆
    private Heap right = new Heap(10, false);//小顶堆

    @Override
    public String toString() {
        return left + " <-> " + right;
    }

    /**
     * 为了保证两边数据量的平衡（左右两个堆中的元素的个数相差不超过1）
     * 1.当两边的个数一样时，左边的个数加1
     * 2.当两边的个数不一样时，右边的个数加1
     * ！！！但是数不能随便加在左或者右
     * eg:    大顶堆（假设顶在右边）[2 1 3]     [7 9 8]小顶堆（假设顶在左边）
     * 假如此时数据流中来了一个【10】加在左边 [2 1 3 10]   [7 9 8] 这个肯定不对，中位数不是10
     * 巧妙的添加办法是：
     * 1.如果是左边的个数需要+1，那么先把新加入的元素添加到右边 ，再把右边的最小的数弹出，加入到左边
     * 还是上面的例子： [2 1 3 7]           [8 9 10]       中位数7
     * 再举个例子假设新来的是【4】 ，[2 1 3 7]         [4 8 9]    中位数(7 + 4)/2
     * 2.（同理）如果是右边的个数需要+1，那么先新加入的元素添加到左边，再把把左边最大的数弹出，加入到右边
     * 假设一开始：大顶堆[2 1 3 5]        [7 12 13]  需要添加的元素【10】
     * [2 3 1 5 ]         [7 13 12 10]  中位数(5 + 7)/2
     */
    public void addNum(int num) {
        if (left.size == right.size) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size != right.size) {
            return left.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        E04Leetcode295_1 test = new E04Leetcode295_1();
        //随机添加的每个值，在[1,v]之间随机
        int V = 100;
        //测试的次数
        int testTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * V);
            test.addNum(n);
            if (i % 11 == 0 || i % 8 == 0) {
                System.out.println(test);
                System.out.println(test.findMedian());
            }
        }
    }

}
