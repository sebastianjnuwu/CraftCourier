package kettraworld.com;

import kettraworld.com.execute.delivery;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import kettraworld.com.commands.Reload;
import kettraworld.com.database.MySQL;
import org.bstats.bukkit.Metrics;
import kettraworld.com.util.Util;

public class Kw extends JavaPlugin {
  private static Kw plugin;
  private MySQL mysql;

  @Override
  public void onEnable() {
    plugin = this;
    saveDefaultConfig();
    reloadConfig();
    
  Util.Console("§r§b§l☽ KettraShop &r§r§b§l✡");
    
    Metrics metrics = new Metrics(this, 15226);
    int delay = getConfig().getInt("time") * 1200;
    getCommand("kw").setExecutor(new Reload());
    mysql = new MySQL();

    new BukkitRunnable() {
      @Override
      public void run() {

        try {

         new delivery();
          Util.Console("§r§b§l☽ KettraShop &r§r§b§l✡ &a✧ Executing a new quest...");

        } catch (Exception e) {
          Util.Console("§r§b§l☽ KettraShop &r§r§b§l✡ &c✧ There was an error => " + e.getMessage());
        }
      }
    }.runTaskTimerAsynchronously(this, delay, delay);
  }

  @Override
  public void onDisable() {
   Util.Console("§r§b§l☽ KettraShop &r§r§b§l✡ﾠ&c✧ disconnected from cluster");
   mysql.disconnect();
 }

  public static Kw getPlugin() {
    return plugin;
  }

  public MySQL getMySQL() {
    return mysql;
  }
}