package craftcourier.spigot.database;

import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.JsonObject;
import craftcourier.spigot.Main;
import org.bukkit.entity.Player;
import com.google.gson.Gson;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import java.sql.*;

public class MySQL {
  
  private Main plugin;
  private Connection sql;

  public MySQL(Main plugin) {
   this.plugin = plugin;
  };
  
  public void connect() {
   
   String hostname = plugin.getConfig().getString("database.hostname");
   
   String username = plugin.getConfig().getString("database.username");
   
   String password = plugin.getConfig().getString("database.password");
  
   String database = plugin.getConfig().getString("database.database");
   
   try {
     
   sql = DriverManager.getConnection("jdbc:mysql://" + hostname + "/" + database, username, password);
   
    plugin.help.Console("&aConnection established.");

   } catch (SQLException e) {
    
     plugin.help.Console("&cFailed to connect to MySQL.");
   
  }
  
};

  public void disconnect() {
    if (sql != null) {
     try {
      if (!sql.isClosed()) {
      sql.close();
      plugin.help.Console("§aMySQL connection closed.");
    }
   } catch (SQLException e) {
    plugin.help.Console("§cError while closing MySQL connection: " + e.getMessage());
    e.printStackTrace();
     }
    }
  };
  
  public void reload() {
    disconnect();
    connect();
  };

  public void $Purchase(Player p) {
   
   Gson gson = new Gson();
   
   String table_name = plugin.getConfig().getString("table.transaction.table-name");
   
   String columns_nickname = plugin.getConfig().getString("table.transaction.columns.nickname");
   
   String columns_status = plugin.getConfig().getString("table.transaction.columns.status");
   
   String columns_uuid = plugin.getConfig().getString("table.transaction.columns.uuid");
   
   int s_default = plugin.getConfig().getInt("table.transaction.status.default");
   
   int s_approved = plugin.getConfig().getInt("table.transaction.status.approved");
   
   int s_received = plugin.getConfig().getInt("table.transaction.status.received");
   
   try {
    
   PreparedStatement search = sql.prepareStatement("SELECT * FROM " + table_name + " WHERE " + columns_nickname + " = ? and " + columns_status + " = ?");
   
    PreparedStatement update = sql.prepareStatement("UPDATE " + table_name + " SET " + columns_status + " = ? WHERE " + columns_uuid + " = ? AND " + columns_status + " = ?");
    
    search.setString(1, p.getName());
    search.setInt(2, s_approved);
    ResultSet rs = search.executeQuery();
    
   while (rs.next()) {
    
  String uuid = rs.getString("uuid");
  String product = rs.getString("product");
  
  JsonObject item = gson.fromJson(product, JsonObject.class);
   
   String command = item.get("command").getAsString();
   String ProductName = item.get("name").getAsString();
   int i = item.get("inventory").getAsInt();
   boolean inventory = (i == 1); 
   boolean PlayerInventory = p.getInventory().firstEmpty() == -1;
   
   if (inventory && PlayerInventory) {
     
     p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
     
     p.sendMessage(plugin.getConfig().getString("message.inventory").replace("@player", p.getName()).replace("@command", command).replace("@product", ProductName));
     
   } else {
   
    update.setInt(1, s_received); 
    update.setString(2, uuid); 
    update.setInt(3, s_default); 
    update.executeUpdate();
    
    plugin.help.Console(plugin.getConfig().getString("message.notification").replace("@player", p.getName()).replace("@command", command).replace("@product", ProductName));
    
    plugin.help.sendHook(plugin.getConfig().getString("webhook.message").replace("@player", p.getName()).replace("@command", command).replace("@product", ProductName));
    
    Bukkit.getScheduler().runTask(plugin, () -> {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("@player", p.getName()));
    });
    
    plugin.sqlite.$bonus();
    
   }
   
  }
    
  } catch (SQLException e) {
      e.printStackTrace();
  }

  };
  
};