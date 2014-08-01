package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_CONSOLE)
@CommandParameters(description = "Changes tab name", usage = "/<command> <message...>")
public class Command_tabname extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
    if (!TFM_Util.DEVELOPERS.contains(sender.getName())) 
    {
        if (args.length == 0)
        {
            playerMsg(TotalFreedomMod.FREEDOMOP_MODINVALID);
            return true;
        }
        if (args.length > 0)
        {
            String name = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");
            sender_p.setPlayerListName(name.replaceAll("&", "§"));
        }
        return true;
    }
    else
    {
      playerMsg("Really? Are you kidding me? lolno.");
    }
    return true;
  }
}
