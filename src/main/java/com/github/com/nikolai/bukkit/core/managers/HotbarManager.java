package com.github.com.nikolai.bukkit.core.managers;

import com.github.zyypj.tadeuBooter.minecraft.tool.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HotbarManager {

       public static void setupHotbar (Player player) {
           ItemStack setUserItem = new ItemBuilder(Material.SKULL_ITEM)
                   .setSkullOwner(player.getName())
                   .setData((byte) 3)
                   .setDisplayName("§aPerfil")
                   .setLore("§7Clique para ver seu perfil")
                   .build();
           player.getInventory().setItem(1, setUserItem);


           ItemStack setLobbyItem = new ItemBuilder(Material.NETHER_STAR)
                   .setDisplayName("§aLobby")
                   .setLore("§7Clique para escolher seu Lobby.")
                   .build();
           player.getInventory().setItem(6, setLobbyItem);

           ItemStack setServerItem = new ItemBuilder(Material.COMPASS)
                   .setDisplayName("§aServidor")
                   .setLore("§7Clique para escolher seu modo de jogo.")
                   .build();
           player.getInventory().setItem(7, setServerItem);
       }
}
