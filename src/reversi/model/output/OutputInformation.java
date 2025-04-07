package reversi.model.output;

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

    // 基本 getter 方法
    public BoardInfo getBoardInfo() { return boardInfo; }
    public GameInfo getGameInfo() { return gameInfo; }
    public OutputType getOutputType() { return type; }
} 