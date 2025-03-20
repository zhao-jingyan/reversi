package reversi.core;

import java.util.HashMap;
import java.util.Map;
import reversi.core.game.Game;
import reversi.core.game.GameMode;
import reversi.core.game.spot.SpotStatus;
import reversi.info.inputinfo.InputInformation;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private final Map<Integer, Game> games;
    private Game currentGame;
    private int currentGameNum;

    private GameManager() {
        games = new HashMap<>();
        currentGameNum = 1;
        instance.createGame("Bill_Black", "Walt_White", GameMode.PEACE);
        instance.createGame("Bill_Black", "Walt_White", GameMode.REVERSI);
        switchToBoard(1);
    }

    private void createGame(String p1Name, String p2Name, GameMode gameMode) {
        Game game = new Game(currentGameNum++, p1Name, p2Name, gameMode);
        games.put(game.getGameNum(), game);
    }    

    private void switchToBoard(int gameNum) {
        currentGame = games.get(gameNum);
    }

    public void updateCurrentGame(InputInformation info) {
        currentGame.update(info);
    }

    public boolean isCurrentGameEnd() {
        return currentGame.getSpotStatus() == SpotStatus.END;
    }

    public boolean isAllGameEnd() {
        //check if all games are ended
        return games.values().stream().allMatch(game -> game.getSpotStatus() == SpotStatus.END);
    }

    public Game getCurrentGame() {
        return currentGame;
    }
} 