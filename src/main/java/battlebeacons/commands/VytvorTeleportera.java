package battlebeacons.commands;

import battlebeacons.teleporter.TeleportDoAreny;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class VytvorTeleportera extends OperatorCommandExecutor {

    @Override
    boolean onCommand(Player operator, World world, Command command, String s, String[] args) {
        var teleporter = world.spawn(operator.getLocation(), Villager.class);
        teleporter.setAI(false);
        teleporter.setCustomName(TeleportDoAreny.JMENO_TELEPORTERA);

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.INVISIBILITY));
        potion.setItemMeta(meta);
        operator.getInventory().addItem(potion);

        return true;
    }
}
