package com.csm.study.datastructure.stack;

import com.csm.study.datastructure.queue.ArrayQueue1;
import com.csm.study.datastructure.queue.ArrayQueue3;

/**
 * 单队列模拟栈  leetcode225
 *
 * @1.调用push和pop的方法最多100次
 * @2.每次调用pop和top都能保证栈不为空
 */
public class E06Leetcode225 {
    /*
      用队列模拟栈：
          栈顶             栈底
          队头             队尾
         往队列中添加a,b,c
         先添加a   [ a ]  queue.offer(a)
         如果继续往队列中添加b，那么[a b]不符合栈的要求
      解决办法：
         每次添加新元素后queue.offer(b)
         需要先把队列之前的元素全部出队size是未添加b时队列的元素个数
         for (int i = 0; i < size; i++)循环中 queue.offer(queue.pop()) 把原先队列里的元素先出队再加入[b a],此时新加元素b成了栈头

          例如队列中已有[c b a] 加入d： [c b a d]要使d成为栈顶 把c b a 先出队再加入队列 [d c b a]
     */

    ArrayQueue3<Integer> queue = new ArrayQueue3(100);
    private int size = 0;

    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
