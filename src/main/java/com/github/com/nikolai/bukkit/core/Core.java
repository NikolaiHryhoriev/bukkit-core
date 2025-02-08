package com.github.com.nikolai.bukkit.core;

import com.github.com.nikolai.bukkit.core.loaders.CoreLoader;
import com.github.com.nikolai.bukkit.core.loaders.api.CoreAPI;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private static InventoryManager manager;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(
                "§e§lCORE ➜ §fO plugin está §aligado!");
        manager = new InventoryManager(this); manager.init();
        CoreAPI api = new CoreAPI(); instance = this;
        CoreLoader loader = api.createLoader(this);
        manager = new InventoryManager(this); manager.init();
        loader.setupListeners("com.github.com.nikolai.bukkit.core.listeners");
        loader.setupCommands("com.github.com.nikolai.bukkit.core.commands");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(
                "§c§lCORE ➜ §fO plugin está §cdesligado!");
    }

    public static Core getInstance() {
        return instance;
    }

    public static InventoryManager getInventoryManager() {
        return manager;
    }
}
