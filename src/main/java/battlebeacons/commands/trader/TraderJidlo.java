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

public class TraderJidlo implements CommandExecutor {
    private final VeciNaProdej veciNaProdej;

    public static final String JMENO_OBCHODNIKA = "TRADER JIDLO";

    public TraderJidlo(VeciNaProdej veciNaProdej) {
        this.veciNaProdej = veciNaProdej;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) return false;
        if (!(sender instanceof Player)) return false;
        var player = (Player) sender;

        var trader = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        trader.setVillagerType(Villager.Type.DESERT);
        trader.setProfession(Villager.Profession.FARMER);
        trader.setAI(false);
        trader.setCustomName(JMENO_OBCHODNIKA);
        trader.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 999999999, true));

        MerchantRecipe enderPearl = new MerchantRecipe(veciNaProdej.enderpearl(), 999);
        enderPearl.addIngredient(new ItemStack(Material.EMERALD, 3));

        MerchantRecipe steak = new MerchantRecipe(veciNaProdej.steak(), 999);
        steak.addIngredient(new ItemStack(Material.GOLD_INGOT, 10));

        MerchantRecipe jablko = new MerchantRecipe(veciNaProdej.jablko(), 999);
        jablko.addIngredient(new ItemStack(Material.IRON_INGOT, 2));

        MerchantRecipe wool = new MerchantRecipe(veciNaProdej.wool(), 999);
        wool.addIngredient(new ItemStack(Material.IRON_INGOT, 4));

        MerchantRecipe endstone = new MerchantRecipe(veciNaProdej.endstone(), 999);
        endstone.addIngredient(new ItemStack(Material.GOLD_INGOT, 10));

        MerchantRecipe bucketOfPowederSnow = new MerchantRecipe(veciNaProdej.bucketOfPowederSnow(), 999);
        bucketOfPowederSnow.addIngredient(new ItemStack(Material.EMERALD, 3));

        MerchantRecipe elytra = new MerchantRecipe(veciNaProdej.elytra(), 999);
        elytra.addIngredient(new ItemStack(Material.NETHERITE_INGOT, 3));

        MerchantRecipe lavaBucket = new MerchantRecipe(veciNaProdej.lavaBucket(), 999);
        lavaBucket.addIngredient(new ItemStack(Material.GOLD_INGOT, 10));

        MerchantRecipe chleba = new MerchantRecipe(veciNaProdej.chleba(), 999);
        chleba.addIngredient(new ItemStack(Material.GOLD_INGOT, 3));

        MerchantRecipe pickAXE = new MerchantRecipe(veciNaProdej.krumpac(), 999);
        pickAXE.addIngredient(new ItemStack(Material.IRON_INGOT, 2));

        MerchantRecipe stonePickAXE = new MerchantRecipe(veciNaProdej.lepsiKrumpac(), 999);
        stonePickAXE.addIngredient(new ItemStack(Material.EMERALD, 5));

        MerchantRecipe ironPickAXE = new MerchantRecipe(veciNaProdej.ocelovyKrumpac(), 999);
        ironPickAXE.addIngredient(new ItemStack(Material.NETHERITE_INGOT, 1));

        trader.setRecipes(Lists.newArrayList(jablko, chleba, steak, enderPearl, wool, endstone, bucketOfPowederSnow, elytra,
                lavaBucket, pickAXE, stonePickAXE, ironPickAXE));

        return true;
    }
}
