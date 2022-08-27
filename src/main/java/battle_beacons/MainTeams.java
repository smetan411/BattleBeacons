package battle_beacons;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;
import battle_beacons.commands.KonecHry;
import battle_beacons.commands.VytvorTeleportera;
import battle_beacons.listenery.*;
import battle_beacons.lobby.Lobby;
import battle_beacons.lobby.LobbyCreator;
import battle_beacons.teleporter.TeleportDoAreny;
import battle_beacons.teleporter.TeleportDoLoby;
import battle_beacons.tymy.SkoreTymu;
import battle_beacons.tymy.Tymy;

public class MainTeams extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        //data
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLoby teleportDoLoby = new TeleportDoLoby(lobby, tymy);
        SkoreTymu skoreTymu = new SkoreTymu(Bukkit.getScoreboardManager().getNewScoreboard(), tymy);

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(teleportDoAreny, skoreTymu), this);
        getServer().getPluginManager().registerEvents(new ZabitiNepritele(tymy, teleportDoLoby, teleportDoAreny, skoreTymu), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy), this);
        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(teleportDoLoby, tymy));
    }

}
