package reversi.core.game;

import reversi.core.game.board.Board;
import reversi.core.game.spot.HotSpot;
import reversi.core.game.spot.SpotStatus;
import reversi.info.inputinfo.InputInformation;
import reversi.info.inputinfo.types.MoveInformation;

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

        //initialize game
        spot.initialize();
        board.clear();  
    }

    // 执行游戏动作
    public void update(InputInformation info) {
        switch (info.getInfoType()) {
            case COORDINATES -> spot.makeMove(board, (MoveInformation) info);
            case PASS -> spot.pass(board);
            default -> throw new IllegalArgumentException("Invalid input type: " + info.getInfoType());
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
} 