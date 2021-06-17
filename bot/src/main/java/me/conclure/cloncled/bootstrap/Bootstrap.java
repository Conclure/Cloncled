package me.conclure.cloncled.bootstrap;

import org.spongepowered.configurate.ConfigurateException;

import me.conclure.cloncled.ExceptionHandler;
import me.conclure.cloncled.util.MoreFiles;
import me.conclure.cloncled.Options;
import me.conclure.cloncled.configuration.Configuration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public final class Bootstrap {
  private final Options options;
  private final ExecutorService executor;
  private final Bootstrapper bootstrapper = new Bootstrapper();
  private final CountDownLatch enableLatch = new CountDownLatch(1);
  private Lifecycle lifecycle = Lifecycle.CREATED;

  public Bootstrap(Options options) {
    this.options = options;
    this.executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder()
        .setNameFormat("Bootstrap")
        .setUncaughtExceptionHandler(ExceptionHandler.INSTANCE)
        .build());
  }

  public void enable() {
    this.executor.execute(() -> {
      if (this.lifecycle != Lifecycle.CREATED) {
        throw new IllegalStateException();
      }

      Configuration bootstrapConfig = new Configuration();
      Path path = this.options.bootstrapPath();

      try {
        MoreFiles.copyIfNotExistsAndCreateDirectories(this.getClass().getClassLoader().getResourceAsStream("bootstrap.yml"),path);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      try {
        bootstrapConfig.load(BootstrapKeys.KEYS,path);
      } catch (ConfigurateException e) {
        this.executor.shutdown();
        throw new RuntimeException(e);
      }

      String token = bootstrapConfig.get(BootstrapKeys.TOKEN);

      try {
        this.bootstrapper.onEnable(this.options.configPath(),token);
        this.enableLatch.countDown();
        this.lifecycle = Lifecycle.ENABLED;
      } catch (Exception e) {
        this.executor.shutdown();
        throw new RuntimeException(e);
      }
    });
  }

  public CountDownLatch enableLatch() {
    return this.enableLatch;
  }

  public void disable() {
    this.executor.execute(() -> {
      if (this.lifecycle != Lifecycle.ENABLED) {
        throw new IllegalStateException();
      }

      try {
        this.bootstrapper.onDisable();
      } catch (Exception e) {
        throw new RuntimeException(e);
      } finally {
        this.executor.shutdown();
        this.lifecycle = Lifecycle.DISABLED;
      }
    });
  }
}
