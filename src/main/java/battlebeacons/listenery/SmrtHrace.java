package battlebeacons.listenery;


import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tym;
import battlebeacons.tymy.Tymy;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class SmrtHrace implements Listener {

    private final Tymy tymy;
    private final Skore skore;
    private final TeleportDoLoby teleportDoLoby;
    private final SpravaBloku spravaBloku;

    public SmrtHrace(Tymy tymy, Skore skore, TeleportDoLoby teleportDoLoby, SpravaBloku spravaBloku) {
        this.tymy = tymy;
        this.skore = skore;
        this.teleportDoLoby = teleportDoLoby;
        this.spravaBloku = spravaBloku;
    }

    @EventHandler
    public void smrtHrace(PlayerDeathEvent playerDeathEvent) {
        if (!tymy.hraJede()) return;
        var player = playerDeathEvent.getEntity();
        //vypadne z nej vlna
        playerDeathEvent.getDrops().clear();
        playerDeathEvent.getDrops().add(new ItemStack(Material.BLACK_WOOL, 10));
        //spectator mod pokud uz neni beacon
        var tym = tymy.vratTym(player);
        if (!tym.isAlive()) {
            player.setGameMode(GameMode.SPECTATOR);
            skore.update();
        }

        //test konce hty
        List<Tym> ziveTymy = vratZiveTymy();
        if (ziveTymy.size() <= 1) {
            if (ziveTymy.isEmpty()) {
                //asi nenastane
                tymy.vratTymy().forEach(vsechnyTymy -> vsechnyTymy.zprava("Nikdo nezvitezil", "Remiza poslednich dvou"));
            } else {
                ziveTymy.forEach(zivyTym -> zivyTym.zprava("Vas tym zvitezil", ""));
                tymy.vratTymy().forEach(vsechnyTymy -> vsechnyTymy.zprava("Zvitezit tym " + ziveTymy.get(0).getNastaveniTymu(), ""));
            }
            teleportDoLoby.teleport();
            spravaBloku.znicPolozeneBloky();
            tymy.konecHry();
        }
    }

    private List<Tym> vratZiveTymy() {
        List<Tym> tymySZivymi = new ArrayList<>();
        for (var tym : tymy.vratTymy()) {
            boolean zivyHracVTeamu = tym.getHraci().stream().anyMatch(hrac -> hrac.getGameMode() != GameMode.SPECTATOR);
            if (zivyHracVTeamu) {
                tymySZivymi.add(tym);
            }
        }
        return tymySZivymi;
    }
}
