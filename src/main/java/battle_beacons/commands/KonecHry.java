package battle_beacons.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import battle_beacons.teleporter.TeleportDoLoby;
import battle_beacons.tymy.Tymy;

public class KonecHry extends  OperatorCommandExecutor {

    private final TeleportDoLoby teleportDoLoby;
    private final Tymy tymy;

    public KonecHry(TeleportDoLoby teleportDoLoby, Tymy tymy) {
        this.teleportDoLoby = teleportDoLoby;
        this.tymy = tymy;
    }

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        teleportDoLoby.teleport();
        tymy.vratTymy().forEach( tym -> {
            tym.zprava("Konec hry", "OP ukoncil hru");
        });
        tymy.clear();
        return false;
    }
}
