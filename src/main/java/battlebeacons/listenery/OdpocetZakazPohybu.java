package battlebeacons.listenery;

import battlebeacons.StavHry;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.tymy.Tymy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OdpocetZakazPohybu implements Listener {

    private final Tymy tymy;
    private final TeleportDoAreny teleportDoHry;
    private final StavHry stavHry;

    public OdpocetZakazPohybu(Tymy tymy, TeleportDoAreny teleportDoHry, StavHry stavHry) {
        this.tymy = tymy;
        this.teleportDoHry = teleportDoHry;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void zakazPohybu(PlayerMoveEvent event) {
        if (!stavHry.isGameRunning()) return;
        if (teleportDoHry.jeOdpocet()) {
            Player player = event.getPlayer();

//  varianta          for (Tym tym : tymy.vratTymy()) {
//                if (tym.patriDoTymu(player)) {
//                    event.setCancelled(true);
//                    return;
//                }
//            }

            boolean jeVTymu = tymy.vratTymy()
                    .stream()
                    .anyMatch(tym -> tym.patriDoTymu(player));
            if (jeVTymu) {
                event.setCancelled(true);
            }
        }
    }
}
