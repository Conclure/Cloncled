package me.conclure.concled;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import me.conclure.concled.bootstrap.Bootstrap;

public final class Main {
  public static void main(String... args) {
    OptionParser parser = new ArgumentParser();
    Options options = Options.from(parser.parse(args));

    Thread bootstrapThread = new Thread(new Bootstrap(options));
    bootstrapThread.setName("Bootstrap");
    bootstrapThread.setUncaughtExceptionHandler((thread, exception) -> exception.printStackTrace());
    bootstrapThread.start();
  }
}
