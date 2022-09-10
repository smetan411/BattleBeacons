package battlebeacons;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.java.JavaPlugin;
import battlebeacons.commands.KonecHry;
import battlebeacons.commands.VytvorTeleportera;
import battlebeacons.listenery.*;
import battlebeacons.lobby.Lobby;
import battlebeacons.lobby.LobbyCreator;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Tymy;

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

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(teleportDoAreny), this);
        getServer().getPluginManager().registerEvents(new ZabitiSpoluhrace(tymy), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy), this);
        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(teleportDoLoby, tymy));
    }

}
