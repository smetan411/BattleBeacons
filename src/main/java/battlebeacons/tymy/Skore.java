package battlebeacons.tymy;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class Skore {

    private final Scoreboard scoreboard;
    private final Tymy tymy;

    public Skore(Scoreboard scoreboard, Tymy tymy) {
        this.scoreboard = scoreboard;
        this.tymy = tymy;
    }

    public void inicializace() {
        for (Tym tym : tymy.vratTymy()) {
            Objective objective = scoreboard.registerNewObjective(tym.getJmenoTymu().getJmeno(), "dummy",
                    tym.getJmenoTymu().getChatColor().toString() + ChatColor.STRIKETHROUGH + tym.getJmenoTymu().getJmeno());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            updateObjective(objective);
            tym.vratHrace().forEach(player -> player.setScoreboard(scoreboard));
        }
    }

    public void update() {
        for (var objective: scoreboard.getObjectives()) {
            updateObjective(objective);
            Tym tym = getTymFromObjective(objective);
            if (!tym.isAlive()) {
                objective.setDisplayName(tym.getJmenoTymu().getChatColor() + tym.getJmenoTymu().getJmeno());
            }
        }
    }

    private void updateObjective(Objective objective) {
        Tym tym = getTymFromObjective(objective);
        Score score = objective.getScore("Nazivu:");
        score.setScore((int) tym.vratHrace().stream()
                .filter(hrac -> hrac.getGameMode() != GameMode.SPECTATOR)
                .count());

    }

    private Tym getTymFromObjective(Objective objective) {
        String jmenoTymu = objective.getName();
        return tymy.vratTym(jmenoTymu);
    }
}