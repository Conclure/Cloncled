package me.conclure.cloncled;

import joptsimple.OptionParser;

import me.conclure.cloncled.bootstrap.Bootstrap;

public final class Main {
  public static void main(String... args) {
    OptionParser parser = new ArgumentParser();
    Options options = Options.from(parser.parse(args));
    new Bootstrap(options).enable();
  }
}
