package battlebeacons.tymy;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Tymy {
    private List<Tym> tymy = new ArrayList<>();

    public List<Tym> vratTymy() {
        return new ArrayList<>(tymy);
    }

    public int pocet() {
        return tymy.size();
    }

    public void konecHry() {
        tymy.clear();
    }

    public Tym vratTym(int cislo) {
        return tymy.get(cislo);
    }
    public Tym vratTym(String jmenoTymu) {
        return tymy.stream()
                .filter(tym -> jmenoTymu.equals(tym.getNastaveniTymu().getJmeno()))
                .findFirst()
                .get();
    }

    public Optional<Tym> vratTymPodleMistaBeaconu(Location location) {
        return tymy.stream()
                .filter(tym -> location.equals(tym.getBeaconPoint()))
                .findFirst();
    }

    public void vytvorTymy(List<Player> hraci, List<Location> spawnPointy, List<Location> beaconPointy) {
        tymy.clear();
        int pocetTymu = spawnPointy.size();
        if (pocetTymu > NastaveniTymu.values().length) throw new IllegalArgumentException("Prilis mnoho tymu.");
        for (int i = 0; i < pocetTymu; i++) {
            tymy.add(new Tym(NastaveniTymu.values()[i], spawnPointy.get(i), beaconPointy.get(i)));
        }
        hraci = zamichej(hraci);
        int i = 0;
        for (Player player : hraci) {
            tymy.get(i++).pridej(player);
            if (i >= pocetTymu) i = 0;
        }
        //prazdne tymy oznacime jako mrtve
        tymy.stream()
                .filter( tym -> tym.getHraci().isEmpty())
                .forEach( tym -> tym.setAlive(false));
    }

    public boolean spoluhraci(Player player1, Player player2) {
        for (Tym tym : tymy) {
            if (tym.patriDoTymu(player1) && tym.patriDoTymu(player2)) {
                return true;
            }
        }
        return false;
    }

    private List<Player> zamichej(List<Player> hraci) {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int prvniIndex = rand.nextInt(hraci.size());
            int druhyIndex = rand.nextInt(hraci.size());
            Player player = hraci.get(prvniIndex);
            hraci.set(prvniIndex, hraci.get(druhyIndex));
            hraci.set(druhyIndex, player);
        }
        return hraci;
    }

    public Tym vratTym(Player player) {
        return
                tymy.stream()
                        .filter(tym -> tym.getHraci().contains(player))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Hrac neexistuje."));
    }

    public boolean hraJede() {
        return !tymy.isEmpty();
    }
}
