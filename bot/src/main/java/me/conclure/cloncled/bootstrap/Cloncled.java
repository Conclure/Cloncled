package me.conclure.cloncled.bootstrap;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

import me.conclure.cloncled.ExceptionHandler;
import me.conclure.cloncled.command.CommandDispatcher;
import me.conclure.cloncled.command.manager.ConsoleCommandManager;
import me.conclure.cloncled.command.manager.DiscordCommandManager;
import me.conclure.cloncled.commands.shared.ReloadCommand;
import me.conclure.cloncled.commands.shared.StopCommand;
import me.conclure.cloncled.util.MoreFiles;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public final class Cloncled {
  private final ShutdownSignal shutdownSignal;
  private final Bootstrap bootstrap;
  private BotConfig botConfig;
  private JDA jda;

  Cloncled(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
    this.shutdownSignal = new ShutdownSignal(bootstrap);
  }

  private void registerCommands() {
    CommandDispatcher commandDispatcher = new CommandDispatcher(this.shutdownSignal);

    ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager(this.shutdownSignal);
    DiscordCommandManager discordCommandManager = new DiscordCommandManager(this.shutdownSignal, this.botConfig,
        commandDispatcher, this.jda);

    Stream.of(
        new ReloadCommand(this.botConfig),
        new StopCommand(this.shutdownSignal)
    ).forEach(command -> {
      consoleCommandManager.commandMap().register(command) ;
      discordCommandManager.commandMap().register(command);
    });
  }

  void onEnable(Path configPath, String token) throws Exception {
    MoreFiles.copyIfNotExistsAndCreateDirectories(this.getClass().getClassLoader().getResourceAsStream("config.yml"),configPath);
    this.botConfig = new BotConfig(configPath,this.bootstrap);

    ForkJoinPool eventPool = new ForkJoinPool(32, ForkJoinPool.defaultForkJoinWorkerThreadFactory,
        ExceptionHandler.INSTANCE, true);

    this.jda = JDABuilder.create(Set.of(GatewayIntent.values()))
        .setCallbackPool(eventPool, true)
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setEventManager(new AnnotatedEventManager())
        .setToken(token)
        .build()
        .awaitReady();

    this.registerCommands();
  }

  void onDisable() {
    this.jda.shutdown();
  }
}
