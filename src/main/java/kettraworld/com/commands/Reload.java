package kettraworld.com.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import kettraworld.com.util.Util;
import kettraworld.com.Kw;

public class Reload implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

      if (sender.hasPermission("kw.reload")) {

        Kw.getPlugin().reloadConfig();
        Kw.getPlugin().getMySQL().reload();

        sender.sendMessage(Util.Cor(Kw.getPlugin().getConfig().getString("message.reload")));

      } else {

        sender.sendMessage(Util.Cor(Kw.getPlugin().getConfig().getString("message.permission")));

      }
      return true;
    }
    return false;

  }
}