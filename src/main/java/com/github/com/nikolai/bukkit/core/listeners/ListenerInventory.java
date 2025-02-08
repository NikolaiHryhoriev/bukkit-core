package com.github.com.nikolai.bukkit.core.listeners;

import com.github.com.nikolai.bukkit.core.ui.LobbySelectMenu;
import com.github.com.nikolai.bukkit.core.ui.UserProfileMenu;
import com.github.com.nikolai.bukkit.core.ui.ServerSelectMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ListenerInventory implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();

        if (item == null || e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        switch (item.getType()) {
            case NETHER_STAR:
                LobbySelectMenu.openLobbyInventory(player);
                break;
            case SKULL_ITEM:
                UserProfileMenu.openUserInventory(player);
                break;
            case COMPASS:
                ServerSelectMenu.openServerInventory(player);
                break;
            default:
                break;
        }
    }
}
