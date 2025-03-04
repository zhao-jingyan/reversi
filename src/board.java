public final class board {
    piece[][] board;  //store the piece info
    boolean[][] valid; //store the valid info

    //construct an empty board with given size
    board(){
        //new board
        board = new piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new piece();
            }
        }

        //new valid
        valid = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                valid[i][j] = true;
            }
        }

        //clear board
        this.clear();
    }

    //clear the board
    void clear(){
        for(piece[] row: board)
            for(piece item: row)
                item.remove();
        this.placeCenter();
    }

    //place center block
    private void placeCenter(){
        board[3][3].status = piecetype.WHITE;
        board[4][4].status = piecetype.WHITE;      
        board[4][3].status = piecetype.BLACK;
        board[3][4].status = piecetype.BLACK;
        valid[3][3] = false;
        valid[4][4] = false;
        valid[4][3] = false;
        valid[3][4] = false;
    }

    //add a move, gamelogic will make sure the input is secure
    void add(player name ,int[] move){
        board[move[0] - 1][move[1] - 1].add(name);
        valid[move[0] - 1][move[1] - 1] = false;
    }

    boolean isfull(){
        boolean ans = true;
        for(piece[] row: board)
            for(piece item: row)
                if(item.status == piecetype.EMPTY)
                    ans = false;
        return ans;
    }

    //flip the pieces
    boolean flip(int[] input) {
        //locate the piece
        int x = input[0] - 1;
        int y = input[1] - 1;

        //set 8 directions
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        //ans == true means flipped
        boolean ans = false;
        for (int[] dir : directions) {
            ans = this.flipbeam(dir, x, y) || ans;    //flipbeam should not be short-circuited
        }
        return ans;
    }

    private boolean flipbeam(int[] direction, int x, int y) {
        int xp = x;
        int yp = y;
        piecetype piece = board[xp][yp].status;
        int dx = direction[0];
        int dy = direction[1];
        boolean ans = false;
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
            else if(board[xp][yp].status == piecetype.EMPTY)
                break;
        }
        return ans;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private void reFreshValid(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++){
                
            }
    }
}
