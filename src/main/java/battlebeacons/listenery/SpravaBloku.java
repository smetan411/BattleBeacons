package battlebeacons.listenery;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import java.util.HashSet;
import java.util.Set;

public final class SpravaBloku {
    public static Set<Material> POVOLENE_BLOKY = Set.of(Material.BLACK_WOOL, Material.END_STONE, Material.LAVA, Material.POWDER_SNOW);
    private final Set<Block> polozeneBloky = new HashSet<>();
    private final Set<Item> odhozeneVeci = new HashSet<>();

    public static boolean jeBlokPovoleny(Material material) {
        return POVOLENE_BLOKY.contains(material);
    }

    public void add(Block block) {
        polozeneBloky.add(block);
    }

    public void add(Item item) {
        odhozeneVeci.add(item);
    }

    public void znicPolozeneBloky() {
        for (var blok : polozeneBloky) {
            blok.setType(Material.AIR);
        }
        polozeneBloky.clear();
    }

    public void znicOdhozeneVeci() {
        for (Item item : odhozeneVeci) {
            item.remove();
        }
        odhozeneVeci.clear();
    }

    public boolean jeBlokPolozenyVeHre(Block blok) {
        return polozeneBloky.contains(blok);
    }
}
