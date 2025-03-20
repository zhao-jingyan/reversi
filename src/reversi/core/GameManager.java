package reversi.core;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;
import reversi.core.game.Game;
import reversi.core.game.GameMode;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private final Map<Integer, Game> games;
    private int currentGameNum;
    private int nextGameNum;

    private GameManager() {
        games = new HashMap<>();
        currentGameNum = 0;
        nextGameNum = 1;
    }

    public static GameManager getInstance() {
        return instance;
    }

    public static Game createGame(String p1Name, String p2Name, GameMode gameMode) {
        return instance.doCreateGame(p1Name, p2Name, gameMode);
    }

    public static Game getCurrentGame() {
        return instance.games.get(instance.currentGameNum);
    }

    public static Game getGame(int gameNum) {
        return instance.games.get(gameNum);
    }

    public static void switchGame(int gameNum) {
        if (instance.games.containsKey(gameNum)) {
            instance.currentGameNum = gameNum;
        }
    }

    public static boolean validateInput(InfoType type, String input) {
        Game currentGame = getCurrentGame();
        if (currentGame == null) {
            return false;
        }
        return currentGame.validateInput(type, input);
    }

    public static void update(InputInformation info) {
        Game currentGame = getCurrentGame();
        if (currentGame != null) {
            currentGame.update(info);
        }
    }

    public static int getCurrentGameNum() {
        return instance.currentGameNum;
    }

    public static int getGameNum() {
        return instance.games.size();
    }

    // 私有实例方法
    private Game doCreateGame(String p1Name, String p2Name, GameMode gameMode) {
        Game game = new Game(nextGameNum++, p1Name, p2Name, gameMode);
        games.put(game.getGameNum(), game);
        currentGameNum = game.getGameNum();
        return game;
    }
} 