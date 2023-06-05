package kettraworld.com.execute;

import org.bukkit.Sound;
import org.bukkit.Bukkit;
import kettraworld.com.Kw;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import kettraworld.com.util.Util;
import java.sql.PreparedStatement;
import org.bukkit.scheduler.BukkitRunnable;

public class delivery {

  Connection con = Kw.getPlugin().getMySQL().getConnection();

  public delivery() throws SQLException {

    PreparedStatement check = con.prepareStatement("SELECT * FROM TRANSACTIONs WHERE nick = ? and status = '1'");

    PreparedStatement update = con.prepareStatement("UPDATE TRANSACTIONs SET status = '3' WHERE TRANSACTIONs.uuid = ?");

    for (Player p: Bukkit.getOnlinePlayers()) {
      check.setString(1, p.getName());
      ResultSet rs = check.executeQuery();

      if (rs.next()) {
        String code = rs.getString("uuid");
        int productCode = rs.getInt("id_product");
        String product = Integer.toString(productCode);

        if (Kw.getPlugin().getConfig().isList(product + ".commands")) {

          boolean inventoryFull = p.getInventory().firstEmpty() == -1;

          boolean inventoryFlag = Kw.getPlugin().getConfig().getBoolean(product + ".inventory");

          if (inventoryFlag && inventoryFull) {
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            p.sendMessage(Util.Cor(Kw.getPlugin().getConfig().getString("message.inventory").replace("@product", Kw.getPlugin().getConfig().getString(product + ".name"))));

          } else {
            update.setString(1, code);
            update.executeUpdate();

            new BukkitRunnable() {
              @Override
              public void run() {

                p.sendMessage(Util.Cor(Kw.getPlugin().getConfig().getString(product + ".message").replace("@product", Kw.getPlugin().getConfig().getString(product + ".name"))));

                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

                Util.sendHook(Kw.getPlugin().getConfig().getString("webhook.message").replace("@product", Kw.getPlugin().getConfig().getString(product + ".name")).replace("@player", p.getName()));


                for (String cmd: Kw.getPlugin().getConfig().getStringList(product + ".commands")) {

                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
                }
              }
            }.runTaskLater(Kw.getPlugin(), 1L);
          }
        }
      }
    }
  }
}