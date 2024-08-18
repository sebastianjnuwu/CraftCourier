package craftcourier.spigot.database;

import craftcourier.spigot.Main;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement; 

public class SQLite {

  private Main plugin;
  private Connection sql;
  
  String uuid = "bônus";

  public SQLite(Main plugin) {
    this.plugin = plugin;
    connect();
    $create();
  }

  private void connect() {
    try {
   
     String url = "jdbc:sqlite:" + plugin.getDataFolder() + "/craftcourier.db";
     
     sql = DriverManager.getConnection(url);
     
     plugin.help.Console("&aConnected to local database...");
     
     } catch (SQLException e) {
       plugin.help.Console("&cFailed to connect to local database: " + e.getMessage());
     }
    }

  private void $create() {
     
    String table = "CREATE TABLE IF NOT EXISTS purchases (" + "uuid TEXT PRIMARY KEY, " + "purchase_count INTEGER DEFAULT 0)";
   
    try (Statement stmt = sql.createStatement()) {
      stmt.execute(table);
    } catch (SQLException e) {
      plugin.help.Console("&cFailed to create database: " + e.getMessage());
    }
  }
  
  public void $bonus() {

  int purchaseCount = $get_bonus();
  purchaseCount++;

  String sql = "INSERT INTO purchases (uuid, purchase_count) VALUES (?, ?) " +
  "ON CONFLICT(uuid) DO UPDATE SET purchase_count = ?";
  
  try (PreparedStatement pstmt = this.sql.prepareStatement(sql)) {
    pstmt.setString(1, uuid);
    pstmt.setInt(2, purchaseCount);
    pstmt.setInt(3, purchaseCount);
    pstmt.executeUpdate();

   int amount = plugin.getConfig().getInt("bonus.amount");

  if (purchaseCount >= amount && plugin.getConfig().getBoolean("bonus.enable")) {
  
  String command = plugin.getConfig().getString("bonus.command");
  
  String message = plugin.getConfig().getString("bonus.message");

 
  for (Player i : Bukkit.getOnlinePlayers()) {
    
    i.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("@player", i.getName())));
    
     Bukkit.getScheduler().runTask(plugin, () -> {
     Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("@player", i.getName()));
    });
    
   }
   
   $reset();

}

  } catch (SQLException e) {
    e.printStackTrace();
  }
}

  private int $get_bonus() {
  
  String sql = "SELECT purchase_count FROM purchases WHERE uuid = ?";
  
  try (PreparedStatement pstmt = this.sql.prepareStatement(sql)) {
    pstmt.setString(1, uuid);
 
  try (ResultSet rs = pstmt.executeQuery()) {
    if (rs.next()) {
      return rs.getInt("purchase_count");
      }
    }
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return 0;
}

  private void $reset() {
  String sql = "UPDATE purchases SET purchase_count = 0";
  try (Statement stmt = this.sql.createStatement()) {
    stmt.executeUpdate(sql);
  } catch (SQLException e) {
    e.printStackTrace();
  }
}

}