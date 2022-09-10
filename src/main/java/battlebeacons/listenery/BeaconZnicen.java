package battlebeacons.listenery;

import battlebeacons.tymy.Tymy;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BeaconZnicen implements Listener {

    private final Tymy tymy;

    public void beaconZnicen(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        tymy.vratTymy().stream()
                .anyMatch(tym -> {
                    tym.getSpawnPoint() )
    }
}
