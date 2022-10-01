package battlebeacons.listenery;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SpravaBloku {
    public static Set<Material> POVOLENE_BLOKY = Set.of(Material.BLACK_WOOL, Material.BEACON);

    private List<Block> polozeneBloky = new ArrayList<>();

    public void add(Block block) {
        polozeneBloky.add(block);
    }

    public void znicPolozeneBloky() {
        for (var blok : polozeneBloky) {
            blok.setType(Material.AIR);
        }
        polozeneBloky.clear();
    }

    public static boolean jeBlokZakazany(Material material) {
        return !POVOLENE_BLOKY.contains(material);
    }
}
