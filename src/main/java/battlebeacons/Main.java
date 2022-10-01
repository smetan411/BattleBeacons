package battlebeacons;

import battlebeacons.commands.KonecHry;
import battlebeacons.commands.VytvorTeleportera;
import battlebeacons.listenery.*;
import battlebeacons.lobby.Lobby;
import battlebeacons.lobby.LobbyCreator;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        //data
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLoby teleportDoLoby = new TeleportDoLoby(lobby, tymy);
        Skore skore = new Skore(tymy);
        SpravaBloku spravaBloku = new SpravaBloku();

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(teleportDoAreny, skore), this);
        getServer().getPluginManager().registerEvents(new ZraneniHrace(tymy, teleportDoLoby), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy), this);
        getServer().getPluginManager().registerEvents(new SmrtHrace(tymy, skore, teleportDoLoby, spravaBloku), this);
        getServer().getPluginManager().registerEvents(new BeaconZnicen(tymy), this);
        getServer().getPluginManager().registerEvents(new BlokPoskozen(), this);
        getServer().getPluginManager().registerEvents(new BlokPolozen(tymy, spravaBloku), this);


        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(teleportDoLoby, tymy));
    }

}
