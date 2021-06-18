package me.conclure.cloncled.command.listener;

import me.conclure.cloncled.bootstrap.ShutdownSignal;

import java.util.Objects;
import java.util.Scanner;

public final class ConsoleCommandListener implements Runnable {

  private final ShutdownSignal shutdownSignal;

  public ConsoleCommandListener(ShutdownSignal shutdownSignal) {
    this.shutdownSignal = shutdownSignal;
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);

    while (!this.shutdownSignal.signalsShutdown() && scanner.hasNextLine()) {
      String input = scanner.nextLine();
      System.out.printf("in %s\n", input);
    }

    scanner.close();
  }

}
