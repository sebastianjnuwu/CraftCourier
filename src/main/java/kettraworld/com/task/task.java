package kettraworld.com.task;

import org.bukkit.Bukkit;
import kettraworld.com.Kw;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import org.bukkit.scheduler.BukkitRunnable;

public class task {
 
  Connection con = Kw.I().sql.MySQL();
  
 public task() throws SQLException {

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
			
		if (Kw.I().getConfig().isList(product + ".commands")) {
		
		  sold.setInt(1, productCode);
		  sold.executeUpdate();

		  update.setString(1, code);
			update.executeUpdate();
		  
		new BukkitRunnable() {	
			@Override
			public void run() {
					for (String cmd : Kw.I().getConfig().getStringList(product + ".commands")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
					}
				}	
			
				}.runTaskLater(Kw.I(), 1L);
			}
		}
    }
 }
}