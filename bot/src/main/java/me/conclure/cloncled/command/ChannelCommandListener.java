package me.conclure.cloncled.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.cloncled.bootstrap.ShutdownSignal;

public record ChannelCommandListener(ShutdownSignal shutdownSignal) {

  @SubscribeEvent
  public void onMessageReceived(MessageReceivedEvent event) {
    if (this.shutdownSignal.shutdown()) {
      event.getJDA().removeEventListener(this);
      return;
    }

    User author = event.getAuthor();

    if (author.isBot()) {
      return;
    }

    if (author.isSystem()) {
      return;
    }

    Message message = event.getMessage();

    if (message.isWebhookMessage()) {
      return;
    }

    if (message.isTTS()) {
      return;
    }

    System.out.printf("in c %s\n", message.getContentRaw());
  }
}
