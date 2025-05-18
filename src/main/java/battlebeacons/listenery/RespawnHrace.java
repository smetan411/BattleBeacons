package battlebeacons.listenery;

import battlebeacons.StavHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnHrace implements Listener {
    private final Tymy tymy;
    private final StavHry stavHry;
    private final Plugin plugin;


    public RespawnHrace(Tymy tymy, StavHry stavHry, Plugin plugin) {
        this.tymy = tymy;
        this.stavHry = stavHry;
        this.plugin = plugin;
    }

    @EventHandler
    public void respawnHrace(PlayerRespawnEvent event) {
        if (!stavHry.isGameRunning()) return;

        var player = event.getPlayer();
        var tym = tymy.vratTym(player);

        player.setGameMode(GameMode.SPECTATOR);
        // Nastav respawn lokaci dočasně třeba na lobby (nebo klidně nech tak, jen ho stejně hned teleportujeme)
        event.setRespawnLocation(tym.getBeaconPoint().add(0,20,0));

        // Po 5 vteřinách (100 ticků) ho přepni zpět do hry
        new BukkitRunnable(){
            @Override
            public void run() {
                // Pokud hra mezitím skončila, nic nedělej
                if(!stavHry.isGameRunning())return;

                player.teleport(tym.getSpawnPoint());
                event.setRespawnLocation(tym.getSpawnPoint());
                player.setGameMode(GameMode.SURVIVAL);
                tym.vybavHrace(player);
            }
        }.runTaskLater(plugin, 100L); // 100 ticku = 5 vterin
    }
}
