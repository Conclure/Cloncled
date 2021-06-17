package me.conclure.cloncled.api;

public abstract class Module {
  protected Module() {

  }
  
  public abstract void enable();

  public abstract void disable();
}
