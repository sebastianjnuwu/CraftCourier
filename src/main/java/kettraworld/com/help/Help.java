package kettraworld.com.help;

import kettraworld.com.BR;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class Help {
  
  private static BR plugin;

  public Help(BR plugin) {
    this.plugin = plugin;
  }
  
  public static void sendHook(String text) {
   
    boolean enable = plugin.getConfig().getBoolean("webhook.enable");

    if (enable) {
    
     WebhookClient client = WebhookClient.withUrl(plugin.getConfig().getString("webhook.url"));

     WebhookMessageBuilder Message = new WebhookMessageBuilder().setContent(text);

     client.send(Message.build());
      
    }
    
  }
  
}