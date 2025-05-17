package battlebeacons.commands;

import battlebeacons.teleporter.TeleportDoAreny;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class VytvorTeleportera extends OperatorCommandExecutor {

    @Override
    boolean onCommand(Player player, World world, Command command, String s, String[] args) {
        if (!player.isOp()) return false;
        var teleporter = world.spawn(player.getLocation(), Villager.class);
        teleporter.setAI(false);
        teleporter.setCustomName(TeleportDoAreny.JMENO_TELEPORTERA);
        return true;
    }

}
