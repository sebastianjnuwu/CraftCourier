package kettraworld.com;

import mysql.MySQL;
import kettraworld.com.help.Help;
import kettraworld.com.commands.Reload;
import kettraworld.com.event.LoginListener;
import org.bukkit.plugin.java.JavaPlugin;

public class BR extends JavaPlugin {

  public MySQL sql;
  public Help help;
  
  @Override
  public void onEnable() {
 
    // mostrar a versão do plugin 
   getLogger().info("§r§b§l☽ KettraShop §r§b§l☽ • v" + getDescription().getVersion());
   
    help = new Help(this);
    
    // conectar no MySQL 
    sql = new MySQL(this);
    sql.connect();
   
    // registrar o comando de recarregar o plugin 
   getCommand("kw").setExecutor(new Reload(this));
   
    // registar o evento de "login" dos players do nlogin
   getServer().getPluginManager().registerEvents(new LoginListener(this), this);

   }

  @Override
  public void onDisable() {
    sql.disconnect();
    getLogger().info("§r§b§l☽ KettraShop §r§b§l☽ • Plugin desligado");
  }
 
  public MySQL getMySQL() {
    return sql;
  }
  
}