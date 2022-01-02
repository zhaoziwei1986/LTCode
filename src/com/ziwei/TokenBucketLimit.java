package com.ziwei;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketLimit {

    private static final Logger logger = LoggerFactory.getLogger(TokenBucketLimit.class);

    private int genNumber;
    private int genTime;
    private TimeUnit timeUnit;
    private int maxNumber;
    private AtomicInteger storedNumber;

    public TokenBucketLimit(int genNumber, int genTime, TimeUnit timeUnit, int maxNumber){
        this.genNumber = genNumber;
        this.genTime = genTime;
        this.maxNumber = maxNumber;
        this.timeUnit = TimeUnit.SECONDS;
        this.storedNumber = new AtomicInteger(0);

        new Thread().start();
    }

    public TokenBucketLimit(int genNumber, int genTime, int maxNumber){
        this(genNumber, genTime, TimeUnit.SECONDS, maxNumber);
    }

    public boolean tryAquire() {
        while(true)
        {
            int currentStoredNumber = storedNumber.get();
            if(currentStoredNumber == 0){
                logger.debug("限流");
                return false;
            }

            if(storedNumber.compareAndSet(currentStoredNumber, currentStoredNumber - 1)){
                return true;
            }
        }
    }

    class TokenBucketThread implements Runnable {

        @Override
        public void run() {
            while (true){
                if(storedNumber.get() == maxNumber) {
                    logger.debug("令牌已满");
                    try{ timeUnit.sleep(genTime);} catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int old = storedNumber.get();
                    int newValue = old + genNumber;
                    if (newValue > maxNumber)
                        newValue = maxNumber;
                    storedNumber.compareAndSet(old, newValue);
                    logger.debug("生成令牌数: {}, 当前令牌数：{}", genNumber, newValue);
                    try {timeUnit.sleep(genTime);} catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
