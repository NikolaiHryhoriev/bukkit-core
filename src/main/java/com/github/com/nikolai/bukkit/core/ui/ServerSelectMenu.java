package com.github.com.nikolai.bukkit.core.ui;

import com.github.com.nikolai.bukkit.core.Core;
import com.github.zyypj.tadeuBooter.minecraft.tool.ItemBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ServerSelectMenu implements InventoryProvider {
    @Override
    public void init(Player player, InventoryContents contents) {

        ItemStack bedwars = new ItemBuilder(Material.BED)
                .setDisplayName("§c➜ BedWars")
                .setLore(
                        "§8Sobreviva com seus equipamentos!",
                        "",
                        "§7Proteja sua cama com os itens da forja.",
                        "§7Use-os para comprar e se equipar bem!",
                        "§7Defenda sua base e destrua as camas!",
                        "§7Elimine os oponentes e vença a partida!",
                        "",
                        "§a➜ Clique para jogar!")
                .build();

        ItemStack skywars = new ItemBuilder(Material.EYE_OF_ENDER)
                .setDisplayName("§b➜ SkyWars")
                .setLore(
                        "§8Sobreviva com seus equipamentos!",
                        "",
                        "§7Ao nascer na ilha, abra o baú.",
                        "§7Escolha e equipe os itens disponíveis.",
                        "§7Prepare-se para uma batalha épica!",
                        "§7Derrote os oponentes e vença o jogo!",
                        "",
                        "§a➜ Clique para jogar!"
                )
                .build();

        ItemStack closeItem = new ItemBuilder(Material.INK_SACK)
                .setData((byte) 1)
                .setDisplayName("§cFechar")
                .build();

        contents.set(1, 3, ClickableItem.of(bedwars, e -> {
            player.performCommand("server bedwars");
        }));

        contents.set(1, 5, ClickableItem.of(skywars, e -> {
            player.performCommand("server skywars");
        }));

        contents.set(3, 4, ClickableItem.of(closeItem, e -> {
            player.closeInventory();
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {}

    public static void openServerInventory(Player player) {
        SmartInventory serverInventory = SmartInventory.builder()
                .manager(Core.getInventoryManager())
                .id("lobby")
                .provider(new ServerSelectMenu())
                .size(4, 9)
                .title("§8Escolha seu modo de jogo.")
                .build();

        serverInventory.open(player);
    }
}
