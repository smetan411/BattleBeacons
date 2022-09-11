package battlebeacons.tymy;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class Tymy {
    private List<Tym> tymy = new ArrayList<>();

    public List<Tym> vratTymy() {
        return new ArrayList<>(tymy);
    }

    public int pocet() {
        return tymy.size();
    }

    public void clear() {
        tymy.clear();
    }

    public Tym vratTym(int cislo) {
        return tymy.get(cislo);
    }
    public Tym vratTym(String jmenoTymu) {
        return tymy.stream()
                .filter(tym -> jmenoTymu.equals(tym.getJmenoTymu().getJmeno()))
                .findFirst()
                .get();
    }

    public void vytvorTymy(List<Player> hraci, List<Location> spawnPointy) {
        tymy.clear();
        int pocetTymu = spawnPointy.size();
        if (pocetTymu > JmenoTymu.values().length) throw new IllegalArgumentException("Prilis mnoho tymu.");
        for (int i = 0; i < pocetTymu; i++) {
            tymy.add(new Tym(JmenoTymu.values()[i], spawnPointy.get(i)));
        }
        hraci = zamichej(hraci);
        int i = 0;
        for (Player player : hraci) {
            tymy.get(i++).pridej(player);
            if (i >= pocetTymu) i = 0;
        }
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
                        .filter(tym -> tym.vratHrace().contains(player))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Hrac neexistuje."));
    }

    public boolean hraJede() {
        return !tymy.isEmpty();
    }
}
