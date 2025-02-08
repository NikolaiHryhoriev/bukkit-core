package com.github.com.nikolai.bukkit.core.commands.server;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends Command {

    public InfoCommand() {
        super("core");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c➜ Comando apenas para §7players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§e§lINFO ➜ §fDesenvolvido por §7nast.");
            return true;
        }
    return true;
    }
}
