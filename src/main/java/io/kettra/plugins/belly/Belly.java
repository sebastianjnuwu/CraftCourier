package io.kettra.plugins.belly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.chatcolor; 
import org.bukkit.entity.Player;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Belly extends JavaPlugin {
    
  private Connection con;
    
  @Override
  public void onEnable() {
		
  int Id = 15226; 
  Metrics metrics = new Metrics(this, Id);
        
  saveDefaultConfig();
	reloadConfig();
      
    try {
           
  con = DriverManager.getConnection("jdbc:mysql://" + getConfig().getString("MySQL.host") + "/" + getConfig().getString("MySQL.database"), getConfig().getString("MySQL.usuario"), getConfig().getString("MySQL.senha"));

 getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&a✧ Conectado no cluster"));
 
  } catch (SQLException e) {
  getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "§r§b§l☽ §r§b§lK§r§b§le§r§b§lt§r§b§lt§r§f§lr§r§f§la§r§f§lW§r§7§lo§r§7§lr§r§d§ll§r§5§ld §r§5§lﾠ&c✧ Ocorreu um erro ao connectar ao cluster: " + e.getMessage()));
   setEnabled(false);
   return;
 }
      
 new BukkitRunnable() {
  	@Override
		public void run() {
		 	try {
		  	check(); 
    	} catch (SQLException e) {
					getLogger().severe(" - houve um erro: " + e.getMessage());
				}
			}
		}.runTaskTimerAsynchronously(this, getConfig().getInt("tempo") * 1200L, getConfig().getInt("tempo") * 1200L);
    }
    
  private void check() throws SQLException {

	PreparedStatement check = con.prepareStatement("SELECT * FROM TRANSACTIONs WHERE nick = ? and status = '3'");
  
  PreparedStatement sold = con.prepareStatement("UPDATE PRODUCTs SET PRODUCTs.sold = PRODUCTs.sold + 1 WHERE id_product = ?");
  
  PreparedStatement update = con.prepareStatement("UPDATE TRANSACTIONs SET notify = '2', status = '4' WHERE TRANSACTIONs.uuid = ?");

    for (Player p : Bukkit.getOnlinePlayers()) {
			check.setString(1, p.getName());
			ResultSet rs = check.executeQuery();
			
		if (rs.next()) {
				String code = rs.getString("uuid");
				int productCode = rs.getInt("id_product");
				String product = Integer.toString(productCode);
			
		if (getConfig().isList(product + ".commands")) {
		
		  sold.setInt(1, productCode);
		  sold.executeUpdate();

		  update.setString(1, code);
			update.executeUpdate();
		  
		new BukkitRunnable() {	
			@Override
			public void run() {
					for (String cmd : getConfig().getStringList(product + ".commands")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
					}
				}	
			
				}.runTaskLater(this, 1L);
			}
		}
	}
 }

    @Override
    public void onDisable() {
   
   getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&b&l☽ &r&b&lK&r&b&le&r&b&lt&r&b&lt&r&f&lr&r&f&la&r&f&lW&r&7&lo&r&7&lr&r&d&ll&r&5&ld &r&5&lﾠ&c✧ Disconectado do cluster!"));
    }
}