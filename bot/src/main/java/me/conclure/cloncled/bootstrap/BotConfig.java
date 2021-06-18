package me.conclure.cloncled.bootstrap;

import org.spongepowered.configurate.ConfigurateException;

import me.conclure.cloncled.configuration.Configuration;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BotConfig {
  private final Configuration configuration;
  private final Path path;
  private final Bootstrap bootstrap;

  public BotConfig(Path path, Bootstrap bootstrap) throws ConfigurateException {
    this.path = path;
    this.bootstrap = bootstrap;
    this.configuration = new Configuration();
    this.configuration.load(ConfigKeys.KEYS,path);
  }

  public String prefix() {
    return this.configuration.get(ConfigKeys.PREFIX);
  }

  public CompletableFuture<Void> load() {
    return CompletableFuture.runAsync(() -> {
      try {
        this.configuration.load(ConfigKeys.KEYS, this.path);
      } catch (ConfigurateException e) {
        throw new RuntimeException(e);
      }
    },this.bootstrap.executor());
  }
}
