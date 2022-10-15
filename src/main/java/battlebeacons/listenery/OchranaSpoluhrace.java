package battlebeacons.listenery;


import battlebeacons.StavHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OchranaSpoluhrace extends PlayerDamageByPlayerListener {

    private final Tymy tymy;
    private final StavHry stavHry;

    public OchranaSpoluhrace(Tymy tymy, StavHry stavHry) {
        this.tymy = tymy;
        this.stavHry = stavHry;
    }

    @Override
    public void playerDamaged(Player utocnik, Player zraneny, double damage, EntityDamageByEntityEvent event) {
        if (!stavHry.isGameRunning()) return;
        if (tymy.spoluhraci(utocnik, zraneny)) {
            event.setCancelled(true);
        }
    }
}
