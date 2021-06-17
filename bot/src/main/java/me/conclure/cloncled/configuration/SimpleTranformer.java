package me.conclure.cloncled.configuration;

import org.spongepowered.configurate.ConfigurationNode;

@FunctionalInterface
public interface SimpleTranformer<T> {
  T get(ConfigurationNode node, Object[] path, T fallback);
}
