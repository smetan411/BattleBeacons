package battle_beacons.tymy;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class SkoreTymu {

    private final Scoreboard scoreboard;
    private final Tymy tymy;
    private final Objective objective;

    public SkoreTymu(Scoreboard scoreboard, Tymy tymy) {
        this.scoreboard = scoreboard;
        this.tymy = tymy;
        objective = scoreboard.registerNewObjective("test", "dummy", "Skore");
    }

    public void inicializace() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        update();
        for (Tym tym : tymy.vratTymy()) {
            tym.vratHrace().forEach(player -> player.setScoreboard(scoreboard));
        }
    }

    public void update() {
        for (Tym tym : tymy.vratTymy()) {
            Score skore = objective.getScore(tym.getJmenoTymu().getChatColor() + tym.getJmenoTymu().getJmeno());
            skore.setScore(tym.pocetKillu);
        }
    }
}
