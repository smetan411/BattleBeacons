package battlebeacons.generatory;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GoldGeneratorCommand implements CommandExecutor {

    private final Generatory generatory;

    public GoldGeneratorCommand(Generatory generatory) {
        this.generatory = generatory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))return false;

        Player player = (Player) sender;
        Location location = player.getLocation();
        generatory.createGoldGenerator(location);
        return true;
    }
}
