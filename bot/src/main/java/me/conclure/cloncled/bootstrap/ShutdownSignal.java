package me.conclure.cloncled.bootstrap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ShutdownSignal {
  private final CountDownLatch latch;

  public ShutdownSignal() {
    this.latch = new CountDownLatch(1);
  }

  public void signal() {
    this.latch.countDown();
  }

  public void await() throws InterruptedException {
    this.latch.await();
  }

  public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
    return this.latch.await(timeout, unit);
  }

  public boolean shutdown() {
    return this.latch.getCount() == 0L;
  }
}