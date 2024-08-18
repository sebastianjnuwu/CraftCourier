package craftcourier.spigot.help;

import org.bukkit.ChatColor;
import craftcourier.spigot.Main;  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class Help {
  
  private static Main plugin;

  public Help(Main plugin) {
    this.plugin = plugin;
  }
  
  public static void Console(String log) {
   plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ CraftCourier: " + log));
  }
  
  public static void sendHook(String text) {
   
    boolean enable = plugin.getConfig().getBoolean("webhook.enable");

    if (enable) {
    
     WebhookClient client = WebhookClient.withUrl(plugin.getConfig().getString("webhook.url"));

     WebhookMessageBuilder Message = new WebhookMessageBuilder().setContent(text);

     client.send(Message.build());
      
    }
    
  }

  public static void Check(String currentVersion) {
    String url = "https://raw.githubusercontent.com/sebastianjnuwu/craftcourier/plugin/.github/version.json";

    try {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { 
          
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String result = response.toString();
            JSONObject json = new JSONObject(result);
            String latestVersion = json.getString("version");

            if (latestVersion.equals(currentVersion)) {
                Console("&aYou are on the latest version!");
            } else {
                Console("&aNew version available: &ev" + latestVersion);
                Console("&aDownload: &ehttps://github.com/sebastianjnuwu/craftcourier");
            }
        } else {
            Console("&cFailed to check for new version: " + responseCode);
        }
    } catch (Exception e) {
        Console("&cError: " + e.getMessage());
    }
}

}