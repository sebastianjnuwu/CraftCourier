package kettraworld.com.commands;

import kettraworld.com.BR;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

public class Reload implements CommandExecutor {
  
  private BR plugin;

  public Reload(BR plugin) {
    this.plugin = plugin;
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

  if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

  if (sender.hasPermission("kw.reload")) {

    plugin.getMySQL().disconnect();
    plugin.getMySQL().connect();
    sender.sendMessage("Tem permissão");
    sender.sendMessage(plugin.getDescription().getVersion());

   } else {

     sender.sendMessage("sem permissão");

   }
    return true;
   }
   return false;
  }
  
}