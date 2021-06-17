package me.conclure.cloncled.configuration;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import me.conclure.cloncled.api.annotations.PolyNull;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;

public final class Configuration {
  public static final Function<Path, YamlConfigurationLoader> FACTORY;

  static {
     FACTORY = path -> YamlConfigurationLoader.builder()
         .nodeStyle(NodeStyle.FLOW)
         .sink(() -> Files.newBufferedWriter(path, StandardCharsets.UTF_8))
         .source(() -> Files.newBufferedReader(path, StandardCharsets.UTF_8))
         .build();
  }

  private final Map<Key<?>,Object> cache = new IdentityHashMap<>();

  public void load(Iterable<Key<?>> keys, Path path) throws ConfigurateException {
    this.cache.clear();
    CommentedConfigurationNode node = FACTORY.apply(path).load();

    for (Key<?> key : keys) {
      Object value = null;

      try {
        value = key.get(node);
      } catch (SerializationException e) {
        e.printStackTrace();
      }

      this.cache.put(key,value);
    }
  }

  @PolyNull
  public <T> T get(Key<T> key) {
    //noinspection unchecked
    return (T) this.cache.get(key);
  }
}
