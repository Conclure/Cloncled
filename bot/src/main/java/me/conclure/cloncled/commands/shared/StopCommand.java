package me.conclure.cloncled.commands.shared;

import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.context.CommandContext;
import me.conclure.cloncled.command.context.DiscordCommandContext;

public class StopCommand extends Command<CommandContext> {
  private final ShutdownSignal shutdownSignal;

  public StopCommand(ShutdownSignal shutdownSignal) {
    super("stop");
    this.shutdownSignal = shutdownSignal;
  }

  @Override
  protected void execute(CommandContext context) throws Exception {
    if (context instanceof DiscordCommandContext discordContext) {
      discordContext.message().getChannel().sendMessage("Stopping!").complete();
    }
    this.shutdownSignal.signal();
  }
}
