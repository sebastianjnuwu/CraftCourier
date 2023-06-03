package kettraworld.com.task;

import org.bukkit.Sound;
import org.bukkit.Bukkit;
import kettraworld.com.Kw;
import java.sql.ResultSet;
import java.sql.Connection;
import org.bukkit.ChatColor;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import kettraworld.com.webhook.Hook;
import org.bukkit.scheduler.BukkitRunnable;

public class task {

  Connection con = Kw.I().sql.MySQL();

  public task() throws SQLException {

    PreparedStatement check = con.prepareStatement("SELECT * FROM TRANSACTIONs WHERE nick = ? and status = '1'");

    PreparedStatement update = con.prepareStatement("UPDATE TRANSACTIONs SET status = '3' WHERE TRANSACTIONs.uuid = ?");

    for (Player p: Bukkit.getOnlinePlayers()) {
      check.setString(1, p.getName());
      ResultSet rs = check.executeQuery();

      if (rs.next()) {
        String code = rs.getString("uuid");
        int productCode = rs.getInt("id_product");
        String product = Integer.toString(productCode);

        if (Kw.I().getConfig().isList(product + ".commands")) {

          boolean inventoryFull = p.getInventory().firstEmpty() == -1;

          boolean inventoryFlag = Kw.I().getConfig().getBoolean(product + ".inventory");

          if (inventoryFlag && inventoryFull) {
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Kw.I().getConfig().getString("message.inventory").replace("@product", Kw.I().getConfig().getString(product + ".name"))));

          } else {
            update.setString(1, code);
            update.executeUpdate();

            new BukkitRunnable() {
              @Override
              public void run() {

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Kw.I().getConfig().getString(product + ".message").replace("@product", Kw.I().getConfig().getString(product + ".name"))));

                Hook webhook = new Hook();
                webhook.SendHook(Kw.I().getConfig().getString("webhook.message").replace("@product", Kw.I().getConfig().getString(product + ".name")).replace("@player", p.getName()));

                for (String cmd: Kw.I().getConfig().getStringList(product + ".commands")) {

                  p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
                }
              }
            }.runTaskLater(Kw.I(), 1L);
          }
        }
      }
    }
  }
}