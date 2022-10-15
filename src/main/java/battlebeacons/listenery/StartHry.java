package battlebeacons.listenery;


import battlebeacons.StavHry;
import battlebeacons.teleporter.TeleportDoAreny;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class StartHry implements Listener {

    private final StavHry stavHry;

    public StartHry(StavHry stavHry) {
        this.stavHry = stavHry;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        Entity entity = event.getEntity();
        if (TeleportDoAreny.JMENO_TELEPORTERA.equals(entity.getCustomName())) {
            event.setCancelled(true);
            if (!player.isOp()) return;
            stavHry.startGame();
        }
    }
}