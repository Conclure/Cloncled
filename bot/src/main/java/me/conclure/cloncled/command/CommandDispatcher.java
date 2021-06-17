package me.conclure.cloncled.command;

import me.conclure.cloncled.ExceptionHandler;
import me.conclure.cloncled.bootstrap.ShutdownSignal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class CommandDispatcher {
  private final ExecutorService executorService;
  private final ShutdownSignal shutdownSignal;

  public CommandDispatcher(ShutdownSignal shutdownSignal) {
    this.shutdownSignal = shutdownSignal;
    this.executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
        .setUncaughtExceptionHandler(ExceptionHandler.INSTANCE)
        .setNameFormat("Command-Dispatcher")
        .build());
  }

  public void execute() {
    if (this.shutdownSignal.shutdown()) {
      this.executorService.shutdown();
      return;
    }

    this.executorService.execute(() -> {

    });
  }
}
