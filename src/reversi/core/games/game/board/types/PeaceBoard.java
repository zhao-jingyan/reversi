package reversi.core.games.game.board.types;

import reversi.core.games.game.board.Board;
import reversi.core.games.game.board.PieceStatus;
/*
 * PeaceBoard implements a simple board for PeaceGame
 * It only allows placing pieces without flipping or capturing
 */
public class PeaceBoard extends Board {
    public PeaceBoard() {
        super();
    }

    @Override
    protected void initializeBoard() {
        // 在和平棋中，初始棋盘是空的，不需要放置任何棋子
    }

    @Override
    public void refreshValid(PieceStatus type) {
        // 在和平棋中，所有空位都是合法的
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getStatus() != PieceStatus.BLACK && board[i][j].getStatus() != PieceStatus.WHITE) {
                    board[i][j].targetValid();
                }
            }
        }
    }

    @Override
    public void update(int[] input, PieceStatus type) {
        round++;
        //落子
        add(type, input);
    }

    @Override
    public boolean isOver() {
        return isfull();
    }
    
    @Override
    public boolean isWaitingForPass() {
        return false;
    }
}