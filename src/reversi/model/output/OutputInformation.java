package reversi.model.output;

import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.Board;
import reversi.core.games.game.board.PieceStatus;
import reversi.core.games.game.spot.Player;
import reversi.model.output.components.BoardInfo;
import reversi.model.output.components.GameInfo;

/**
 * 输出信息类
 * 包含棋盘信息和游戏信息
 */
public class OutputInformation {
    private final BoardInfo boardInfo;
    private final GameInfo gameInfo;
    private final OutputType type;

    public OutputInformation(BoardInfo boardInfo, GameInfo gameInfo, OutputType type) {
        this.boardInfo = boardInfo;
        this.gameInfo = gameInfo;
        this.type = type;
    }

    // Getter方法
    public BoardInfo getBoardInfo() { return boardInfo; }
    public GameInfo getGameInfo() { return gameInfo; }
    public OutputType getOutputType() { return type; }
    public Player getChargePlayer() { return boardInfo.getChargePlayer(); }
    public int getCurrentGameNumber() { return boardInfo.getCurrentGameNumber(); }
    public int getTotalGames() { return gameInfo.getTotalGamesNumber(); }
    public GameMode[] getGameList() { return gameInfo.getGameList(); }
    public int getWhite() { return boardInfo.getWhite(); }
    public int getBlack() { return boardInfo.getBlack(); }
    public Board getBoard() { return boardInfo.getBoard(); }
    public String getPlayer1Name() { return boardInfo.getPlayer1Name(); }
    public String getPlayer2Name() { return boardInfo.getPlayer2Name(); }
    public int getCurrentRound() { return boardInfo.getCurrentRound(); }
    public int getCurrentGame() { return boardInfo.getCurrentGameNumber(); }
    public GameMode getCurrentGameMode() { return gameInfo.getCurrentGameMode(); }
    public PieceStatus getWinner() { return boardInfo.getWinner(); }
} 