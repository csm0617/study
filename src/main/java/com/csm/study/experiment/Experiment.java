package com.csm.study.experiment;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Experiment {


    // 一开始有100个人，每个人都有100元
    // 在每一轮都做如下的事情 :
    // 每个人都必须拿出1元钱给除自己以外的其他人，给谁完全随机
    // 如果某个人在这一轮的钱数为0，那么他可以不给，但是可以接收
    // 发生很多很多轮之后，这100人的社会财富分布很均匀吗？
    public static void main(String[] args) {
        System.out.println("一个社会的基尼系数是一个在0~1之间的小数");
        System.out.println("基尼系数为0代表所有人的财富完全一样");
        System.out.println("基尼系数为1代表有1个人掌握了全社会的财富");
        System.out.println("基尼系数越小，代表社会财富分布越均衡；越大则代表财富分布越不均衡");
        System.out.println("在2022年，世界各国的平均基尼系数为0.44");
        System.out.println("目前普遍认为，当基尼系数到达 0.5 时");
        System.out.println("就意味着社会贫富差距非常大，分布非常不均匀");
        System.out.println("社会可能会因此陷入危机，比如大量的犯罪或者经历社会动荡");
        System.out.println("测试开始");
        int n = 100;
        int t = 1;
        log.info("人数：{}", n);
        log.info("轮数：{}", t);

        //
        experiment(100, 1000000);
    }

    public static void experiment(int n, int t) {
        //富人数组，长度表示人数，值表示财富
        double[] wealth = new double[n];
        //假设每人刚开始都有100元
        Arrays.fill(wealth, 100);
        //用于存放每轮实验前此人是否有钱
        boolean[] hasMoney = new boolean[n];
        //判断每轮开始钱每个人是否有钱设置状态
        for (int i = 0; i < t; i++) {
            log.info("当前{}轮的钱数{}", i,wealth);

            //首先假设每人每轮开始的时候都是没钱的
            Arrays.fill(hasMoney, false);
            //更新每轮每个人的钱的状态
            for (int j = 0; j < n; j++) {
                if (wealth[j] > 0) {
                    hasMoney[j] = true;
                }
            }
            //进行给钱实验
            for (int j = 0; j < n; j++) {
                //如果还有钱需要给别人钱
                if (hasMoney[j]) {
                    //首先假设给自己
                    int other = j;
                    //随机确定一个，直到随机的那个人不是自己
                    do {
                        //0~~n-1等概率随机
                        other = (int) (Math.random() * n);
                    } while (other == j);
                    //给钱
                    wealth[j]--;
                    wealth[other]++;
                }
            }
        }
        log.info("列出每个人的财富（贫穷=======>>>>富有）：");
        Arrays.sort(wealth);
        for (int i = 0; i < n; i++) {
            System.out.print((int) wealth[i] + "  ");
            //0~9(10个一换行)
            if (i % 10 == 9) {
                log.info("");
            }
        }
        log.info("这个社会的基尼系数为：{}", calculateGini(wealth));

    }

    /**
     * 计算基尼系数
     *
     * @param wealth 富人数组，长度表示人数，值表示财富
     * @return
     */
    public static double calculateGini(double[] wealth) {
        double sumOfAbsoluteDifferences = 0;
        double sumOfWealth = 0;
        for (int i = 0; i < wealth.length; i++) {
            //统计所有人的财富
            sumOfWealth += wealth[i];
            //计算自己与及他人的财富差的绝对值
            for (int j = 0; j < wealth.length; j++) {
                sumOfAbsoluteDifferences += Math.abs(wealth[i] - wealth[j]);
            }
        }

        return sumOfAbsoluteDifferences / (2 * wealth.length * sumOfWealth);
    }
}
