package source.domain.util;

import java.util.Random;

public class GameRandom extends Random {
    private static final long serialVersionUID = 1L;
    private static final GameRandom DEFAULT_INSTANCE = new GameRandom();

    public GameRandom() {
        super();
    }

    public static GameRandom getDefaultRandom() {
        return DEFAULT_INSTANCE;
    }

    public static GameRandom getDefault() {
        return DEFAULT_INSTANCE;
    }
}
