package kettraworld.com.database;

import kettraworld.com.util.Util;
import kettraworld.com.Kw;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.ChatColor;

public class MySQL {

  private Connection connection;

  public MySQL() {
    connect();
  }

  public void connect() {
    try {
      String host = Kw.getPlugin().getConfig().getString("MySQL.host");
      String user = Kw.getPlugin().getConfig().getString("MySQL.user");
      String pass = Kw.getPlugin().getConfig().getString("MySQL.password");
      String db = Kw.getPlugin().getConfig().getString("MySQL.database");

      connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, user, pass);

      Util.Console("§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&a✧ Connected to the MySQL cluster");

    } catch (SQLException e) {
      Util.Console("§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&c✧ An error occurred while connecting to the MySQL cluster => " + e.getMessage());
    }
  }

  public void reload() {
    disconnect();
    connect();
  }

  public void disconnect() {
    if (connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Connection getConnection() {
    return connection;
  }
}