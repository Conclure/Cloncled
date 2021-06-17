package me.conclure.cloncled;

import joptsimple.OptionParser;

public final class ArgumentParser extends OptionParser {
  {
    this.accepts("bootstrap").withOptionalArg();
    this.accepts("config").withOptionalArg();
  }
}
