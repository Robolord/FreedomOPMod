package me.StevenLawson.TotalFreedomMod.Listener;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

public class TFM_BlockListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBurn(BlockBurnEvent event)
    {
        if (!TFM_ConfigEntry.ALLOW_FIRE_SPREAD.getBoolean())
        {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockIgnite(BlockIgniteEvent event)
    {
        if (!TFM_ConfigEntry.ALLOW_FIRE_PLACE.getBoolean())
        {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event)
    {
        final Player player = event.getPlayer();
        final Location location = event.getBlock().getLocation();

        if (TFM_ConfigEntry.NUKE_MONITOR_ENABLED.getBoolean())
        {
            final TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(player);

            final Location playerLocation = player.getLocation();

            final double nukeMonitorRange = TFM_ConfigEntry.NUKE_MONITOR_RANGE.getDouble().doubleValue();

            boolean outOfRange = false;
            if (!playerLocation.getWorld().equals(location.getWorld()))
            {
                outOfRange = true;
            }
            else if (playerLocation.distanceSquared(location) > (nukeMonitorRange * nukeMonitorRange))
            {
                outOfRange = true;
            }

            if (outOfRange)
            {
                if (playerdata.incrementAndGetFreecamDestroyCount() > TFM_ConfigEntry.FREECAM_TRIGGER_COUNT.getInteger())
                {
                    TFM_Util.bcastMsg(player.getName() + " has been flagged for possible Extended Range Breaking.", ChatColor.RED);
                    TFM_Util.autoEject(player, "Extended range block breaking is not allowed on FreedomOP!");

                    playerdata.resetFreecamDestroyCount();

                    event.setCancelled(true);
                    return;
                }
            }

            final Long lastRan = TFM_Heartbeat.getLastRan();
            if (lastRan == null || lastRan + TotalFreedomMod.HEARTBEAT_RATE * 1000L < System.currentTimeMillis())
            {
                // TFM_Log.warning("Heartbeat service timeout - can't check block place/break rates.");
            }
            else
            {
                if (playerdata.incrementAndGetBlockDestroyCount() > TFM_ConfigEntry.NUKE_MONITOR_COUNT_BREAK.getInteger())
                {
                    TFM_Util.bcastMsg(player.getName() + " is breaking blocks too fast!", ChatColor.RED);
                    TFM_Util.autoEject(player, "You are breaking blocks too fast!  Nukers aren't allowed on FreedomOP!");

                    playerdata.resetBlockDestroyCount();

                    event.setCancelled(true);
                    return;
                }
            }
        }

        if (TFM_ConfigEntry.PROTECTAREA_ENABLED.getBoolean())
        {
            if (!TFM_AdminList.isSuperAdmin(player))
            {
                if (TFM_ProtectedArea.isInProtectedArea(location))
                {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        Location blockLocation = event.getBlock().getLocation();

        if (TFM_ConfigEntry.NUKE_MONITOR_ENABLED.getBoolean())
        {
            TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(player);

            Location playerLocation = player.getLocation();

            double nukeMonitorRange = TFM_ConfigEntry.NUKE_MONITOR_RANGE.getDouble().doubleValue();

            boolean outOfRange = false;
            if (!playerLocation.getWorld().equals(blockLocation.getWorld()))
            {
                outOfRange = true;
            }
            else if (playerLocation.distanceSquared(blockLocation) > (nukeMonitorRange * nukeMonitorRange))
            {
                outOfRange = true;
            }

            if (outOfRange)
            {
                if (playerdata.incrementAndGetFreecamPlaceCount() > TFM_ConfigEntry.FREECAM_TRIGGER_COUNT.getInteger())
                {
                    TFM_Util.bcastMsg(player.getName() + " has been flagged for possible Extended Range placement.", ChatColor.RED);
                    TFM_Util.autoEject(player, "Extended range building is not allowed on FreedomOP!");

                    playerdata.resetFreecamPlaceCount();

                    event.setCancelled(true);
                    return;
                }
            }

            Long lastRan = TFM_Heartbeat.getLastRan();
            if (lastRan == null || lastRan + TotalFreedomMod.HEARTBEAT_RATE * 1000L < System.currentTimeMillis())
            {
                //TFM_Log.warning("Heartbeat service timeout - can't check block place/break rates.");
            }
            else
            {
                if (playerdata.incrementAndGetBlockPlaceCount() > TFM_ConfigEntry.NUKE_MONITOR_COUNT_PLACE.getInteger())
                {
                    TFM_Util.bcastMsg(player.getName() + " is placing blocks too fast!", ChatColor.RED);
                    TFM_Util.autoEject(player, "You are placing blocks too fast!  Build hacks aren't allowed due to server issues.");

                    playerdata.resetBlockPlaceCount();

                    event.setCancelled(true);
                    return;
                }
            }
        }

        if (TFM_ConfigEntry.PROTECTAREA_ENABLED.getBoolean())
        {
            if (!TFM_AdminList.isSuperAdmin(player))
            {
                if (TFM_ProtectedArea.isInProtectedArea(blockLocation))
                {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        switch (event.getBlockPlaced().getType())
        {
            case LAVA:
            case STATIONARY_LAVA:
            {
                if (TFM_ConfigEntry.ALLOW_LAVA_PLACE.getBoolean())
                {
                    TFM_Log.info(String.format("%s placed lava @ %s", player.getName(), TFM_Util.formatLocation(event.getBlock().getLocation())));

                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                }
                else
                {
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.COOKIE, 1));
                    player.sendMessage(ChatColor.GRAY + "Lava placement is not tolerated, ask an admin to place lava for you!");
                    
                    event.setCancelled(true);
                }
                break;
            }
            case WATER:
            case STATIONARY_WATER:
            {
                if (TFM_ConfigEntry.ALLOW_WATER_PLACE.getBoolean())
                {
                    TFM_Log.info(String.format("%s placed water @ %s", player.getName(), TFM_Util.formatLocation(event.getBlock().getLocation())));

                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                }
                else
                {
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.COOKIE, 1));
                    player.sendMessage(ChatColor.GRAY + "Water placement is currently disabled.");

                    event.setCancelled(true);
                }
                break;
            }
            case FIRE:
            {
                if (TFM_ConfigEntry.ALLOW_FIRE_PLACE.getBoolean())
                {
                    TFM_Log.info(String.format("%s placed fire @ %s", player.getName(), TFM_Util.formatLocation(event.getBlock().getLocation())));

                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                }
                else
                {
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.COOKIE, 1));
                    player.sendMessage(ChatColor.GRAY + "Fire placement is disabled due to client side lag, please contact administrators to place fire for you.");

                    event.setCancelled(true);
                }
                break;
            }
            case TNT:
            {
                if (TFM_ConfigEntry.ALLOW_EXPLOSIONS.getBoolean())
                {
                    TFM_Log.info(String.format("%s placed TNT @ %s", player.getName(), TFM_Util.formatLocation(event.getBlock().getLocation())));

                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                }
                else
                {
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.COOKIE, 1));

                    player.sendMessage(ChatColor.GRAY + "TNT placement isn't allowed due to server lag issues!");
                    event.setCancelled(true);
                }
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onRollbackBlockBreak(BlockBreakEvent event)
    {
        if (!TFM_AdminList.isSuperAdmin(event.getPlayer()))
        {
            TFM_RollbackManager.blockBreak(event);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onRollbackBlockPlace(BlockPlaceEvent event)
    {
        if (!TFM_AdminList.isSuperAdmin(event.getPlayer()))
        {
            TFM_RollbackManager.blockPlace(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockFromTo(BlockFromToEvent event)
    {
        if (!TFM_ConfigEntry.ALLOW_FLUID_SPREAD.getBoolean())
        {
            event.setCancelled(true);
        }
    }
}
