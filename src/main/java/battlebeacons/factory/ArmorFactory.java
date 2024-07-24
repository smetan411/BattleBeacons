package battlebeacons.factory;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public final class ArmorFactory {

    private final Color color;

    public ArmorFactory(Color color) {
        this.color = color;
    }

    public ItemStack chestplate() {
        var chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
        meta.setColor(color);
        chestplate.setItemMeta(meta);
        return chestplate;
    }

    public ItemStack helmet() {
        var helmet = new ItemStack(Material.LEATHER_HELMET, 1);
        helmet.addEnchantment(Enchantment.BINDING_CURSE, 1);  // aby nesla sundat helma
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(color);
        helmet.setItemMeta(meta);
        return helmet;
    }

    public ItemStack leggins() {
        var leggins = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) leggins.getItemMeta();
        meta.setColor(color);
        leggins.setItemMeta(meta);
        return leggins;
    }

    public ItemStack boots() {
        var boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        return boots;
    }

}
