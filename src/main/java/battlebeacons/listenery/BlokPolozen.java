package battlebeacons.listenery;

import battlebeacons.tymy.Tymy;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlokPolozen implements Listener {

    private final Tymy tymy;
    private final SpravaBloku spravaBloku;

    public BlokPolozen(Tymy tymy, SpravaBloku spravaBloku) {
        this.tymy = tymy;
        this.spravaBloku = spravaBloku;
    }

    @EventHandler
    public void polozeniBloku(BlockPlaceEvent event) {
        Block blok = event.getBlock();
        if (SpravaBloku.jeBlokZakazany(blok.getType())) return;
        switch(blok.getType()) {
            //pokud hrac polozi beacon na beacon point tak ozivi tym a tym muze pokracovat ve hre
            case BEACON -> {
                Location location = blok.getLocation();
                var tymOpt = tymy.vratTymPodleMistaBeaconu(location);
                if (tymOpt.isPresent()) {
                    var tym = tymOpt.get();
                    tym.setAlive(true);
                    tym.zprava("Beacon obnoven", "zivi jsou zachraneni");
                }
            }
            case BLACK_WOOL -> spravaBloku.add(blok);
        }
    }
}
