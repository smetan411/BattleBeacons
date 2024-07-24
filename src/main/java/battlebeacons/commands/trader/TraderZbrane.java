package battlebeacons.commands.trader;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TraderZbrane implements CommandExecutor {
    private final VeciNaProdej veciNaProdej;

    public static final String JMENO_OBCHODNIKA = "TRADER ZBRANE";

    public TraderZbrane(VeciNaProdej veciNaProdej) {
        this.veciNaProdej = veciNaProdej;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        var player = (Player) sender;

        var trader = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        trader.setVillagerType(Villager.Type.DESERT);
        trader.setProfession(Villager.Profession.ARMORER);
        trader.setAI(false);
        trader.setCustomName(JMENO_OBCHODNIKA);
        trader.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 999999999, true));

        MerchantRecipe chainmailLeggins = new MerchantRecipe(veciNaProdej.chainmailLeggins(), 999);
        chainmailLeggins.addIngredient(new ItemStack(Material.IRON_INGOT, 10));

        MerchantRecipe chainmailBoots = new MerchantRecipe(veciNaProdej.chainmailBoots(), 999);
        chainmailBoots.addIngredient(new ItemStack(Material.IRON_INGOT, 10));

        MerchantRecipe chainmailChestplate = new MerchantRecipe(veciNaProdej.chainmailChestplate(), 999);
        chainmailChestplate.addIngredient(new ItemStack(Material.IRON_INGOT, 15));

        MerchantRecipe ironLeggins = new MerchantRecipe(veciNaProdej.ironLeggins(), 999);
        ironLeggins.addIngredient(new ItemStack(Material.GOLD_INGOT, 15));

        MerchantRecipe ironBoots = new MerchantRecipe(veciNaProdej.ironlBoots(), 999);
        ironBoots.addIngredient(new ItemStack(Material.GOLD_INGOT, 15));

        MerchantRecipe ironChestplate = new MerchantRecipe(veciNaProdej.ironChestplate(), 999);
        ironChestplate.addIngredient(new ItemStack(Material.GOLD_INGOT, 20));

        MerchantRecipe diaLeggins = new MerchantRecipe(veciNaProdej.diaLeggins(), 999);
        diaLeggins.addIngredient(new ItemStack(Material.EMERALD, 5));

        MerchantRecipe diaBoots = new MerchantRecipe(veciNaProdej.diaBoots(), 999);
        diaBoots.addIngredient(new ItemStack(Material.EMERALD, 5));

        MerchantRecipe diaChestplate = new MerchantRecipe(veciNaProdej.diaChestplate(), 999);
        diaChestplate.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe mec = new MerchantRecipe(veciNaProdej.zeleznyMec(), 999);
        mec.addIngredient(new ItemStack(Material.GOLD_INGOT, 5));

        MerchantRecipe diaMec = new MerchantRecipe(veciNaProdej.diamantovyMec(), 999);
        diaMec.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe netheritMec = new MerchantRecipe(veciNaProdej.netheritovyMec(), 999);
        netheritMec.addIngredient(new ItemStack(Material.NETHERITE_INGOT, 2));

        MerchantRecipe sekera = new MerchantRecipe(veciNaProdej.zeleznaSekera(), 999);
        sekera.addIngredient(new ItemStack(Material.GOLD_INGOT, 8));

        MerchantRecipe diaSekera = new MerchantRecipe(veciNaProdej.diamantovaSekera(), 999);
        diaSekera.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe netheritSekera = new MerchantRecipe(veciNaProdej.netheritovaSekera(), 999);
        netheritSekera.addIngredient(new ItemStack(Material.NETHERITE_INGOT, 2));

        MerchantRecipe luk = new MerchantRecipe(veciNaProdej.luk(), 999);
        luk.addIngredient(new ItemStack(Material.GOLD_INGOT, 10));

        MerchantRecipe silnyLuk = new MerchantRecipe(veciNaProdej.silnyLuk(), 999);
        silnyLuk.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe odhazujiciLuk = new MerchantRecipe(veciNaProdej.odhazujiciLuk(), 999);
        odhazujiciLuk.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe nekonecnyLuk = new MerchantRecipe(veciNaProdej.nekonecnyLuk(), 999);
        nekonecnyLuk.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe sip = new MerchantRecipe(veciNaProdej.sipy(), 999);
        sip.addIngredient(new ItemStack(Material.GOLD_INGOT, 2));

        MerchantRecipe otravenySip = new MerchantRecipe(veciNaProdej.jedovateSipy(), 999);
        otravenySip.addIngredient(new ItemStack(Material.EMERALD, 1));

        MerchantRecipe oslepujiciSip = new MerchantRecipe(veciNaProdej.oslepujiciSipy(), 999);
        oslepujiciSip.addIngredient(new ItemStack(Material.EMERALD, 1));

        trader.setRecipes(Lists.newArrayList(chainmailChestplate, chainmailLeggins, chainmailBoots, ironChestplate,
                ironLeggins, ironBoots, diaChestplate, diaLeggins, diaBoots, mec, diaMec, netheritMec, sekera, diaSekera, netheritSekera,
                luk, silnyLuk, odhazujiciLuk, nekonecnyLuk, sip, otravenySip, oslepujiciSip));

        return true;
    }
}
