package kettraworld.com.util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import org.bukkit.ChatColor;
import kettraworld.com.Kw;

public class Util {

  public static void Console(String log) {
    Kw.getPlugin().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', log));
  }

  public static String Cor(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }

  public static void sendHook(String message) {

    boolean enable = Kw.getPlugin().getConfig().getBoolean("webhook.enable");

    if (enable) {

      WebhookClient client = WebhookClient.withUrl(Kw.getPlugin().getConfig().getString("webhook.url"));

      WebhookMessageBuilder Message = new WebhookMessageBuilder().setContent(message);

      client.send(Message.build());
    }
  }
}