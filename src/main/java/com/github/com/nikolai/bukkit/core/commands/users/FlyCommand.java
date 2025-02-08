package com.github.com.nikolai.bukkit.core.commands.users;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends Command {

    // Construtor público para garantir que seja instanciado corretamente
    public FlyCommand() {
        super("fly"); // Nome do comando
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        // Verifica se o sender é um jogador
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c➜ Comando apenas para §7players");
            return false;  // Retorna false para indicar que o comando não foi executado corretamente
        }

        Player player = (Player) sender;

        // Verifica se o comando foi chamado sem argumentos
        if (args.length == 0) {
            // Alterna entre ativar e desativar o voo do jogador
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);  // Desativa o voo
                player.sendMessage("§c§lFLY ➜ §fO comando §7fly §festá §cdesativado!");
            } else {
                player.setAllowFlight(true);  // Ativa o voo
                player.sendMessage("§a§lFLY ➜ §fO comando §7fly §festá §aativado!");
            }
            return true;  // Comando executado com sucesso
        } else {
            // Mensagem para o caso de parâmetros não reconhecidos
            sender.sendMessage("§cUso correto: /fly");
            return false;
        }
    }
}
