package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level=AdminLevel.SUPER, source=SourceType.BOTH)
@CommandParameters(description="Runs the cleanup system.", usage="/<command>")
public class Command_purge extends TFM_Command
{
  public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
  {
    this.server.dispatchCommand(sender, "purgeall");
    this.server.dispatchCommand(sender, "rd");
    this.server.dispatchCommand(sender, "uall");
    this.server.dispatchCommand(sender, "mp");
    this.server.dispatchCommand(sender, "potion clearall");
    this.server.dispatchCommand(sender, "setl");
    this.server.dispatchCommand(sender, "invis smite");
    
    return true;
  }
}
