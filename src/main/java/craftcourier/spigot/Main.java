package craftcourier.spigot;

import craftcourier.spigot.database.MySQL;
import craftcourier.spigot.database.SQLite;
import craftcourier.spigot.help.Help;
import craftcourier.spigot.event.nLoginListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  
  public Metrics metrics;
  public Help help;
  public MySQL sql;
  public SQLite sqlite;
  
  @Override
  public void onEnable() {
  
    saveDefaultConfig();
    reloadConfig();
    metrics = new Metrics(this, 23043);
    help = new Help(this);
    help.Console("&aInitializing...");
    help.Console(
    "\n&a" +
    "  ___  __   _  _  ____  __  ____  ____ \n" +
    " / __)/  \\ / )( \\(  _ \\(  )(  __)(  _ \\\n" +
    "( (__(  O )) \\/ ( )   / )(  ) _)  )   /\n" +
    " \\___)\\__/ \\____/(__\\_)(__)(____)(__\\_)\n" +
    "                                       \n" +
    "  &a• GitHub: &ehttps://github.com/sebastianjnuwu/craftcourier" + "\n" +
    "  &a• Author: &esebastianjnuwu <sebastianjnuwu@gmail.com>" + "\n" +
    "  &a• Version: &ev" + getDescription().getVersion() + "\n");
    sqlite = new SQLite(this);
    sql = new MySQL(this);
    Help.Check(getDescription().getVersion());
    sql.connect();

   getServer().getPluginManager().registerEvents(new nLoginListener(this), this);
   
  }

  @Override
  public void onDisable() {
    sql.disconnect();
    help.Console("&cClosing");
  }

}