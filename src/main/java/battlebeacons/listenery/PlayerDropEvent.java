package battlebeacons.listenery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropEvent implements Listener {

    private final SpravaBloku spravaBloku;

    public PlayerDropEvent(SpravaBloku spravaBloku) {
        this.spravaBloku = spravaBloku;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        spravaBloku.add(event.getItemDrop());
    }
}
