package dev.soostrator.togglebridge;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author SooStrator1136
 */
public final class ToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) return false;

        final Player sendingPlayer = (Player) sender;

        final int ignoreIndex = ToggleBridge.IGNORED_PLAYERS.indexOf(sendingPlayer);
        if (ignoreIndex == -1) {
            ToggleBridge.IGNORED_PLAYERS.add(sendingPlayer);
            sendingPlayer.sendMessage(new TextComponent("§cBridge is now disabled till you type /togglebridge again or the server restarts."));
        } else {
            ToggleBridge.IGNORED_PLAYERS.remove(ignoreIndex);
            sendingPlayer.sendMessage(new TextComponent("§aBridge is now enabled again."));
        }

        return true;
    }

}
