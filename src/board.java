public class board {
    int round;
    piece[][] board;
    boolean[][] valid;
    int[] lastMove;

    //construct an empty board with given size
    board(){
        board = new piece[8][8];
        valid = new boolean[8][8];
        lastMove = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                valid[i][j] = true;
            }
        }
        round = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new piece();
                board[i][j].remove();
            }
        }
        lastMove[0] = -1;
        lastMove[0] = -1;
        //central block
        board[3][3].status = pieceStatus.WHITE;
        board[4][4].status = pieceStatus.WHITE;      
        board[4][3].status = pieceStatus.BLACK;
        board[3][4].status = pieceStatus.BLACK;
        valid[3][3] = false;
        valid[4][4] = false;
        valid[4][3] = false;
        valid[3][4] = false;
    }

    //clear the board
    void clear(){
        round = 0;
        for(piece[] row: board)
            for(piece item: row)
                item.remove();
        board[3][3].status = pieceStatus.WHITE;
        board[4][4].status = pieceStatus.WHITE;      
        board[4][3].status = pieceStatus.BLACK;
        board[3][4].status = pieceStatus.BLACK;
    }

    //add a move, gamelogic will make sure the input is secure
    void add(player name , int row , int col){
        board[row - 1][col - 1].add(name);
        valid[row - 1][col - 1] = false;
        round++;
        lastMove[0] = row;
        lastMove[1] = col;
    }

    boolean isfull(){
        boolean ans = true;
        for(piece[] row: board)
            for(piece item: row)
                if(item.status == pieceStatus.EMPTY)
                    ans = false;
        return ans;
    }

    //flip the pieces
    boolean flip(int[] input) {
        boolean ans = false;
        int x = input[0] - 1;
        int y = input[1] - 1;
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        for (int[] dir : directions) {
            ans = this.flipbeam(dir, x, y) || ans;    //flipbeam should not be short-circuited
        }
        return ans;
    }

    private boolean flipbeam(int[] direction, int x, int y) {
        boolean ans = false;
        int xp = x;
        int yp = y;
        pieceStatus piece = board[xp][yp].status;
        int dx = direction[0];
        int dy = direction[1];
        while(isValidPosition(xp + dx, yp + dy)){
            xp += dx;
            yp += dy;
            if(board[xp][yp].status == piece){
            xp -= dx;
            yp -= dy;
                while(xp != x || yp != y) {
                    board[xp][yp].flip();
                    xp -= dx;
                    yp -= dy;
                }
                ans = true;
                break;
            }
            else if(board[xp][yp].status == pieceStatus.EMPTY)
                break;
        }
        return ans;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
