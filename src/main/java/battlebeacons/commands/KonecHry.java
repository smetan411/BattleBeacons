package battlebeacons.commands;

import battlebeacons.StavHry;
import battlebeacons.tymy.Tymy;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class KonecHry extends OperatorCommandExecutor {

    private final StavHry stavHry;
    private final Tymy tymy;

    public KonecHry(StavHry stavHry, Tymy tymy) {
        this.stavHry = stavHry;
        this.tymy = tymy;
    }

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        if (!operator.isOp()) return false;
        if (!stavHry.isGameRunning()) return true;
        tymy.vratTymy().forEach(tym -> tym.zprava("Konec hry", "OP ukoncil hru"));
        stavHry.stopGame();
        // simuluje, jako by hráč zadal tento příkaz do chatu, předpokládá, že hráč má oprávnění OP
        // vyčistí svět po skončení hry - odstraní všechny Armor Standy ve světě
        operator.chat("/kill @e[type=minecraft:armor_stand]");
        // odstraní dropnuté předměty - bloky, upuštěné meče apod.
        operator.chat("/kill @e[type=minecraft:item]");
        return true;
    }
}
