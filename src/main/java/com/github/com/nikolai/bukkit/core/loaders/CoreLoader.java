package com.github.com.nikolai.bukkit.core.loaders;


import com.github.com.nikolai.bukkit.core.loaders.register.RegisterCommands;
import com.github.com.nikolai.bukkit.core.loaders.utils.ClassScanner;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class CoreLoader {
    private final JavaPlugin plugin;
    private final RegisterCommands commandRegister;

    public CoreLoader(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandRegister = new RegisterCommands();
    }

    public void setupCommands(String packageName) {
        loadComponents(packageName, ComponentType.COMMAND);
    }

    public void setupListeners(String packageName) {
        loadComponents(packageName, ComponentType.LISTENER);
    }

    public void setupAll(String packageName) {
        loadComponents(packageName, ComponentType.ALL);
    }

    private void loadComponents(String packageName, ComponentType componentType) {
        if (plugin == null || packageName == null) {
            throw new IllegalArgumentException("Plugin and package name must not be null!");
        }

        for (Class<?> clazz : ClassScanner.fetchClassesFromPackage(plugin, packageName)) {
            try {
                initializeComponent(clazz, componentType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeComponent(Class<?> clazz, ComponentType componentType) throws InstantiationException, IllegalAccessException {
        Class<?> targetType = componentType.getTargetType();

        if (!clazz.getName().contains("$") && (targetType == null || targetType.isAssignableFrom(clazz))) {
            Object instance = clazz.newInstance();

            if (instance instanceof Listener) {
                plugin.getLogger().info("Listener initialized: " + clazz.getName());
                Bukkit.getPluginManager().registerEvents((Listener) instance, plugin);
            } else if (instance instanceof Command) {
                Command command = (Command) instance;
                plugin.getLogger().info("Command initialized: " + command.getName());
                commandRegister.attachCommand(plugin, command);
            }
        }
    }

    public enum ComponentType {
        COMMAND(Command.class), LISTENER(Listener.class), ALL(null);

        private final Class<?> targetType;

        ComponentType(Class<?> targetType) {
            this.targetType = targetType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }
    }
}
