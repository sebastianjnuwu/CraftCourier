package kettraworld.com.webhook;

import kettraworld.com.Kw;
import org.bukkit.ChatColor;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class Hook {
  
  public void SendHook(String message) {

  boolean webhook = Kw.I().getConfig().getBoolean("webhook.enable");
  
  String config = Kw.I().getConfig().getString("webhook.url");
     
  WebhookClient client = WebhookClient.withUrl(config);
  
  WebhookMessageBuilder Message = new WebhookMessageBuilder().setContent(message);
    
    if (webhook) {
     
     try {
     
    client.send(Message.build());
    
     } catch(Exception e) {
       Kw.I().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&c✧ There was an error sending notification webhook: " + e.getMessage()));
     }
    }
 }
 
}