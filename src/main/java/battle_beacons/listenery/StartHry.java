package battle_beacons.listenery;


import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import battle_beacons.teleporter.TeleportDoAreny;
import battle_beacons.tymy.SkoreTymu;

public class StartHry implements Listener {

    private final TeleportDoAreny teleport;
    private final SkoreTymu skoreTymu;

    public StartHry(TeleportDoAreny teleport, SkoreTymu skoreTymu) {
        this.teleport = teleport;
        this.skoreTymu = skoreTymu;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (!player.isOp()) return;

        Entity entity = event.getEntity();
        if (TeleportDoAreny.JMENO_TELEPORTERA.equals(entity.getCustomName())) {
            teleport.teleportPriStartuHry();
            skoreTymu.inicializace();
        }
    }
}