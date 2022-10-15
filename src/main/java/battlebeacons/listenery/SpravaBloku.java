package battlebeacons.listenery;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SpravaBloku {
    public static Set<Material> POVOLENE_BLOKY = Set.of(Material.BLACK_WOOL, Material.BEACON);
    private final List<Block> polozeneBloky = new ArrayList<>();
    private final List<Item> odhozeneVeci = new ArrayList<>();

    public static boolean jeBlokZakazany(Material material) {
        return !POVOLENE_BLOKY.contains(material);
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
}
