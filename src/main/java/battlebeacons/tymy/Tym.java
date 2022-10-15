package battlebeacons.tymy;


import battlebeacons.factory.ArmorFactory;
import battlebeacons.factory.Weapons;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public final class Tym {

    private final Set<Player> hraci = new HashSet<>();
    private final NastaveniTymu nastaveniTymu;
    private final ArmorFactory armor;
    private final Weapons weapons;
    private final Location spawnPoint;
    private final Location beaconPoint;

    private Boolean alive = true;

    public Tym(NastaveniTymu nastaveniTymu, Location spawnPoint, Location beaconPoint) {
        this.nastaveniTymu = nastaveniTymu;
        armor = new ArmorFactory(nastaveniTymu.getColor());
        this.spawnPoint = spawnPoint;
        this.beaconPoint = beaconPoint;
        weapons = new Weapons();
    }

    private static int convertSecToTicks(int sec) {
        return sec * 20;
    }

    public void pridej(Player player) {
        vybavHrace(player);
        hraci.add(player);
    }

    public Location getSpawnPoint() {
        return spawnPoint;
    }

    public Location getBeaconPoint() {
        return beaconPoint;
    }

    public boolean patriDoTymu(Player player) {
        return hraci.contains(player);
    }

    public Set<Player> getHraci() {
        return new HashSet<>(hraci);
    }

    public void zprava(String text, String subtext) {
        hraci.forEach(hrac -> hrac.sendTitle(text, subtext, 0, convertSecToTicks(10), 0));
    }

    public NastaveniTymu getNastaveniTymu() {
        return nastaveniTymu;
    }

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void vybavHrace(Player hrac) {
        var inventory = hrac.getInventory();
        inventory.clear();
        inventory.setChestplate(armor.chestplate());
        inventory.setBoots(armor.boots());
        inventory.setLeggings(armor.leggins());
        inventory.setHelmet(armor.helmet());
        inventory.setItemInMainHand(weapons.sword());
        inventory.setItemInOffHand(weapons.shield());
        inventory.addItem(weapons.bow());
        inventory.addItem(weapons.arrow());
        inventory.addItem(weapons.axe());
    }
}
