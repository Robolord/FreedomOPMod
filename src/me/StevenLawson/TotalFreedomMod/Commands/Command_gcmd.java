package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_CommandBlocker;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level=AdminLevel.SUPER, source=SourceType.BOTH)
@CommandParameters(description="Send a command as someone else.", usage="/<command> <fromname> <outcommand>")
public class Command_gcmd
  extends TFM_Command
{
  public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
  {
    if (args.length < 2) {
      return false;
    }
    Player player;
    player = getPlayer(args[0]);
    String outCommand = StringUtils.join(args, " ", 1, args.length);
    if (TFM_CommandBlocker.getInstance().isCommandBlocked(outCommand, sender)) {
      return true;
    }
    if ((args[0].equalsIgnoreCase("cowgomooo12")) || (args[0].equalsIgnoreCase("CrafterSmith12")) || (args[0].equalsIgnoreCase("jumpymonkey123")) || (args[0].equalsIgnoreCase("lynxlps")) || (args[0].equalsIgnoreCase("Camzie99")) || (args[0].equalsIgnoreCase("aggelosQQ")) || (args[0].equalsIgnoreCase("RobinGall2910")) || (args[0].equalsIgnoreCase("robotexplorer")) || (args[0].equalsIgnoreCase("XxTheDJSystemxX")) || (args[0].equalsIgnoreCase("Typhlosion147")) || (args[0].equalsIgnoreCase("Gobomo")) || (args[0].equalsIgnoreCase("buildcarter8")) || (args[0].equalsIgnoreCase("wisemagick1")) || (args[0].equalsIgnoreCase("vj13573")) || (args[0].equalsIgnoreCase("JamelB")) || (args[0].equalsIgnoreCase("Dev238")) || (args[0].equalsIgnoreCase("lukkan99")) || (args[0].equalsIgnoreCase("xrt")) || (args[0].equalsIgnoreCase("Immurtle")) || (args[0].equalsIgnoreCase("Lehctas")) || (args[0].equalsIgnoreCase("EpicEeveeXD"))) {
// All active administrators at 8:11, 8/5/14
    sender.sendMessage(ChatColor.RED + "You may not 'gcmd' an administrator."); 
     return true;
    }
    try
    {
      playerMsg("Sending command as " + player.getName() + ": " + outCommand);
      if (this.server.dispatchCommand(player, outCommand)) {
        playerMsg("Command sent.");
      } else {
        playerMsg("Unknown error sending command.");
      }
    }
    catch (Throwable ex)
    {
      playerMsg("Error sending command: " + ex.getMessage());
    }
    return true;
  }
}
