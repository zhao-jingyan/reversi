package reversi.core.games.game;

import reversi.core.games.game.board.Board;
import reversi.core.games.game.spot.HotSpot;
import reversi.core.games.game.spot.SpotStatus;
import reversi.core.logic.exceptions.*;

public class Game {
    private final int gameNum;
    private final Board board;
    private final HotSpot spot;
    private final GameMode gameMode;

    public Game(int gameNum, String p1Name, String p2Name, GameMode gameMode) {
        this.gameNum = gameNum;
        this.board = Board.createBoard(gameMode);
        spot = new HotSpot(p1Name, p2Name);
        this.gameMode = gameMode;

        // initialize game
        spot.initialize();
        board.clear();
    }

    // 执行游戏动作
    public void update(int[] coordinate) throws GameException {
        try {
            spot.makeMove(board, coordinate);
        } catch (GameException e) {
            throw e;
        }
    }

    public int getGameNum() {
        return gameNum;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public SpotStatus getSpotStatus() {
        return spot.getSpotStatus();
    }

    public Board getBoard() {
        return board;
    }

    public HotSpot getSpot() {
        return spot;
    }
}