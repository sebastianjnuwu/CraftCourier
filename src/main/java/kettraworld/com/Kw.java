package kettraworld.com;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bstats.bukkit.Metrics;
import kettraworld.com.database.MySQL;
import kettraworld.com.task.task;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Kw extends JavaPlugin {
  
   private static Kw plugin;
   public MySQL sql;
   
  @Override
  public void onEnable() {
    
    saveDefaultConfig();
  	reloadConfig();
  	plugin = this;
	  sql = new MySQL();
	  Random n = new Random();
   int Id = 15226; 
   Metrics metrics = new Metrics(this, Id);
  
	  new BukkitRunnable() {
  	@Override
		public void run() {
		 	try {
		  	new task();
		 getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&b&l☽ &r&b&lK&r&b&le&r&b&lt&r&b&lt&r&f&lr&r&f&la&r&f&lW&r&7&lo&r&7&lr&r&d&ll&r&5&ld &r&5&lﾠ&a✧ Executando task " + (n.nextInt(1000) + 1) * 3));
    	} catch (Exception e) {
					getLogger().severe(" • houve um erro: " + e.getMessage());
				}
			}
		}.runTaskTimerAsynchronously(this, getConfig().getInt("time") * 1200L, getConfig().getInt("time") * 1200L);
 }
  
   @Override
  public void onDisable() {
   
   getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&b&l☽ &r&b&lK&r&b&le&r&b&lt&r&b&lt&r&f&lr&r&f&la&r&f&lW&r&7&lo&r&7&lr&r&d&ll&r&5&ld &r&5&lﾠ&c✧ Disconectado do cluster!"));
  
  }
  
   public static Kw I(){
        return plugin;
    }
}