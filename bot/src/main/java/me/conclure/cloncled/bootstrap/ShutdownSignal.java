package me.conclure.cloncled.bootstrap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ShutdownSignal {
  private final CountDownLatch latch;
  private final Bootstrap bootstrap;

  public ShutdownSignal(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
    this.latch = new CountDownLatch(1);
  }

  public void signal() {
    this.latch.countDown();
    this.bootstrap.disable();
  }

  public void await() throws InterruptedException {
    this.latch.await();
  }

  public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
    return this.latch.await(timeout, unit);
  }

  public boolean signalsShutdown() {
    return this.latch.getCount() == 0L;
  }
}
