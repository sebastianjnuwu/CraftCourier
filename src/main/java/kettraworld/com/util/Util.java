package kettraworld.com.util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import org.bukkit.ChatColor;
import kettraworld.com.Kw;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

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

  public static void Update(String Version) {
    try {

   OkHttpClient client = new OkHttpClient();

   Request request = new Request.Builder().url("https://raw.githubusercontent.com/sebastianjnuwu/KettraShop/plugin/.github/version.json").build();

   Response response = client.newCall(request).execute();

   if (response.isSuccessful()) {

   ResponseBody responseBody = response.body();

   if (responseBody != null) {
  
   String result = responseBody.string();
   
   JSONObject json = new JSONObject(result);
  
  String version = json.getString("version");
   
   if (version.equals(Version)) {
     Console("§r§b§l☽ KettraShop &r§r§b§l✡ &a✧ you are on the latest version!");
   } else {
     Console("§r§b§l☽ KettraShop &r§r§b§l✡ &a✧ New version available &e" + version);
     Console("§r§b§l☽ KettraShop &r§r§b§l✡ &a✧ Download: &ehttps://github.com/sebastianjnuwu/KettraShop");
   }
    }
     } else {
      Console("§r§b§l☽ KettraShop &r§r§b§l✡ &c✧ Request failed. Error code: " + response.code());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
