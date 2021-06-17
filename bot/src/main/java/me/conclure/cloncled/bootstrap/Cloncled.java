package me.conclure.cloncled.bootstrap;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

import me.conclure.cloncled.ExceptionHandler;
import me.conclure.cloncled.bot.ConfigKeys;
import me.conclure.cloncled.command.CommandManager;
import me.conclure.cloncled.configuration.Configuration;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

public final class Cloncled {
  private final ShutdownSignal shutdownSignal;
  private final Bootstrap bootstrap;
  private final Configuration configuration;
  private CommandManager commandManager;
  private JDA jda;

  Cloncled(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
    this.configuration = new Configuration();
    this.shutdownSignal = new ShutdownSignal();
  }

  void onEnable(Path configPath, String token) throws Exception {
    this.configuration.load(ConfigKeys.KEYS,configPath);

    ForkJoinPool eventPool = new ForkJoinPool(32, ForkJoinPool.defaultForkJoinWorkerThreadFactory,
        ExceptionHandler.INSTANCE, true);

    this.jda = JDABuilder.create(Set.of(GatewayIntent.values()))
        .setCallbackPool(eventPool, true)
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setEventManager(new AnnotatedEventManager())
        .setToken(token)
        .build()
        .awaitReady();

    this.commandManager = new CommandManager(this.jda,this.shutdownSignal);
  }

  void onDisable() {
    this.jda.shutdown();
  }

  public Configuration configuration() {
    return this.configuration;
  }

  public CountDownLatch enableLatch() {
    return this.bootstrap.enableLatch();
  }

  public ShutdownSignal shutdownSignal() {
    return this.shutdownSignal;
  }
}
