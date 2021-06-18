package me.conclure.cloncled.command;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public abstract class Command<C extends CommandContext> {
  private final String name;
  private final Set<Class<?>> listenerSet;

  protected Command(String name, Set<Class<?>> listenerSet) {
    this.name = name.toLowerCase();
    Set<Class<?>> tempMap = Collections.newSetFromMap(new IdentityHashMap<>());
    tempMap.addAll(listenerSet);
    this.listenerSet = tempMap;
  }

  protected Command(String name, Class<?>... classes) {
    this(name,Set.of(classes));
  }

  protected Command(String name, Collection<Class<?>> classes) {
    this(name,Set.copyOf(classes));
  }

  public String name() {
    return this.name;
  }

  public boolean allowsExecutionFrom(Class<?> clazz) {
    return this.listenerSet.contains(clazz);
  }

  protected abstract void execute(C context);
}
