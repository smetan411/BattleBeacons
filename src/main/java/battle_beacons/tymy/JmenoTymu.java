package battle_beacons.tymy;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum JmenoTymu {
    REDS("Reds", Color.RED, ChatColor.RED),
    BLUES("Blues", Color.BLUE, ChatColor.BLUE),
    GREENS("Greens", Color.GREEN, ChatColor.GREEN),
    BLACKS("Blacks", Color.BLACK, ChatColor.BLACK),
    WHITES("Whites", Color.WHITE, ChatColor.WHITE);

    private final String jmeno;
    private final Color color;
    private final ChatColor chatColor;

    JmenoTymu(String jmeno, Color color, ChatColor chatColor) {
        this.jmeno = jmeno;
        this.color = color;
        this.chatColor = chatColor;
    }

    public Color getColor() {
        return color;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getJmeno() {
        return jmeno;
    }
}
