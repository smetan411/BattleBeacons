package battlebeacons.listenery;

import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Tym;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import battlebeacons.tymy.Tymy;

import java.util.ArrayList;
import java.util.List;

public class RespawnHrace implements Listener {
    private final Tymy tymy;
    private final TeleportDoLoby teleportDoLoby;

    public RespawnHrace(Tymy tymy, TeleportDoLoby teleportDoLoby) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
    }

    @EventHandler
    public void respawnHrace(PlayerRespawnEvent event) {
        if (!tymy.hraJede()) return;
        var player = event.getPlayer();
        var tym = tymy.vratTym(player);
        if (jeKonec()) {
            teleportDoLoby.teleport();
            return;
        }
        if (!tym.isAlive()) {
            player.setGameMode(GameMode.SPECTATOR);
        }
        player.teleport(tym.getSpawnPoint());
        event.setRespawnLocation(tym.getSpawnPoint());
    }

    private boolean jeKonec() {
        List<Tym> tymySZivymi = new ArrayList<>();
        for (var tym : tymy.vratTymy()) {
           boolean zivyHracVTeamu = tym.vratHrace().stream().anyMatch(hrac -> hrac.getGameMode() != GameMode.SPECTATOR);
           if (zivyHracVTeamu) {
               tymySZivymi.add(tym);
           }
        }
        if (tymySZivymi.size() == 1) {
            tymySZivymi.get(0).zprava("Vas tym zvitezil.", "Jste nejlepsi");
        }
        return tymySZivymi.size() == 1;
    }
}
