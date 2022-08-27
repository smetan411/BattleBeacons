package battle_beacons.teleporter;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import battle_beacons.lobby.Lobby;
import battle_beacons.tymy.Tym;
import battle_beacons.tymy.Tymy;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public final class TeleportDoAreny {

    private static final String TEAM_LOCATION_CONFIG_KEY = "TeamBattleLocation_";
    public static final String JMENO_TELEPORTERA = "Team Battle Teleporter";

    private final Plugin plugin;
    private final Lobby lobby;
    private final Tymy tymy;
    private Odpocet odpocet = null;

    public TeleportDoAreny(Plugin plugin, Lobby lobby, Tymy tymy) {
        this.plugin = plugin;
        this.lobby = lobby;
        this.tymy = tymy;
    }

    public void teleportPriStartuHry() {
        List<Player> lobbyPlayers = lobby.hraciVLobby();
        List<Location> mista = cilTeleportu();
        tymy.vytvorTymy(lobbyPlayers, mista);
        for (int i = 0; i < tymy.pocet(); i++) {
            Tym tym = tymy.vratTym(i);
            for (Player player : tym.vratHrace()) {
                player.teleport(tym.getSpawnPoint());
                player.setBedSpawnLocation(tym.getSpawnPoint());
            }
        }
        odpocet(lobbyPlayers);
    }

    public void teleport(Player player) {
        player.teleport(tymy.vratTym(player).getSpawnPoint());
    }

    private String getConfigKey(int teamNumber) {
        return TEAM_LOCATION_CONFIG_KEY + teamNumber;
    }

    private List<Location> cilTeleportu() {
        List<Location> mista = new ArrayList<>();
        var i = 1;
        Location teamLocation;
        while ((teamLocation = plugin.getConfig().getLocation(getConfigKey(i++))) != null) {
            mista.add(teamLocation);
        }
        if (mista.size() == 0) {
            plugin.getLogger().log(Level.CONFIG, "Nenactena spawnovaci mista z configu pro team battle.");
        } else {
            plugin.getLogger().log(Level.CONFIG, "Pocet spawnovacich mist pro team battle:  " + mista.size());
        }
        return mista;
    }

    public boolean jeOdpocet() {
        if (odpocet == null) return false;
        return odpocet.jeOdpocet();
    }

    private void odpocet(List<Player> hraci) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        odpocet = new Odpocet(hraci);
        executorService.submit(odpocet);
    }

}
