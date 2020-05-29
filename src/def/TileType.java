package def;

import static def.Constants.*;

public enum TileType {
    EMPTY(' ', FOREGROUND_WHITE),
    WALL('#', FOREGROUND_WHITE),
    LAVA('#', FOREGROUND_RED),
    ICE('#', FOREGROUND_CYAN),
    STAIRCASE('=', FOREGROUND_YELLOW + BRIGHT);

    public final char letter;
    public final int colour;

    TileType(char letter, int colour) {
        this.letter = letter;
        this.colour = colour;
    }

};
