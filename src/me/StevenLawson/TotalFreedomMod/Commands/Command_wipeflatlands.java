package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_CONSOLE, blockHostConsole = true)
@CommandParameters(description = "Wipe the flatlands map.   Requires restart after command is used!", usage = "/<command>")
public class Command_wipeflatlands extends TFM_Command
{
    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.setSavedFlag("do_wipe_flatlands", true);

        TFM_Util.bcastMsg("FreedomOP is restarting for to wipe the flatlands!", ChatColor.GRAY);

        for (Player player : server.getOnlinePlayers())
        {
            player.kickPlayer("FreedomOP is restarting to wipe the flatlands, come back in a three minutes :X");
        }

        server.shutdown();

        return true;
    }
}
