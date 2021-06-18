package me.conclure.cloncled.commands.shared;

import me.conclure.cloncled.bootstrap.BotConfig;
import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.context.CommandContext;
import me.conclure.cloncled.command.context.DiscordCommandContext;

public class ReloadCommand extends Command<CommandContext> {
  private final BotConfig botConfig;

  public ReloadCommand(BotConfig botConfig) {
    super("reload");
    this.botConfig = botConfig;
  }

  @Override
  protected void execute(CommandContext context) throws Exception {
    this.botConfig.load().join();

    if (context instanceof DiscordCommandContext discordContext) {
      discordContext.message().getChannel().sendMessage("Reloaded!").queue();
    }
  }
}
