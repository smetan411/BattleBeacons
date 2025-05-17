package battlebeacons.listenery;

import battlebeacons.StavHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnHrace implements Listener {
    private final Tymy tymy;
    private final StavHry stavHry;


    public RespawnHrace(Tymy tymy, StavHry stavHry) {
        this.tymy = tymy;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void respawnHrace(PlayerRespawnEvent event) {
        if (!stavHry.isGameRunning()) return;

        var player = event.getPlayer();
        var tym = tymy.vratTym(player);
        tym.vybavHrace(player);
        player.teleport(tym.getSpawnPoint());
        event.setRespawnLocation(tym.getSpawnPoint());
    }

}
