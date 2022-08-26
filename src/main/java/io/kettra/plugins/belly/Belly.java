package io.kettra.plugins.belly;

import io.kettra.plugins.belly.discord.DiscordWebhook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Belly extends JavaPlugin {
    
    private Connection con;
    
  @Override
  public void onEnable() {
		
		int pluginId = 15226; 
    Metrics metrics = new Metrics(this, pluginId);
        
    saveDefaultConfig();
	  reloadConfig();
       
  getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §aPlugin connected successfully!");
      
    try {
           
  con = DriverManager.getConnection("jdbc:mysql://" + getConfig().getString("MySQL.host") + "/" + getConfig().getString("MySQL.database"), getConfig().getString("MySQL.usuario"), getConfig().getString("MySQL.senha"));

  } catch (SQLException e) {
  getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §cCould not connect to MySQL: " + e.getMessage());
   setEnabled(true);
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

	PreparedStatement check = con.prepareStatement("SELECT * FROM transaction WHERE nick = ? and status = '2'");
  
  PreparedStatement sold = con.prepareStatement("UPDATE product SET product.sold = product.sold + 1 WHERE id_product = ?");
  
  PreparedStatement delete = con.prepareStatement("DELETE FROM transaction WHERE uuid = ?");
  
  PreparedStatement log = con.prepareStatement("INSERT INTO `historic` (`uuid`, `nick`, `id_product`, `date`, `status`)"
				+ " VALUES (?, ?, ?, ?, 2)");

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

		  delete.setString(1, code);
			delete.executeUpdate();
		  
		  log.setString(1, code);
		  log.setString(2, p.getName());
	  	log.setInt(3, productCode);
			log.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			log.executeUpdate();
					
   p.sendMessage(getConfig().getString("mensagem").replaceAll("&", "§").replaceAll("@player", p.getName()));
   
   DiscordWebhook webhook = new DiscordWebhook(getConfig().getString("webhook.url"));
   webhook.setContent(getConfig().getString("webhook.mensagem").replaceAll("@player", p.getName()));
   
      try {
                webhook.execute();
            } catch (Exception e) {
                getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- " + e);
            }
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
   
        getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §cPlugin successfully disconnected!");
    }
}