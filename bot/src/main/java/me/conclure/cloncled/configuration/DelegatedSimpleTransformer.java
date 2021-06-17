package me.conclure.cloncled.configuration;

import org.spongepowered.configurate.ConfigurationNode;

import java.util.Arrays;

public final class DelegatedSimpleTransformer<T> implements Key.Transformer<T> {
  private final SimpleTranformer<T> tranformer;
  private final T fallback;
  private final Object[] path;

  public DelegatedSimpleTransformer(SimpleTranformer<T> tranformer, T fallback, String path) {
    this.tranformer = tranformer;
    this.fallback = fallback;
    this.path = Arrays.stream(path.split("\\.")).filter(String::isBlank).toArray();
  }

  @Override
  public T transform(ConfigurationNode node) {
    return this.tranformer.get(node,this.path,this.fallback);
  }
}
