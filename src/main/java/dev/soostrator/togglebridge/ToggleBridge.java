package dev.soostrator.togglebridge;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessagePreBroadcastEvent;
import github.scarsz.discordsrv.api.events.Event;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ToggleBridge extends JavaPlugin {

    static final List<Player> IGNORED_PLAYERS = new CopyOnWriteArrayList<>();

    @Override
    public void onEnable() {
        DiscordSRV.api.subscribe(this);

        this.getCommand("togglebridge").setExecutor(new ToggleCommand());
    }

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void onEvent(final Event event) {
        if (event instanceof DiscordGuildMessagePreBroadcastEvent) {
            ((DiscordGuildMessagePreBroadcastEvent) event).getRecipients().removeIf(IGNORED_PLAYERS::contains);
        }
    }

}
