package me.StevenLawson.TotalFreedomMod.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Toggle DisguiseCraft", usage = "/<command>")
public class Command_dtoggle extends TFM_Command
{
@Override
public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
{
server.dispatchCommand(sender, "toggle disguisecraft");
return true;
}
}
