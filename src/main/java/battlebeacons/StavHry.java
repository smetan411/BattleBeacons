package battlebeacons;

import battlebeacons.generatory.Generatory;
import battlebeacons.listenery.SpravaBloku;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLobby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;
import org.bukkit.plugin.Plugin;


public class StavHry {

    private final Tymy tymy;
    private final TeleportDoLobby teleportDoLoby;
    private final SpravaBloku spravaBloku;
    private final TeleportDoAreny teleport;
    private final Skore skore;
    private final Generatory generatory;
    private final Plugin plugin;

    private boolean gameRunning;

    public StavHry(Tymy tymy, TeleportDoLobby teleportDoLoby, SpravaBloku spravaBloku, TeleportDoAreny teleport, Skore skore, Generatory generatory, Main plugin) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
        this.spravaBloku = spravaBloku;
        this.teleport = teleport;
        this.skore = skore;
        this.generatory = generatory;
        this.plugin = plugin;
        this.gameRunning = false;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void startGame() {
        teleport.teleportPriStartuHry();
        skore.inicializace();
        generatory.loadFromConfig();
        this.gameRunning = true;
    }

    public void stopGame() {
        this.gameRunning = false;
        teleportDoLoby.teleport();
        tymy.smazTymy();
        spravaBloku.znicPolozeneBloky();
        spravaBloku.znicOdhozeneVeci();
        generatory.destroyAll();
        plugin.getServer().getOnlinePlayers().forEach(player -> player.chat(player.isOp() ? "/kill @e[type=minecraft:armor_stand]" : ""));
        plugin.getServer().getOnlinePlayers().forEach(player -> player.chat(player.isOp() ? "/kill @e[type=minecraft:item]" : ""));
    }
}
