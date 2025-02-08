package com.github.com.nikolai.bukkit.core.loaders.register;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class RegisterCommands {


    private final CommandMap commandMap;

    public RegisterCommands() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException("Failed to access command map", e);
        }
    }

    public void attachCommand(JavaPlugin plugin, Command command) {
        commandMap.register(plugin.getName(), command);
    }
}
