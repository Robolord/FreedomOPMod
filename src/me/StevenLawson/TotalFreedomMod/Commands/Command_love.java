package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Makes someone love another <3", usage = "/<command> <playername>")
public class Command_love extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
          sender_p.chat("I love you.");
          sender_p.chat("Well <3.");
          sender_p.chat("Lets just have fun together :).");
          sender_p.chat("Ok. <3.");
        }
        return true;
    }
}