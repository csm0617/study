package com.csm.study.datastructure.queue.structure;

public interface Queue<E> {

    /**
     * 向队列尾插入值
     * @param value 待插入值
     * @return 插入成功返回true，插入失败返回false
     */
    boolean offer(E value);

    /**
     * 从队列头获取值，并移除
     * @return 如果队列非空返回队头值，否则返回null
     */
    E poll();

    /**
     * 从队列头获取值，不移除
     * @return 如果队列非空返回队头值，否则返回null
     */
    E peek();

    /**
     * 检查队列是否为空
     * @return 空返回ture,否则返回false
     */
    boolean isEmpty();

    /**
     * 检查队列是否满了
     * @return 满了返回true,没满返回false
     */
    boolean isFull();

}
