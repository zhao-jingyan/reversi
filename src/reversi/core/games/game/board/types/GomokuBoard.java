package reversi.core.games.game.board.types;

import reversi.core.games.game.board.Board;
import reversi.core.games.game.board.PieceStatus;

/**
 * 五子棋棋盘实现
 * 实现了五子棋的胜负判定逻辑
 */
public class GomokuBoard extends Board {

    private boolean isOver;

    public GomokuBoard() {
        super();
        isOver = false;
    }

    @Override
    protected void initializeBoard() {
        // 在五子棋中，初始棋盘是空的，不需要放置任何棋子
    }

    @Override
    public void refreshValid(PieceStatus type) {
        // 在五子棋中，所有空位都是合法的
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
        // 先落子
        add(type, input);
        if(FiveInRow(input[0], input[1], type)){
            winner = type;
            isOver = true;
        }
        else if(isfull()){
            winner = PieceStatus.EMPTY;
            isOver = true;
        }
        else
            round++;
    }

    @Override
    public boolean isOver() { 
        return isOver; 
    }

    @Override
    public boolean isWaitingForPass() { 
        return false; 
    }

    // 检查是否五子连珠
    private boolean FiveInRow(int x, int y, PieceStatus currentPiece) {
        // 四个方向: 水平、垂直、两个对角线
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};  // 水平、垂直、主对角线、副对角线
        
        // 检查每个方向
        for (int[] dir : directions) {
            if (checkDirection(x, y, dir, currentPiece)) {
                return true;
            }
        }
        return false;
    }

    // 检查某个方向是否五子连珠
    private boolean checkDirection(int x, int y, int[] dir, PieceStatus currentPiece) {
        int count = 1;
        int dx = dir[0];
        int dy = dir[1];
        
        // 正向检查
        count += countPieces(x, y, dx, dy, currentPiece);
        
        // 反向检查
        count += countPieces(x, y, -dx, -dy, currentPiece);
        
        return count >= 5;
    }

    // 计算某个方向上相同颜色的棋子数量
    private int countPieces(int x, int y, int dx, int dy, PieceStatus currentPiece) {
        int count = 0;
        int newX = x + dx;
        int newY = y + dy;
        
        while (newX >= 0 && newX < 8 && newY >= 0 && newY < 8 
               && board[newX][newY].getStatus() == currentPiece) {
            count++;
            newX += dx;
            newY += dy;
        }
        
        return count;
    }
}