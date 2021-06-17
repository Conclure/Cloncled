package me.conclure.concled.configuration;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import me.conclure.concled.api.annotations.PolyNull;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class Configuration {
  public static final Function<Path, YamlConfigurationLoader> FACTORY;

  static {
     FACTORY = path -> YamlConfigurationLoader.builder()
         .nodeStyle(NodeStyle.FLOW)
         .sink(() -> Files.newBufferedWriter(path, StandardCharsets.UTF_8))
         .source(() -> Files.newBufferedReader(path, StandardCharsets.UTF_8))
         .build();
  }

  private ConfigurationNode node;

  public void load(Path path) throws ConfigurateException {
    this.node = FACTORY.apply(path).load();
  }

  @PolyNull
  public <T> T get(Key<T> key) {
    try {
      return key.get(this.node);
    } catch (SerializationException e) {
      e.printStackTrace();
      return null;
    }
  }
}
