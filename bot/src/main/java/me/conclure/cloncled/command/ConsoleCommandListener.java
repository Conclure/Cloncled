package me.conclure.cloncled.command;

import me.conclure.cloncled.bootstrap.ShutdownSignal;

import java.util.Scanner;

public record ConsoleCommandListener(ShutdownSignal shutdownSignal) implements Runnable {

  public ConsoleCommandListener(ShutdownSignal shutdownSignal) {
    this.shutdownSignal = shutdownSignal;
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);

    while (!this.shutdownSignal.shutdown() && scanner.hasNextLine()) {
      String input = scanner.nextLine();
      System.out.printf("in %s\n", input);
    }

    scanner.close();
  }
}
