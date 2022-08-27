package battle_beacons.listenery;


import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import battle_beacons.teleporter.TeleportDoAreny;
import battle_beacons.teleporter.TeleportDoLoby;
import battle_beacons.tymy.SkoreTymu;
import battle_beacons.tymy.Tym;
import battle_beacons.tymy.Tymy;

public class ZabitiNepritele extends PlayerDamageByPlayerListener {

    public static final int limit = 10;
    private final Tymy tymy;
    private final TeleportDoLoby teleportDoLoby;
    private final TeleportDoAreny teleportDoAreny;
    private final SkoreTymu skoreTymu;

    public ZabitiNepritele(Tymy tymy, TeleportDoLoby teleportDoLoby, TeleportDoAreny teleportDoAreny, SkoreTymu skoreTymu) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
        this.skoreTymu = skoreTymu;
        this.teleportDoAreny = teleportDoAreny;
    }

    @Override
    public void playerDamaged(Player utocnik, Player zraneny, double damage, EntityDamageByEntityEvent event) {
        if (tymy.spoluhraci(utocnik, zraneny)) event.setCancelled(true);
        if (zraneny.getHealth() < damage) {
            for (Tym tym: tymy.vratTymy()) {
                if (tym.vratHrace().contains(utocnik)) {
                    tym.pocetKillu++;
                    skoreTymu.update();
                    if (tym.pocetKillu >= limit) {
                        konecHry(tym);
                    }
                }
            }
        }
    }

    private void konecHry(Tym viteznyTym) {
        teleportDoLoby.teleport();
        tymy.vratTymy().forEach(tymZ -> {
            if (tymZ.getJmenoTymu() == viteznyTym.getJmenoTymu()) {
                tymZ.zprava("Game Over", "You Win");
            } else {
                tymZ.zprava("Game Over", "You Lost");
            }
        });
        tymy.clear();
    }
}
