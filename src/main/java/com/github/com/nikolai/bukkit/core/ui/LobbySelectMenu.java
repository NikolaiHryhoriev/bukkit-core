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

public class LobbySelectMenu implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        ItemStack lobby1 = new ItemBuilder(Material.GRASS)
                .setDisplayName("§aLobby #01")
                .setLore("§7Escolha o Lobby #01")
                .build();

        ItemStack lobby2 = new ItemBuilder(Material.GRASS)
                .setDisplayName("§aLobby #02")
                .setLore("§7Escolha o Lobby #02")
                .build();

        ItemStack closeItem = new ItemBuilder(Material.INK_SACK)
                .setData((byte) 1)
                .setDisplayName("§cFechar")
                .build();

        contents.set(1, 1, ClickableItem.of(lobby1, e -> {
            player.performCommand("server lobby1");
        }));

        contents.set(1, 2, ClickableItem.of(lobby2, e -> {
            player.performCommand("server lobby2");
        }));

        contents.set(3, 4, ClickableItem.of(closeItem, e -> {
            player.closeInventory();
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {}

    public static void openLobbyInventory(Player player) {
        SmartInventory lobbyInventory = SmartInventory.builder()
                .manager(Core.getInventoryManager())
                .id("lobby")
                .provider(new LobbySelectMenu())
                .size(4, 9)
                .title("§8Escolha seus lobbies")
                .build();

        lobbyInventory.open(player);
    }
}