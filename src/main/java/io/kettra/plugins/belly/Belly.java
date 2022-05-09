package io.kettra.plugins.belly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Belly extends JavaPlugin {
    
    // conexão com mysql!
    private Connection con;
    
    @Override // ação de quando o plugin é ligado!
    public void onEnable() {
		
       saveDefaultConfig();
	     reloadConfig();
       
       getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §aPlugin Ligado com sucesso!");
      
       try {
	// conexão com mysql
 con = DriverManager.getConnection("jdbc:mysql://" + getConfig().getString("MySQL.host") + "/" + getConfig().getString("MySQL.database"), getConfig().getString("MySQL.usuario"), getConfig().getString("MySQL.senha"));
    // caso ah algum erro aparece essa mensagem no console!
 } catch (SQLException e) {
   getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §cNão foi possível conectar ao MySQL: " + e.getMessage());
   setEnabled(true);
   return;
   
      }
      
      new BukkitRunnable() {
			@Override
			public void run() {
				try {
					check();
				} catch (SQLException e) {
					getLogger().severe("Erro ao realizar checagem: " + e.getMessage());
				}
			}
		}.runTaskTimerAsynchronously(this, getConfig().getInt("tempo") * 1200L, getConfig().getInt("tempo") * 1200L);
    }
    
    private void check() throws SQLException {

		PreparedStatement check = con.prepareStatement("SELECT * FROM `transacao` WHERE `nick` = ?");
        
    for (Player p : Bukkit.getOnlinePlayers()) {
			check.setString(1, p.getName());
			ResultSet rs = check.executeQuery();
			
		if (rs.next()) {
				String code = rs.getString("uuid");
				int productCode = rs.getInt("id_produto");
				String product = Integer.toString(productCode);
			
		if (getConfig().isList(product + ".commands")) {
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
   
        getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §cPlugin Desligado com sucesso!");
    }
}