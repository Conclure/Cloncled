package me.conclure.cloncled.command;

import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.command.context.CommandContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class CommandDispatcher {
  private final ExecutorService executorService;
  private final ShutdownSignal shutdownSignal;

  public CommandDispatcher(ShutdownSignal shutdownSignal) {
    this.shutdownSignal = shutdownSignal;
    this.executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
        .setNameFormat("Command-Dispatcher")
        .build());
  }

  public <C extends CommandContext> void execute(Command<C> command, C context) {
    if (this.shutdownSignal.signalsShutdown()) {
      this.executorService.shutdown();
      return;
    }

    this.executorService.execute(() -> {
      try {
        command.execute(context);
      } catch (Exception e) {
        throw new CommandException(e);
      }
    });
  }
}
