package com.csm.study.experiment;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Experiment {
    public static void main(String[] args) {
      log.info("一个社会的基尼系数是一个在0~1之间的小数");
      log.info("一个社会的基尼系数是一个在0~1之间的小数");
      int n=100;
      int t=1;
      log.info("人数：{}",n);
      log.info("论数：{}",t);
      //
        experiment(100,1000000);
    }

    public static void experiment(int n,int t){
        //富人数组，长度表示人数，值表示财富
        double[] wealth = new double[n];
        //假设每人刚开始都有100元
        Arrays.fill(wealth,100);
        //用于存放每轮实验前此人是否有钱
        boolean[] hasMoney = new boolean[n];
        //判断每轮开始钱每个人是否有钱设置状态
        for (int i = 0; i < t; i++) {
            log.info("每轮的钱数{}",wealth);

            //首先假设每人每轮开始的时候都是没钱的
            Arrays.fill(hasMoney,false);
            //更新每轮每个人的钱的状态
            for (int j = 0; j < n; j++) {
                if (wealth[j]>0){
                    hasMoney[j]=true;
                }
            }
            //进行给钱实验
            for (int  j = 0;  j < n;  j++) {
                //如果还有钱需要给别人钱
                if (hasMoney[j]){
                    //首先假设给自己
                    int other =j;
                    //随机确定一个，直到随机的那个人不是自己
                    do {
                        //0~~n-1等概率随机
                        other=(int)(Math.random()*n);
                    }while (other==j);
                    //给钱
                    wealth[j]--;
                    wealth[other]++;
                }
            }
        }
        log.info("列出每个人的财富（贫穷=======>>>>富有）：");
        Arrays.sort(wealth);
        for (int i = 0; i < n; i++) {
            System.out.print((int)wealth[i]+"  ");
            //0~9(10个一换行)
            if (i%10==9){
                log.info("");
            }
        }
        log.info("这个社会的基尼系数为：{}",calculateGini(wealth));
        
    }

    /**
     * 计算基尼系数
     * @param wealth 富人数组，长度表示人数，值表示财富
     * @return
     */
    public static double calculateGini(double[] wealth){
        double sumOfAbsoluteDifferences =0;
        double sumOfWealth=0;
        for (int i = 0; i < wealth.length; i++) {
            //统计所有人的财富
            sumOfWealth+=wealth[i];
            //计算自己与及他人的财富差的绝对值
            for (int j = 0; j < wealth.length; j++) {
                sumOfAbsoluteDifferences+=Math.abs(wealth[i]-wealth[j]);
            }
        }

        return sumOfAbsoluteDifferences/(2*wealth.length*sumOfWealth);
    }
}
