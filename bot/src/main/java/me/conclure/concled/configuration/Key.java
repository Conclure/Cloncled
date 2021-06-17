package me.conclure.concled.configuration;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public record Key<T>(Transformer<T> transformer) {

  public T get(ConfigurationNode node) throws SerializationException {
    return this.transformer.transform(node);
  }

  @FunctionalInterface
  public interface Transformer<T> {
    T transform(ConfigurationNode node) throws SerializationException;
  }
}
