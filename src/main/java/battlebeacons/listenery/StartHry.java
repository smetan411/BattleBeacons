package battlebeacons.listenery;


import battlebeacons.StavHry;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.timelimit.TimeLimit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class StartHry implements Listener {

    private final StavHry stavHry;
    private final TimeLimit timeLimit;

    public StartHry(StavHry stavHry, TimeLimit timeLimit) {
        this.stavHry = stavHry;
        this.timeLimit = timeLimit;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        Entity entity = event.getEntity();
        if (TeleportDoAreny.JMENO_TELEPORTERA.equals(entity.getCustomName())) {
            event.setCancelled(true);
            if (!player.isOp()) return;
            stavHry.startGame();
            timeLimit.start();
            for(Player player1: Bukkit.getOnlinePlayers()){
                player1.getEnderChest().clear();
            }
        }
    }
}
