/*
 * hotspot is a class that contains two players, one is in spot, the other is not
 * inspired by civilization VI, having a mode called "hot spot" mode, in which player take turns to take action
 * so player contains only information, while hotspot is the one that takes action
 * 
 */

package com.reversi.core.games.game.spot;

import com.reversi.core.games.game.board.Board;
import com.reversi.core.games.game.board.PieceStatus;
import com.reversi.core.logic.exceptions.GameErrorCode;
import com.reversi.core.logic.exceptions.GameException;

/**
 * 玩家管理器类
 * 管理两个玩家的轮流行动
 * 
 * 功能说明：
 * 1. 维护两个玩家的信息
 * 2. 管理当前行动玩家
 * 3. 处理玩家的行动（落子或跳过）
 * 4. 在玩家行动后自动切换当前玩家
 */
public class HotSeat {
    private final Player p1;          // 玩家1
    private final Player p2;          // 玩家2
    private Player chargePlayer;      // 当前行动玩家

    /**
     * 构造函数
     * @param p1Name 玩家1名称
     * @param p2Name 玩家2名称
     */
    public HotSeat(String p1Name, String p2Name) {
        this.p1 = new Player(p1Name, PieceStatus.BLACK);
        this.p2 = new Player(p2Name, PieceStatus.WHITE);
        this.chargePlayer = p1;
    }

    /**
     * 执行移动
     * @param board 棋盘
     * @param coordinate 移动坐标
     * @throws GameException 如果移动无效
     */
    public void makeMove(Board board, int[] coordinate) throws GameException {
        // 检查是否是跳过操作
        if (coordinate[0] == -1 && coordinate[1] == -1) {
            if (board.isWaitingForPass()) {
                changeSpot(board);
                return;
            }
            throw new GameException(GameErrorCode.MAY_NOT_PASS,
                    "Cannot pass when there are valid moves");
        }

        // 检查落子位置是否有效
        if (!board.isValid(coordinate)) {
            if (board.getPieceStatus(coordinate) == PieceStatus.WHITE
             || board.getPieceStatus(coordinate) == PieceStatus.BLACK) {
                throw new GameException(GameErrorCode.CONFLICTING_MOVE,
                        "Conflicting move! [" + (char) ('A' + coordinate[1]) + (coordinate[0] + 1)
                        + "] is already occupied");
            }
            throw new GameException(GameErrorCode.ILLEGAL_MOVE,
                    "Invalid move! [" + (char) ('A' + coordinate[1]) + (coordinate[0] + 1)
                    + "] is not a valid position");
        }

        // 正常落子的逻辑
        board.update(coordinate, chargePlayer.getPiecetype());
        changeSpot(board);
    }

    // Getters
    public Player getChargePlayer() { return chargePlayer; }
    public Player getPlayer1() { return p1; }
    public Player getPlayer2() { return p2; }

    //change the current player
    private void changeSpot(Board board) {
        chargePlayer = (chargePlayer == p1) ? p2 : p1;
        board.refreshValid(chargePlayer.getPiecetype());
    }
}