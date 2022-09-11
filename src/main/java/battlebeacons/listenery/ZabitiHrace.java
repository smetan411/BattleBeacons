package battlebeacons.listenery;


import battlebeacons.tymy.Tymy;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ZabitiHrace extends PlayerDamageByPlayerListener {

    private final Tymy tymy;

    public ZabitiHrace(Tymy tymy) {
        this.tymy = tymy;
    }

    @Override
    public void playerDamaged(Player utocnik, Player zraneny, double damage, EntityDamageByEntityEvent event) {
        if (tymy.spoluhraci(utocnik, zraneny)) event.setCancelled(true);
    }
}
