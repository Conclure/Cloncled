package me.conclure.cloncled.bootstrap;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import me.conclure.cloncled.ExceptionHandler;
import me.conclure.cloncled.bot.ConfigKeys;
import me.conclure.cloncled.configuration.Configuration;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public final class Bootstrapper {
  private final Configuration configuration;
  private JDA jda;

  Bootstrapper() {
    this.configuration = new Configuration();
  }

  void onEnable(Path configPath, String token) throws Exception {
    this.configuration.load(ConfigKeys.KEYS,configPath);

    ForkJoinPool eventPool = new ForkJoinPool(32, ForkJoinPool.defaultForkJoinWorkerThreadFactory,
        ExceptionHandler.INSTANCE, true);

    this.jda = JDABuilder.create(Set.of(GatewayIntent.values()))
        .setCallbackPool(eventPool, true)
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setToken(token)
        .build();
  }

  void onDisable() {
    this.jda.shutdown();
  }
}
