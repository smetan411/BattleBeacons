package battlebeacons.commands.trader;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VeciNaProdej {

    public ItemStack chleba() {
        var chleb = new ItemStack(Material.BREAD, 8);
        return chleb;
    }

    public ItemStack jablko() {
        var jabko = new ItemStack(Material.APPLE, 8);
        return jabko;
    }

    public ItemStack steak() {
        var rizek = new ItemStack(Material.COOKED_PORKCHOP, 8);
        return rizek;
    }
    public ItemStack enderpearl() {
        var perla = new ItemStack(Material.ENDER_PEARL);
        return perla;
    }

    public ItemStack wool() {
        var wool = new ItemStack(Material.BLACK_WOOL, 16);
        return wool;
    }

    public ItemStack endstone() {
        var endstone = new ItemStack(Material.END_STONE, 16);
        return endstone;
    }

    public ItemStack elytra() {
        var elytra = new ItemStack(Material.ELYTRA, 1);
        return elytra;
    }
    public ItemStack bucketOfPowederSnow() {
        var bucketOfPowederSnow = new ItemStack(Material.POWDER_SNOW_BUCKET, 1);
        return bucketOfPowederSnow;
    }
    public ItemStack lavaBucket() {
        var lavaBucket = new ItemStack(Material.LAVA_BUCKET, 1);
        return lavaBucket;
    }
    public ItemStack chainmailLeggins() {
        var chainmailLeggins = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        return chainmailLeggins;
    }
    public ItemStack chainmailBoots() {
        var hainmailBoots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        return hainmailBoots;
    }

    public ItemStack chainmailChestplate() {
        var chainmailChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        return chainmailChestplate;
    }
    public ItemStack ironLeggins() {
        var ironLeggins = new ItemStack(Material.IRON_LEGGINGS, 1);
        return ironLeggins;
    }
    public ItemStack ironlBoots() {
        var ironBoots = new ItemStack(Material.IRON_BOOTS, 1);
        return ironBoots;
    }

    public ItemStack ironChestplate() {
        var hainmailChestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        return hainmailChestplate;
    }
    public ItemStack diaBoots() {
        var diaBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        return diaBoots;
    }
    public ItemStack diaLeggins() {
        var diaLeggins = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        return diaLeggins;
    }

    public ItemStack diaChestplate() {
        var diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        return diamondChestplate;
    }

    public ItemStack krumpac() {
        var pickAxe = new ItemStack(Material.WOODEN_PICKAXE);
        return pickAxe;
    }

    public ItemStack lepsiKrumpac() {
        var pickAxe = new ItemStack(Material.STONE_PICKAXE);
        return pickAxe;
    }

    public ItemStack ocelovyKrumpac() {
        var pickAxe = new ItemStack(Material.IRON_PICKAXE);
        return pickAxe;
    }

    public ItemStack zeleznyMec(){
        var mec = new ItemStack(Material.IRON_SWORD);
        mec.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        return mec;
    }

    public ItemStack diamantovyMec(){
        var mec = new ItemStack(Material.DIAMOND_SWORD);
        mec.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        return mec;
    }
    public ItemStack netheritovyMec(){
        var mec = new ItemStack(Material.NETHERITE_SWORD);
        mec.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        return mec;
    }

    public ItemStack zeleznaSekera() {
        var axe = new ItemStack(Material.IRON_AXE);
        return axe;
    }

    public ItemStack diamantovaSekera() {
        var axe = new ItemStack(Material.DIAMOND_AXE);
        return axe;
    }

    public ItemStack netheritovaSekera() {
        var axe = new ItemStack(Material.NETHERITE_AXE);
        return axe;
    }

    public ItemStack luk() {
        var luk = new ItemStack(Material.BOW);
        luk.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        return luk;
    }

    public ItemStack silnyLuk() {
        var luk = new ItemStack(Material.BOW);
        luk.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
        return luk;
    }

    public ItemStack odhazujiciLuk() {
        var luk = new ItemStack(Material.BOW);
        luk.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        luk.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        return luk;
    }

    public ItemStack nekonecnyLuk() {
        var luk = new ItemStack(Material.BOW);
        luk.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        luk.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        return luk;
    }

    public ItemStack sipy() {
        var sip = new ItemStack(Material.ARROW, 10);
        return sip;
    }

    public ItemStack jedovateSipy() {
        var sip = new ItemStack(Material.TIPPED_ARROW, 5);
        PotionMeta meta = (PotionMeta) sip.getItemMeta();
        meta.setColor(Color.GREEN);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 140,1), true);
        sip.setItemMeta(meta);
        return sip;
    }

    public ItemStack oslepujiciSipy() {
        var sip = new ItemStack(Material.TIPPED_ARROW, 5);
        PotionMeta meta = (PotionMeta) sip.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 180,1), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 100,1), true); //motani hlavy
        meta.setColor(Color.GRAY);
        ItemMeta itemMeta = (ItemMeta) meta;
        sip.setItemMeta(itemMeta);
        return sip;
    }
}