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
        //servicy
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLoby teleportDoLoby = new TeleportDoLoby(lobby, tymy);
        Skore skore = new Skore(tymy);
        SpravaBloku spravaBloku = new SpravaBloku();
        StavHry stavHry = new StavHry(tymy, teleportDoLoby, spravaBloku, teleportDoAreny, skore);

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(stavHry), this);
        getServer().getPluginManager().registerEvents(new OchranaSpoluhrace(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny, stavHry), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new SmrtHrace(tymy, skore, stavHry), this);
        getServer().getPluginManager().registerEvents(new BeaconZnicen(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlokPoskozen(), this);
        getServer().getPluginManager().registerEvents(new BlokPolozen(tymy, spravaBloku, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlockDropEvent(spravaBloku), this);
        getServer().getPluginManager().registerEvents(new PlayerDropEvent(spravaBloku), this);

        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(stavHry, tymy));
    }

}
