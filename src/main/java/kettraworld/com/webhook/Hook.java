package kettraworld.com.webhook;

import kettraworld.com.Kw;
import org.bukkit.ChatColor;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class Hook {
  
  public static void sendHook(String message) {

    boolean Enabled = Kw.I().getConfig().getBoolean("webhook.enable");

    if (!Enabled) {
      return;
    }

    String config = Kw.I().getConfig().getString("webhook.url");

    if (config == null) {
      Kw.I().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§cA URL do webhook não está configurada."));
      return;
    }

    WebhookClient client = WebhookClient.withUrl(config);
    WebhookMessageBuilder webhookMessage = new WebhookMessageBuilder().setContent(message);

    try {
      client.send(webhookMessage.build());
    } catch (Exception e) {
      Kw.I().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§l✧ §cThere was an error sending the webhook notification: " + e.getMessage()));
    }
  }
}