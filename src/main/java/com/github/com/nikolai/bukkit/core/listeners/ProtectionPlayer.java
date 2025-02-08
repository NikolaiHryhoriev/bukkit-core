package com.github.com.nikolai.bukkit.core.listeners;

import com.google.common.collect.ImmutableList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import org.bukkit.event.Listener;

public class ProtectionPlayer implements Listener {

    private static final ImmutableList<String> BLOCKED_COMMANDS = ImmutableList.of(
            "/pl",
            "/plugins",
            "/ver",
            "/version",
            "/about",
            "/help"

    );

    @EventHandler
    private void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) return;

        if (!event.getPlayer().hasPermission("block.command.bypass") && BLOCKED_COMMANDS.contains(event.getMessage())) {
            event.setCancelled(true);
        }
    }
}
