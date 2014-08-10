package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "For the bad bad bad bad bad bad pepole shall meet dahlia.", usage = "/<command> <playername>")
public class Command_dahlia extends TFM_Command
{
    @Override
    public boolean run(final CommandSender sender, final Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
   
           if (!sender.getName().equalsIgnoreCase("lynxlps"))
        {
            playerMsg(TotalFreedomMod.MSG_NO_PERMS);
            return true;
        }
        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(TotalFreedomMod.PLAYER_NOT_FOUND);
            return true;
        }
         TFM_Util.adminAction(sender.getName(), "Making it rain hell over " + player.getName(), true);
         TFM_Util.adminAction(sender.getName(), "And will be destroyed!", true);
         sender_p.chat("Oh yeah, Dahlia is coming.");
         player.chat("NO! PLEASE NO!");
         sender_p.chat("Well sorry, now Dahlia is coming to kill you.");
         player.chat("Dont get rid of me!");
         sender_p.chat("Well, how about this? NO!- Dahlia Hawthrone");
         // im awesome aint i?
         sender.sendMessage(player.getName() + " well, you have sent the player to dahlia, he is gone :)");
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().strikeLightning(player.getLocation());
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          player.getWorld().createExplosion(player.getLocation(), 4F);
          server.dispatchCommand(sender, "orbit" + player.getName());
          
        final String ip = player.getAddress().getAddress().getHostAddress().trim();

        // remove from superadmin
        if (TFM_AdminList.isSuperAdmin(player))
        {
            TFM_Util.adminAction(sender.getName(), "Removing " + player.getName() + " from the superadmin list.", true);
            TFM_AdminList.removeSuperadmin(player);
        }

        // remove from whitelist
        player.setWhitelisted(false);

        // deop
        player.setOp(false);

        // ban IPs
        for (String playerIp : TFM_PlayerList.getEntry(player).getIps())
        {
            TFM_BanManager.addIpBan(new TFM_Ban(playerIp, player.getName()));
        }

        // ban uuid
        TFM_BanManager.addUuidBan(player);
        
         // set gamemode to survival
         player.setGameMode(GameMode.SURVIVAL);
 
         // clear inventory
         player.closeInventory();
         player.getInventory().clear();
 
         // ignite player
         player.setFireTicks(10000);
 
         // generate explosion
         player.getWorld().createExplosion(player.getLocation(), 4F);
 
         // Shoot the player in the sky
         player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
 
         new BukkitRunnable()
         {
            @Override
             public void run()
             {
                 // strike lightning
                 player.getWorld().strikeLightning(player.getLocation());
 
                 // kill (if not done already)
                 player.setHealth(0.0);
                 
                 //Broadcast player is gone
                 TFM_Util.bcastMsg("WOAH! Obliviated by Dahlia?!? Thats amazing!");
                 
                 //says im dead
                 player.chat("Well I'm gone. Dahlia, take me to hell and kill me.");
                 sender_p.chat("Sure. *smiles innocently* You will die from there.");
             }
        }.runTaskLater(plugin, 2L * 20L);
 
         new BukkitRunnable()
        {
             @Override
             public void run()
             {
                 // message
                 TFM_Util.adminAction(sender.getName(), "Banning " + player.getName() + ", IP: " + ip, true);
 
                 // generate explosion
                 player.getWorld().createExplosion(player.getLocation(), 4F);
 
                // kick player
                 player.kickPlayer(ChatColor.RED + "You have been killed by Dahlia Hawthrone. Have a nice day in hell! -Dahlia Hawthrone");
                 TFM_Util.bcastMsg(player.getName() + ", Has met his fate." + ChatColor.DARK_GREEN);
                 sender.sendMessage("You will now recive some CoreProtect spam rollback.");
                 server.dispatchCommand(sender, "co rb u:" + player.getName() + " t:24h r:#global");
            }
        }.runTaskLater(plugin, 3L * 20L);
         return true;
    }
}
