package com.github.com.nikolai.bukkit.core.loaders.api;

import com.github.com.nikolai.bukkit.core.Core;
import com.github.com.nikolai.bukkit.core.loaders.CoreLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreAPI {
    public CoreLoader createLoader(JavaPlugin plugin) {
        return new CoreLoader(plugin);
    }

    public Core core() {
        return Core.getInstance();
    }
}

