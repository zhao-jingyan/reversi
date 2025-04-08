package reversi.core.games.game.board.types;

import reversi.core.games.game.board.Board;
import reversi.core.games.game.board.PieceStatus;

/**
 * 黑白棋棋盘实现
 * 实现了黑白棋的翻转逻辑
 */
public class ReversiBoard extends Board {
    private boolean isOver;
    private boolean isWaitingForPass;

    public ReversiBoard() {
        super();
        isOver = false;
        isWaitingForPass = false;
    }

    @Override
    protected void initializeBoard() {
        // 在黑白棋中，初始化时需要在中心放置四个棋子
        white += 2;
        black += 2;
        board[3][3].setWhite();
        board[4][4].setWhite();
        board[4][3].setBlack();
        board[3][4].setBlack();
    }

    @Override
    public void update(int[] input, PieceStatus type) {
        round++;
        // 先落子
        add(type, input);

        // 再翻转
        int x = input[0];
        int y = input[1];
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        for (int[] dir : directions) {
            this.flipbeam(dir, x, y);
        }
    }

    @Override
    public void refreshValid(PieceStatus type) {
        // 先清除所有有效位置标记
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getStatus() == PieceStatus.VALID) {
                    board[i][j].setEmpty();
                }
            }
        }

        // 检查当前玩家是否有合法位置
        boolean hasValidMove = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidPosition(type, i, j)) {
                    board[i][j].setValid();
                    hasValidMove = true;
                }
            }
        }

        // 如果当前玩家没有合法位置
        if (!hasValidMove) {
            // 检查对方玩家是否有合法位置
            boolean opponentHasValidMove = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (isValidPosition(type.opp(), i, j)) {
                        opponentHasValidMove = true;
                        break;
                    }
                }
            }

            if (opponentHasValidMove) {
                // 对方有合法位置，当前玩家需要pass
                isWaitingForPass = true;
            } 
            else {
                // 双方都没有合法位置，游戏结束
                isOver = true;
                checkWinner();
            }
        } else {
            // 当前玩家有合法位置，不需要pass
            isWaitingForPass = false;
        }
    }

    @Override
    public boolean isOver() { 
        return isOver; 
    }

    @Override
    public boolean isWaitingForPass() { 
        return isWaitingForPass; 
    }

    // 翻转一行棋子
    private void flipbeam(int[] direction, int x, int y) {
        // set the variables
        int xp = x;
        int yp = y;
        PieceStatus piece = board[x][y].getStatus();
        int dx = direction[0];
        int dy = direction[1];

        // flip the pieces
        while (isInBoard(xp + dx, yp + dy)  // in boarder
                && board[xp + dx][yp + dy].getStatus() == piece.opp()) {  // do not meet same piece
            xp += dx;
            yp += dy;

            // going back and flip the pieces
            if (isInBoard(xp + dx, yp + dy)
                    && board[xp + dx][yp + dy].getStatus() == piece) {
                while (xp != x || yp != y) {
                    if (board[xp][yp].getStatus() == PieceStatus.BLACK) {
                        black--;
                        white++;
                    } else if (board[xp][yp].getStatus() == PieceStatus.WHITE) {
                        black++;
                        white--;
                    }
                    board[xp][yp].flip();
                    xp -= dx;
                    yp -= dy;
                }
                break;
            }
        }
    }

    private boolean isValidPosition(PieceStatus type, int x, int y) {
        if (!isInBoard(x, y) || board[x][y].getStatus() != PieceStatus.EMPTY) {
            return false;
        }
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        for (int[] dir : directions) {
            if (canFlipInDirection(type, x, y, dir)) {
                return true;
            }
        }
        return false;
    }

    private boolean canFlipInDirection(PieceStatus type, int x, int y, int[] direction) {
        // set the variables
        int xp = x;
        int yp = y;
        int dx = direction[0];
        int dy = direction[1];
        PieceStatus piece = type;
        PieceStatus opp = type.opp();

        if (!isInBoard(xp + dx, yp + dy) || board[xp + dx][yp + dy].getStatus() != opp)  // not in board or no opp
            return false;
        else {
            xp += dx;
            yp += dy;
            while (isInBoard(xp + dx, yp + dy)  // in boarder
                    && board[xp + dx][yp + dy].getStatus() != PieceStatus.EMPTY
                    && board[xp + dx][yp + dy].getStatus() != PieceStatus.VALID) {  // do not meet empty or valid
                if (board[xp + dx][yp + dy].getStatus() == piece)
                    return true;
                else if (board[xp + dx][yp + dy].getStatus() == opp) {
                    xp += dx;
                    yp += dy;
                }
            }
            return false;
        }
    }
}