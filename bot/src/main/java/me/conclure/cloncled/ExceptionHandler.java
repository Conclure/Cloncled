package me.conclure.cloncled;

import java.lang.Thread.UncaughtExceptionHandler;

public final class ExceptionHandler implements UncaughtExceptionHandler {
  public static final ExceptionHandler INSTANCE = new ExceptionHandler();

  private ExceptionHandler() {

  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    e.printStackTrace();
  }
}
