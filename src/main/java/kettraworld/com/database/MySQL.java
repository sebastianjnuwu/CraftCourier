package kettraworld.com.database;

import java.sql.Connection;
import org.bukkit.ChatColor;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kettraworld.com.Kw;

public class MySQL {
  
  private Connection connection;
  
 public MySQL() {
  
  try {
    
 String host = Kw.I().getConfig().getString("MySQL.host");
 String user = Kw.I().getConfig().getString("MySQL.user");
 String pass = Kw.I().getConfig().getString("MySQL.password");
 String db = Kw.I().getConfig().getString("MySQL.database");
 
   connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, user, pass);
 
   Kw.I().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&a✧ Connected from cluster"));
 
  } catch(SQLException e) {
  
   Kw.I().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&c✧ An error occurred while connecting to the cluster: " + e.getMessage()));
  }
 }
 
  public Connection MySQL() {
		return connection;
	}
} 