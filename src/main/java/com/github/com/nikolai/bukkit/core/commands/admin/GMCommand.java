package com.github.com.nikolai.bukkit.core.commands.admin;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCommand extends Command {

    // Construtor público para que o comando seja acessível via reflexão
    public GMCommand() {
        super("gm"); // Nome do comando
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        // Verifica se o sender é um jogador
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c➜ Comando apenas para §7players");
            return false;  // Retorna false para indicar que o comando não foi executado corretamente
        }

        Player player = (Player) sender;

        // Verifica se o comando foi chamado com um argumento
        if (args.length == 1) {
            String mode = args[0].toLowerCase(); // Modo de jogo (criativo ou survival)

            // Condicional simples para verificar e definir o GameMode
            if (mode.equals("1") || mode.equals("creative")) {
                player.setGameMode(GameMode.CREATIVE);  // Modo Criativo
                player.sendMessage("§b§lGM ➜ §fVocê entrou no modo §bcriativo");
                return true;
            } else if (mode.equals("0") || mode.equals("survival")) {
                player.setGameMode(GameMode.SURVIVAL);  // Modo Sobrevivência
                player.sendMessage("§b§lGM ➜ §fVocê entrou no modo §asurvival");
                return true;
            } else {
                // Se o argumento não for válido
                player.sendMessage("§b§lGM ➜ §fO comando §acorreto §fé: §7/gm [1|0|creative|survival]");
                return false;
            }
        }

        // Mensagem para quando não houver argumentos
        player.sendMessage("§b§lGM ➜ §fO comando §acorreto §fé: §7/gm [1|0|creative|survival]");
        return false;
    }
}
