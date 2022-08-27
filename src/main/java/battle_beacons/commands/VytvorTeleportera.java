package battle_beacons.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import battle_beacons.teleporter.TeleportDoAreny;

import static battle_beacons.commands.VytvorTeleportera.TypHry.TeamBattle;

public class VytvorTeleportera extends OperatorCommandExecutor {

    enum TypHry {
        TeamBattle
    }

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        if (args.length == 0) {
            zobrazNapovedu(operator, command);
            return false;
        }
        TypHry typHry;
        try {
            typHry = TypHry.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            zobrazNapovedu(operator, command);
            return false;
        }

        switch (typHry) {
            case TeamBattle:
                var teleporter = world.spawn(operator.getLocation(), Villager.class);
                teleporter.setAI(false);
                teleporter.setCustomName(TeleportDoAreny.JMENO_TELEPORTERA);
                //  teleporter.setInvulnerable(true);
                break;
        }
        return true;
    }

    private void zobrazNapovedu(Player operator, Command command) {
        operator.sendMessage("Pouziti: " + command.getName() + " " + TeamBattle.name());
        operator.sendMessage("Vytvori teleportera pro vybranou hru");
    }
}
