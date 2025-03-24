package reversi.core.games;

import java.util.HashMap;
import java.util.Map;
import reversi.core.games.game.Game;
import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.Board;
import reversi.core.games.game.spot.SpotStatus;
import reversi.core.logic.exceptions.GameErrorCode;
import reversi.core.logic.exceptions.GameException;
import reversi.model.input.InputInformation;

public final class GameManager {
    private static final GameManager instance = new GameManager();
    private final Map<Integer, Game> games;
    private Game currentGame;

    private GameManager() {
        games = new HashMap<>();
        createGame("Bill_Black", "Walt_White", GameMode.PEACE);
        //createGame("Bill_Black", "Walt_White", GameMode.REVERSI);
        try {
            switchToGame(1);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameManager getInstance() {
        return instance;
    }

    public void createGame(String p1Name, String p2Name, GameMode gameMode) {
        Game game = new Game(games.size() + 1, p1Name, p2Name, gameMode);
        games.put(game.getGameNum(), game);
    }    
    
    public void switchToGame(int gameNum) {
        if (games.get(gameNum) != null) {
            currentGame = games.get(gameNum);
        } else {
            throw new GameException(GameErrorCode.GAME_NOT_FOUND, 
                "Game " + gameNum + " does not exist");
        }
    }

    public void updateCurrentGame(InputInformation info) {
        try {
            switch(info.getInputType()) {
                case COORDINATES -> currentGame.update((int[])info.getInfo());
                case PASS -> currentGame.update(new int[]{-1, -1});
                default -> throw new GameException(GameErrorCode.INVALID_INPUT, 
                    "Invalid input");
            }
        } catch (GameException e) {
            throw e;
        }
    }

    public boolean isCurrentGameOver() {
        return currentGame.getSpotStatus() == SpotStatus.END;
    }

    public boolean isAllGamesOver() {
        return games.values().stream().allMatch(game -> game.getSpotStatus() == SpotStatus.END);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Board getCurrentBoard() {
        return currentGame.getBoard();
    }

    public int getCurrentGameNum() {
        return currentGame.getGameNum();
    }

    public GameMode[] getGameList() {
        GameMode[] gameModes = new GameMode[games.size()];
        int index = 0;
        for (Game game : games.values()) {
            gameModes[index++] = game.getGameMode();
        }
        return gameModes;
    }
} 