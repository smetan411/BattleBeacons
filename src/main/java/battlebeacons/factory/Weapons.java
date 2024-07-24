package battlebeacons.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Weapons {

    public ItemStack sword() {
        var mec = new ItemStack(Material.STONE_SWORD, 1);
        return mec;
    }

    public ItemStack shield() {
        var stit = new ItemStack(Material.SHIELD, 1);
        return stit;
    }

    public ItemStack axe() {
        var axe = new ItemStack(Material.WOODEN_AXE, 1);
        return axe;
    }


}

