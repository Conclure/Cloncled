package me.conclure.cloncled.configuration;

import org.spongepowered.configurate.ConfigurationNode;

import me.conclure.concled.api.annotations.Contract;

@FunctionalInterface
public interface SimpleTranformer<T> {
  T get(ConfigurationNode node, Object[] path, T fallback);
}
