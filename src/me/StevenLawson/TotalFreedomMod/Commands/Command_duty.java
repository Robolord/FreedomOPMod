package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Off duty, on duty.", usage = "/<command> [off | on]")
public class Command_duty extends TFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
      if (args.length == 1)
        {
        if (args[0].equals("off"))
        {
        TFM_Util.adminAction(ChatColor.RED + sender.getName(), "I'm now off duty please contact another admin.", false);
        return true;
        }
        else if (args[0].equals("on"))
        {
      TFM_Util.adminAction(ChatColor.AQUA + sender.getName(), "I'm now on duty you may conatct me now.", false);
      return true;
        }
        }
        return true;
    }
}
