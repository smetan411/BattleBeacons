package battlebeacons.listenery;


import battlebeacons.teleporter.TeleportDoAreny;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class StartHry implements Listener {

    private final TeleportDoAreny teleport;

    public StartHry(TeleportDoAreny teleport) {
        this.teleport = teleport;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (!player.isOp()) return;

        Entity entity = event.getEntity();
        if (TeleportDoAreny.JMENO_TELEPORTERA.equals(entity.getCustomName())) {
            teleport.teleportPriStartuHry();
        }
    }
}