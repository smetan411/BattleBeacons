package battlebeacons;

import battlebeacons.commands.KonecHry;
import battlebeacons.commands.VytvorTeleportera;
import battlebeacons.commands.trader.TraderJidlo;
import battlebeacons.commands.trader.TraderZbrane;
import battlebeacons.commands.trader.VeciNaProdej;
import battlebeacons.generatory.Generatory;
import battlebeacons.generatory.GoldGeneratorCommand;
import battlebeacons.generatory.IronGeneratorCommand;
import battlebeacons.listenery.*;
import battlebeacons.lobby.Lobby;
import battlebeacons.lobby.LobbyCreator;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLobby;
import battlebeacons.timelimit.TimeLimit;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        //service
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLobby teleportDoLoby = new TeleportDoLobby(lobby, tymy);
        Skore skore = new Skore(tymy);
        SpravaBloku spravaBloku = new SpravaBloku();
        Generatory generatory = new Generatory(this, spravaBloku);
        StavHry stavHry = new StavHry(tymy, teleportDoLoby, spravaBloku, teleportDoAreny, skore, generatory, this);
        TimeLimit timeLimit = new TimeLimit(this, stavHry, tymy);

        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(stavHry, timeLimit), this);
        getServer().getPluginManager().registerEvents(new OchranaSpoluhrace(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny, stavHry), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy, stavHry, this), this);
        getServer().getPluginManager().registerEvents(new SmrtHrace(tymy, skore, stavHry), this);
        getServer().getPluginManager().registerEvents(new BeaconZnicen(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlokPoskozen(spravaBloku), this);
        getServer().getPluginManager().registerEvents(new BlokPolozen(spravaBloku, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlockDropEvent(spravaBloku), this);
        getServer().getPluginManager().registerEvents(new PlayerDropEvent(spravaBloku), this);


        //commands
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(stavHry, tymy));
        getCommand("+vytvorTraderaJidlo").setExecutor(new TraderJidlo(new VeciNaProdej()));
        getCommand("+vytvorTraderaZbrane").setExecutor(new TraderZbrane(new VeciNaProdej()));
        getCommand("+vytvorIronGenerator").setExecutor(new IronGeneratorCommand(generatory));
        getCommand("+vytvorGoldGenerator").setExecutor(new GoldGeneratorCommand(generatory));
    }
}
