package battlebeacons.timelimit;

import battlebeacons.StavHry;
import battlebeacons.listenery.StartHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.time.Duration;

public class TimeLimit implements Runnable {

    private final Plugin plugin;
    private final StavHry stavHry;
    private final Tymy tymy;
    private static Duration GAME_DURATION = Duration.ofMinutes(20); // nastaveni delky hry v minutach / zde 2 minuty

    public TimeLimit(Plugin plugin, StavHry stavHry, Tymy tymy) {
        this.plugin = plugin;
        this.stavHry = stavHry;
        this.tymy = tymy;
    }

    public void start()
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, GAME_DURATION.getSeconds() * 20);//20 ticku na sekundu
    }


    @Override
    public void run() {
        tymy.vratTymy().forEach(tym -> tym.zprava("Uplynul casovy limit.", "Konec hry."));
        stavHry.stopGame();
    }
}
