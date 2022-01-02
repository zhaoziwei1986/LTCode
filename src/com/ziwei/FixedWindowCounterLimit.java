package com.ziwei;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounterLimit  extends CounterLimit{

    //打印日志
    private static Logger logger = LoggerFactory.getLogger(FixedWindowCounterLimit.class);

    // 计数器，初始化是为0
    private AtomicInteger counter = new AtomicInteger(0);

    // 构造方法
    public FixedWindowCounterLimit(int limitCount, long limitTime, TimeUnit timeUnit) {
        this.limitCount = limitCount;
        this.limitTime = limitTime;
        this.timeUnit = timeUnit;
        new Thread(new CounterResetThread()).start();
    }

    // 构造方法
    public FixedWindowCounterLimit(int limitCount, long limitTime){
        this(limitCount, limitTime, TimeUnit.SECONDS);
    }

    @Override
    protected boolean tryCount() {
        while (true){
            if(isLimited){
                return false;
            } else {
                int currentCount = counter.get();
                if(currentCount == limitCount){
                    logger.debug("限流：{}", LocalDateTime.now().toString());
                    isLimited = true;
                    return false;
                } else {
                    if(counter.compareAndSet(currentCount, currentCount +1))
                        return true;
                }
            }
        }
    }

    class CounterResetThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try{
                    timeUnit.sleep(limitTime);
                    counter.compareAndSet(limitCount, 0);
                    isLimited = false;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
