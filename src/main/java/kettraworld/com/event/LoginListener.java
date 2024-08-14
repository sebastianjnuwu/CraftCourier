package kettraworld.com.event;

import kettraworld.com.BR;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.nickuc.login.api.event.bukkit.auth.LoginEvent;

public class LoginListener implements Listener {

  private BR plugin;

  public LoginListener(BR plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onLogin(LoginEvent e) {
   plugin.sql.SearchPurchase(e.getPlayer());
  }
  
}