package com.reversi.core.games.game.board;

/**
 * 棋子状态枚举
 * 定义了棋子的所有可能状态
 * 
 * 状态说明：
 * 1. EMPTY: 空位置
 * 2. BLACK: 黑子
 * 3. WHITE: 白子
 * 4. VALID: 有效落子位置
 */
public enum PieceStatus {
    EMPTY,   // 空位
    BLACK,   // 黑子
    WHITE,   // 白子
    VALID;   // 有效位置

    /**
     * 获取对手的棋子状态
     * @return 对手的棋子状态
     */
    public PieceStatus opp() {
        if (this == BLACK) return WHITE;
        if (this == WHITE) return BLACK;
        return this;
    }
}