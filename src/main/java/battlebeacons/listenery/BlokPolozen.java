package battlebeacons.listenery;

import battlebeacons.StavHry;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlokPolozen implements Listener {

    private final SpravaBloku spravaBloku;

    private final StavHry stavHry;

    public BlokPolozen(SpravaBloku spravaBloku, StavHry stavHry) {
        this.spravaBloku = spravaBloku;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void polozeniBloku(BlockPlaceEvent event) {
        if (!stavHry.isGameRunning()) return;

        Block blok = event.getBlock();
        if (!SpravaBloku.jeBlokPovoleny(blok.getType())) return;
        switch (blok.getType()) {
            case BLACK_WOOL -> polozBlok(blok);
            case END_STONE -> polozBlok(blok);
            case LAVA -> polozBlok(blok);
            case POWDER_SNOW -> polozBlok(blok);
        }
    }

    private void polozBlok(Block blok) {
        if (stavHry.isGameRunning()) {
            spravaBloku.add(blok);
        }
    }
}
