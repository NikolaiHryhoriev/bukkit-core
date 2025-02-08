package com.github.com.nikolai.bukkit.core.ui;

import com.github.com.nikolai.bukkit.core.Core;
import com.github.zyypj.tadeuBooter.minecraft.tool.ItemBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import fr.minuskube.inv.SmartInventory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserProfileMenu implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        long lastPlayed = player.getLastPlayed();
        String lastLogin = (lastPlayed == 0) ? "Nunca!" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(lastPlayed));

        ItemStack usuario = new ItemBuilder(Material.SKULL_ITEM)
                .setSkullOwner(player.getName())
                .setData((byte) 3)
                .setDisplayName("§a➜ Seu Perfil")
                .setLore("", "§fSeu nome: §7" + player.getDisplayName(), "§fÚltimo login: §7" + lastLogin)
                .build();

        ItemStack discord = new ItemBuilder(Material.SKULL_ITEM)
                .setSkullOwner(player.getName())
                .setData((byte) 3)
                .setSkullValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY4NjViYjg4ZjU2Y2UwMTBhOGQ5YWVhYWNlNDRhMmRkY2QzZDYzMTdhZWQ4OTkwYjQxYjRmZmEwMzk4MzZjMyJ9fX0=")
                .setDisplayName("§b➜ Discord")
                .setLore("§7Clique entre em nosso §bDiscord.")
                .build();

        ItemStack close = new ItemStack(Material.INK_SACK);
        close.setDurability((byte) 1);
        ItemStack closeItem = new ItemBuilder(Material.INK_SACK)
                .setData((byte) 1)
                .setDisplayName("§cSair")
                .build();

        contents.set(1, 2, ClickableItem.of(usuario, e -> {
        }));

        contents.set(2, 2, ClickableItem.of(discord, e -> {
            player.sendMessage("§b§lDISCORD ➜ §fNosso §bDiscord §fé: §7discord.gg/seu-discord");
        }));

        contents.set(3, 4, ClickableItem.of(closeItem, e -> {
            player.closeInventory();
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {}

    public static void openUserInventory(Player player) {
        SmartInventory userInventory = SmartInventory.builder()
                .manager(Core.getInventoryManager())
                .id("userProfile")
                .provider(new UserProfileMenu())
                .size(4, 9)
                .title("Seu Perfil")
                .build();
        userInventory.open(player);
    }
}
