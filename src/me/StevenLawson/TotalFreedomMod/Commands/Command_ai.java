package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "How to become admin", usage = "/<command>", aliases = "admininfo")
public class Command_ai extends TFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        
            playerMsg(ChatColor.AQUA + "The following is accurate as of 7/25/14");
            playerMsg(ChatColor.RED + "Currently applying for Super Admin is impossible");
         /*  playerMsg(ChatColor.GREEN + "To apply for admin you need to go to the forums at http://to.fop.us.to/apply");
            playerMsg(ChatColor.YELLOW + "Then read the requirements in the Super Admin in the \"Admin Application Template\".");
            playerMsg(ChatColor.WHITE + "Then if you feel you are ready, make a new thread in the 'admin applications'' board.");
            playerMsg(ChatColor.BLUE + "And fill out the template in the new thread.");
            playerMsg(ChatColor.RED + "We ask for you not to ask existing admins for recommendations, this will get your application denied.");
            playerMsg(ChatColor.GOLD + "Good Luck!");
        */
            return true;
    }
}
