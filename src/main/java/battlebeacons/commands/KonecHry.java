package battlebeacons.commands;

import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Tymy;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class KonecHry extends  OperatorCommandExecutor {

    private final TeleportDoLoby teleportDoLoby;
    private final Tymy tymy;

    public KonecHry(TeleportDoLoby teleportDoLoby, Tymy tymy) {
        this.teleportDoLoby = teleportDoLoby;
        this.tymy = tymy;
    }

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        if (!tymy.hraJede()) return true;
        teleportDoLoby.teleport();
        tymy.vratTymy().forEach( tym -> {
            tym.zprava("Konec hry", "OP ukoncil hru");
        });
        tymy.konecHry();
        return true;
    }
}
