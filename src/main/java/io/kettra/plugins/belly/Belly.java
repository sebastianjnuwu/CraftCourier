package io.kettra.plugins.belly;

// importando as dependências!
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

// classe principal!
public class Belly extends JavaPlugin {
    
    // conexão com mysql!
    private Connection con;
    
    @Override // ação de quando o plugin é ligado!
    public void onEnable() {
		// salvar e carregar as config.yml
       saveDefaultConfig();
	     reloadConfig();
       // mensagem no console quando o plugin é ligado
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
}

    @Override
    // quando o plugin é desligado! açao que ele ira realizar ;-;
    public void onDisable() {
      
      // mensagem no console de quando o plugin é desligado!
     getServer().getConsoleSender().sendMessage("§f[Ket§btr§ca§aSh§bop§f] §f- §cPlugin Desligado com sucesso!");
    }
}