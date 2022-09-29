package battlebeacons.listenery;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockPoskozen implements Listener {

    @EventHandler
    public void blockPoskozen(BlockDamageEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.BEACON || block.getType() == Material.BLACK_WOOL) {
            return;
        }

        event.setCancelled(true);
    }
}
