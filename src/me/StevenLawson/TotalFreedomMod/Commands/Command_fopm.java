package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_CommandBlocker;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_PermbanList;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about FreedomOpMod or reloads it", usage = "/<command> [reload]")
public class Command_fopm extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!TFM_AdminList.isSuperAdmin(sender))
            {
                playerMsg(TotalFreedomMod.MSG_NO_PERMS);
            }

            TFM_AdminList.load();
            TFM_PermbanList.load();
            TFM_PlayerList.load();
            TFM_BanManager.load();
            TFM_CommandBlocker.getInstance().load();


            final String message = String.format("%s v%s.%s reloaded.",
                    TotalFreedomMod.pluginName,
                    TotalFreedomMod.pluginVersion,
                    TotalFreedomMod.buildNumber);

            playerMsg(message);
            TFM_Log.info(message);
            return true;
        }
        playerMsg("Looking for totalfreedommod info? /tfm!", ChatColor.GREEN );
        playerMsg("FreedomOPMod for 'Freedom OP'", ChatColor.GOLD);
        playerMsg(String.format("Version "
                + ChatColor.BLUE + "%s.%s" + ChatColor.GOLD + ", built "
                + ChatColor.BLUE + "%s" + ChatColor.GOLD + " by "
                + ChatColor.BLUE + "%s" + ChatColor.GOLD + ".",
                TotalFreedomMod.pluginVersion,
                TotalFreedomMod.buildNumber,
                TotalFreedomMod.buildDate,
                TotalFreedomMod.buildCreator), ChatColor.GOLD);
        playerMsg("Running on " + TFM_ConfigEntry.SERVER_NAME.getString() + ".", ChatColor.GOLD);
        playerMsg("FOPM Addition created by buildcarter8", ChatColor.GOLD);
        playerMsg("Visit " + ChatColor.AQUA + "http://freedomop.boards.net " + ChatColor.GREEN + " for more information.", ChatColor.GREEN);

        return true;
    }
}
