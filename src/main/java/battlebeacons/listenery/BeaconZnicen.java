package battlebeacons.listenery;

import battlebeacons.StavHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BeaconZnicen implements Listener {
    private final Tymy tymy;
    private final StavHry stavHry;

    public BeaconZnicen(Tymy tymy, StavHry stavHry) {
        this.tymy = tymy;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void beaconZnicen(BlockBreakEvent event) {
        if (!stavHry.isGameRunning()) return;
        if (event.getBlock().getType() != Material.BEACON) return;
        Location location = event.getBlock().getLocation();
        for (var tym : tymy.vratTymy()) {
            if (tym.getBeaconPoint().equals(location)) {
                tym.setAlive(false);
                tym.zprava("Vas beacon byl znicen.", "Uz se nesnazte.");
                event.getBlock().getDrops().clear();
                return;
            }
        }
    }
}
