package com.ziwei;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounterLimit extends CounterLimit{

    private static final Logger logger = LoggerFactory.getLogger(SlidingWindowCounterLimit.class);

    private AtomicInteger[] gridDistribution;
    private volatile int currentIndex;
    private int preTotalCount;
    private int gridNumber;
    private volatile boolean isResetting;

    public SlidingWindowCounterLimit(int gridNumber, int limitCount, int limitTime){
        this(gridNumber, limitCount, limitTime, TimeUnit.SECONDS);
    }

    public SlidingWindowCounterLimit(int gridNumber, int limitCount, int limitTime, TimeUnit timeUnit){

        if(gridNumber < limitTime)
            throw new RuntimeException("无法完成限流，窗口数目需大于限制时间！");

        this.gridNumber = gridNumber;
        this.limitCount = limitCount;
        this.limitTime = limitTime;
        this.timeUnit = timeUnit;
        gridDistribution = new AtomicInteger[gridNumber];
        for (int i = 0; i < gridNumber; i++) {
            gridDistribution[i] = new AtomicInteger(0);
        }
        new Thread(new CounterResetThread()).start();
    }

    @Override
    protected boolean tryCount() {
        while(true) {
            if (isLimited) {
                return false;
            } else {
                int currentGridCount = gridDistribution[currentIndex].get();
                if(preTotalCount + currentGridCount == limitCount) {
                    logger.debug("限流 {}", LocalDateTime.now().toString());
                    isLimited = true;
                    return false;
                }

                if(!isResetting && gridDistribution[currentIndex].compareAndSet(currentGridCount, currentGridCount + 1))
                    return true;
            }
        }
    }

    class CounterResetThread implements Runnable {
        @Override
        public void run() {
            while(true){
                try{
                    timeUnit.sleep(1);
                    int indexToReset = currentIndex - 1;
                    if (indexToReset < 0) indexToReset += gridNumber;
                    isResetting = true;
                    preTotalCount = preTotalCount - gridDistribution[indexToReset].get() + gridDistribution[currentIndex++].get();
                    if (currentIndex == gridNumber) currentIndex = 0;
                    if (preTotalCount + gridDistribution[currentIndex].get() < limitCount)
                        isLimited = false;
                    isResetting = false;
                    logger.debug("当前格子：{}, 重置格子：{}, 重置格子访问量:{}, 前窗格子总数：{}", currentIndex, indexToReset, gridDistribution[indexToReset].get(), preTotalCount);
                    gridDistribution[indexToReset].set(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
