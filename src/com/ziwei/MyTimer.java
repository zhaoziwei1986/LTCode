package com.ziwei;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyTimer {

  public static long start;

  public static void main(String[] arg) throws Exception {
//        timerTask();
    runnableTimer();
  }

  public static void timerTask() {
    ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    TimerTask ts1 = new TimerTask() {
      @Override
      public void run() {
        try {
          Thread.sleep(2000);
          System.out.println("Task 1 invoked!" + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    TimerTask ts2 = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Task 2 invoked!" + (System.currentTimeMillis() - start));
      }
    };
    Timer timer = new Timer();
    start = System.currentTimeMillis();
//        timer.schedule(ts1, 0, 1000);
//        timer.schedule(ts2,0,2000);
//        timer.scheduleAtFixedRate(ts1, new Date(), 1000);
    service.schedule(ts1, 1000, TimeUnit.MILLISECONDS);
    service.schedule(ts2, 1000, TimeUnit.MILLISECONDS);
    service.scheduleAtFixedRate(ts2, 100, 2000, TimeUnit.MILLISECONDS);
  }

  public static void runnableTimer() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable Timer Start!");
      }
    };
    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    service.schedule(runnable, 100, TimeUnit.MILLISECONDS);
    service.scheduleWithFixedDelay(runnable, 10, 5, TimeUnit.SECONDS);
  }

}
