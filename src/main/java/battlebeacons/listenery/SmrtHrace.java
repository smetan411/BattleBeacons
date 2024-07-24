package battlebeacons.listenery;


import battlebeacons.StavHry;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tym;
import battlebeacons.tymy.Tymy;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class SmrtHrace implements Listener {

    private final Tymy tymy;
    private final Skore skore;

    private final StavHry stavHry;

    public SmrtHrace(Tymy tymy, Skore skore, StavHry stavHry) {
        this.tymy = tymy;
        this.skore = skore;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void smrtHrace(PlayerDeathEvent playerDeathEvent) {
        if (!stavHry.isGameRunning()) return;
        var player = playerDeathEvent.getEntity();
        player.getInventory().clear();
        setDrop(playerDeathEvent);
        setSpectatorMode(player);
        ukonciHru();
    }

    private void setDrop(PlayerDeathEvent playerDeathEvent) {
        playerDeathEvent.getDrops().clear();
        playerDeathEvent.getDrops().add(new ItemStack(Material.GOLD_INGOT, 5));
        playerDeathEvent.getDrops().add(new ItemStack(Material.IRON_INGOT, 5));
    }

    private void setSpectatorMode(Player player) {
        var tym = tymy.vratTym(player);
        if (!tym.isAlive()) {
            player.setGameMode(GameMode.SPECTATOR);
            skore.update();
        }
    }

    private void ukonciHru() {
        List<Tym> ziveTymy = getZiveTymy();
        if (ziveTymy.size() > 1) return;
        if (ziveTymy.isEmpty()) {
            //asi nenastane
            tymy.vratTymy().forEach(vsechnyTymy -> vsechnyTymy.zprava("Nikdo nezvitezil", "Remiza poslednich dvou"));
        } else {
            var vitez = ziveTymy.get(0);
            for (Tym tym : tymy.vratTymy()) {
                if (tym.equals(vitez)) {
                    tym.zprava("Vas tym zvitezil", "");
                } else {
                    tym.zprava("Zvitezil tym " + ziveTymy.get(0).getNastaveniTymu(), "");
                }
            }
        }
        stavHry.stopGame();
    }

    private List<Tym> getZiveTymy() {
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
