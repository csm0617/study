package com.csm.study.datastructure.queue.deque.structure;

/**
 * 双端队列接口
 * @param <E> 队列中的元素类型
 */
public interface Deque <E>{
    /**
     * 从双端队列的队头插入
     * @param value 待插入元素
     * @return 插入成功返回true,失败返回false
     */
    boolean offerFirst(E value);
    /**
     * 从双端队列的队尾插入
     * @param value 待插入元素
     * @return 插入成功返回true,失败返回false
     */
    boolean offerLast(E value);

    /**
     * 从双端队列的队头获取元素，并移除
     * @return 双端队列的队头元素
     */
    E pollFirst();
    /**
     * 从双端队列的队头获取元素，并移除
     * @return 双端队列的队头元素
     */
    E pollLast();
    /**
     * 从双端队列的队头获取元素，不移除
     * @return 双端队列的队头元素
     */
    E peekFirst();
    /**
     * 从双端队列的队尾获取值，不移除
     * @return 双端队列的队头元素
     */
    E peekLast();

    /**
     * 判断队列是否为空
     * @return 为空返回true,不为空返回false
     */
    boolean isEmpty();

    /**
     * 判断队列是否满了
     * @return 满了返回true,没满返回false
     */
    boolean isFull();

}
