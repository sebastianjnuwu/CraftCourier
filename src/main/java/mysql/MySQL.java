package mysql;

import kettraworld.com.help.Help;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import com.google.gson.Gson;
import org.bukkit.Sound;
import java.sql.*;

public class MySQL {
  
  private JavaPlugin plugin;
  private Connection sql;

  public MySQL(JavaPlugin plugin) {
   this.plugin = plugin;
  }
  
  public void connect() {
   
   String host = plugin.getConfig().getString("MySQL.host");
   
   String username = plugin.getConfig().getString("MySQL.user");
   
   String password = plugin.getConfig().getString("MySQL.password");
  
   String database = plugin.getConfig().getString("MySQL.database");
   
   try {
     
   sql = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
   
    plugin.getLogger().info("§r§b§l☽ KettraShop §r§b§l☽ • MySQL: §2Conectado na database");

   } catch (SQLException e) {
    
     plugin.getLogger().info("§r§b§l☽ KettraShop §r§b§l☽ • MySQL: §cFalha na conexão!");
   
  }
  
}

  public void disconnect() {
    try {
      sql.close();
      sql = null;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  };
  
  public void SearchPurchase(Player p) {
   
   Gson gson = new Gson();
   
   String table_name = plugin.getConfig().getString("table.transaction.table-name");
   
   int s_default = plugin.getConfig().getInt("table.transaction.status.default");
   
   int s_approved = plugin.getConfig().getInt("table.transaction.status.approved");
   
   int s_received = plugin.getConfig().getInt("table.transaction.status.received");
   
   try {
    
   PreparedStatement search = sql.prepareStatement("SELECT * FROM " + table_name + " WHERE nickname = ? and status = ?");
   
    PreparedStatement update = sql.prepareStatement("UPDATE " + table_name + " SET status = ? WHERE uuid = ? AND status = ?");
    
    search.setString(1, p.getName());
    search.setInt(2, s_approved);
    ResultSet rs = search.executeQuery();
    
   while (rs.next()) {
    
  String uuid = rs.getString("uuid");
  String product = rs.getString("product");
  
  JsonObject item = gson.fromJson(product, JsonObject.class);
   
   String command = item.get("command").getAsString();
   int i = item.get("inventory").getAsInt();
   boolean inventory = (i == 1); 
   boolean PlayerInventory = p.getInventory().firstEmpty() == -1;
   
   if (inventory && PlayerInventory) {
     
     p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
     
     p.sendMessage(plugin.getConfig().getString("message.inventory").replace("@player", p.getName()));
     
   } else {
   
    update.setInt(1, s_received); 
    update.setString(2, uuid); 
    update.setInt(3, s_default); 
    update.executeUpdate();
    
    Help.sendHook(plugin.getConfig().getString("webhook.message"));
    
    Bukkit.getScheduler().runTask(plugin, () -> {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("@player", p.getName()));
    });
    
 /* if (plugin.getConfig().getBoolean("bonus.enable")) {

   String b_cmd = plugin.getConfig().getStringList("bonus.command").get(0);
   
   String b_msg = plugin.getConfig().getString("bonus.message");
   
   Bukkit.broadcastMessage(b_msg.replace("@player", p.getName()));
   
   for (Player online : Bukkit.getOnlinePlayers()) {
      Bukkit.getScheduler().runTask(plugin, () -> {
     Bukkit.dispatchCommand(Bukkit.getConsoleSender(), b_cmd.replace("@player", online.getName()));
    });
     };
    
     } */
     
   }
   
  }
    
  } catch (SQLException e) {
      e.printStackTrace();
  }

  }

}