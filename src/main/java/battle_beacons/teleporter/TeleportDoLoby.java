package battle_beacons.teleporter;


import battle_beacons.lobby.Lobby;
import battle_beacons.tymy.Tymy;

public class TeleportDoLoby {
    private final Lobby lobby;
    private final Tymy tymy;

    public TeleportDoLoby(Lobby lobby, Tymy tymy) {
        this.lobby = lobby;
        this.tymy = tymy;
    }

    public void teleport() {
        tymy.vratTymy().forEach( tym -> {
            tym.vratHrace().forEach(hrac -> {
                var mistovLoby = lobby.nahodneMistoVLobby();
                hrac.teleport(mistovLoby);
            });
        });
    }
}
