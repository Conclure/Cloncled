package me.conclure.concled;

import joptsimple.OptionParser;

public class ArgumentParser extends OptionParser {
  {
    this.accepts("bootstrap").withOptionalArg();
    this.accepts("config").withOptionalArg();
  }
}
