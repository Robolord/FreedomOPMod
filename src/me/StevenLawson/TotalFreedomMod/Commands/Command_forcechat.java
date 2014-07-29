package me.StevenLawson.TotalFreedomMod.Commands;


import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_CONSOLE, blockHostConsole = true)
@CommandParameters(description = "Makes someone talk.", usage = "/<command> <playername> <message>")
public class Command_forcechat extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (TFM_AdminList.isSuperAdmin(sender))
            {
                if (args.length == 1)
                {
                    sender.sendMessage(ChatColor.RED + "Usage: /forcechat <player> <message...>");
                    return true;
                }

                final Player player;
                player = getPlayer(args[1]);

                String message = "";
                for (int i = 2; i < args.length; i++)
                {
                    if (i > 2)
                    {
                        message += " ";
                    }
                    message += args[i];
                }

                if (message.startsWith("/"))
                {
                    sender.sendMessage(ChatColor.RED + "You cannot start with a command, please use /gcmd for commands.");
                }
                else
                {
                    player.chat(message);
                }

                return true;
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }
        return true;
    }
}