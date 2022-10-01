package battlebeacons.listenery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlokPoskozen implements Listener {

    @EventHandler
    public void blockPoskozen(BlockDamageEvent event) {
        if (SpravaBloku.jeBlokZakazany(event.getBlock().getType())) event.setCancelled(true);
    }
}
