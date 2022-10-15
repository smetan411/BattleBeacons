package battlebeacons.listenery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class BlockDropEvent implements Listener {

    private final SpravaBloku spravaBloku;

    public BlockDropEvent(SpravaBloku spravaBloku) {
        this.spravaBloku = spravaBloku;
    }

    @EventHandler
    public void onBlockBreak(BlockDropItemEvent event) {
        event.getItems().forEach(spravaBloku::add);
    }
}
