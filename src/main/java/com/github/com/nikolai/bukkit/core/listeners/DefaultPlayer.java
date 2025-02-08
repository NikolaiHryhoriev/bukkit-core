package com.github.com.nikolai.bukkit.core.listeners;

import com.github.com.nikolai.bukkit.core.managers.HotbarManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DefaultPlayer implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        HotbarManager.setupHotbar(player);
        player.setMaxHealth(2);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLevelFood(FoodLevelChangeEvent event) {
     event.setFoodLevel(20); event.setCancelled(true);
    }
}
