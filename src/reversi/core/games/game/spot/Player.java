package reversi.core.games.game.spot;

import reversi.core.games.game.board.PieceStatus;

/**
 * 玩家类
 * 表示游戏中的一个玩家
 * 
 * 属性说明：
 * 1. name: 玩家名称
 * 2. piecetype: 玩家使用的棋子类型（黑/白）
 */
public class Player {
    private final String name;        // 玩家名称
    private final PieceStatus piecetype;  // 棋子类型

    /**
     * 构造函数
     * @param name 玩家名称
     * @param piecetype 棋子类型
     */
    public Player(String name, PieceStatus piecetype) {
        this.name = name;
        this.piecetype = piecetype;
    }

    // Getters
    public String getName() { return name; }
    public PieceStatus getPiecetype() { return piecetype; }
}