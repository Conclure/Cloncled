package me.conclure.cloncled.command;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class Arguments implements Iterable<String> {

  private final List<String> arguments;

  public Arguments(List<String> arguments) {
    this.arguments = List.copyOf(arguments);
  }

  public String get(int index) {
    return this.arguments.get(index);
  }

  public String getLowerCase(int index) {
    return this.get(index).toLowerCase();
  }

  @NotNull
  @Override
  public Iterator<String> iterator() {
    return this.arguments.iterator();
  }
}
