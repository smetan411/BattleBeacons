package battlebeacons.teleporter;


import battlebeacons.lobby.Lobby;
import battlebeacons.tymy.Tymy;
import org.bukkit.GameMode;

public class TeleportDoLobby {
    private final Lobby lobby;
    private final Tymy tymy;

    public TeleportDoLobby(Lobby lobby, Tymy tymy) {
        this.lobby = lobby;
        this.tymy = tymy;
    }

    public void teleport() {
        tymy.vratTymy().forEach(tym -> {
            tym.getHraci().forEach(hrac -> {
                var mistovLobby = lobby.nahodneMistoVLobby();
                hrac.setGameMode(GameMode.ADVENTURE);
                hrac.getInventory().clear();
                hrac.setArrowsInBody(0);
                hrac.getActivePotionEffects().clear();
                hrac.teleport(mistovLobby);
            });
        });
    }
}
