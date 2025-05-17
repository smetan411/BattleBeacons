package battlebeacons.generatory;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IronGeneratorCommand implements CommandExecutor {

    private final Generatory generatory;

    public IronGeneratorCommand(Generatory generatory) {
        this.generatory = generatory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) return false;
        if (!(sender instanceof Player))return false;

        Player player = (Player) sender;
        Location location = player.getLocation();
        generatory.createIronGenerator(location);
        return true;
    }
}
