package craftcourier.spigot.event;

import craftcourier.spigot.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.nickuc.login.api.event.bukkit.auth.LoginEvent;

public class nLoginListener implements Listener {

  private Main plugin;

  public nLoginListener(Main plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onLogin(LoginEvent e) {
    plugin.sql.$Purchase(e.getPlayer());
  }
  
}