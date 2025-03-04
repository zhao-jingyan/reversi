public final class board {
    piece[][] board;  //store the piece info

    //construct an empty board with given size
    board(){
        //new board
        board = new piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new piece();
            }
        }
        //clear board
        this.clear();
    }

    //clear the board
    void clear(){
        piece.initialize();
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
    }

    //add a move, gamelogic will make sure the input is secure
    void add(player name ,int[] move){
        board[move[0] - 1][move[1] - 1].add(name);
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
    void flip(int[] input) {
        //locate the piece
        int x = input[0] - 1;
        int y = input[1] - 1;

        //set 8 directions
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        for (int[] dir : directions) {
            this.flipbeam(dir, x, y);
        }
    }

    private void flipbeam(int[] direction, int x, int y) {
        int xp = x;
        int yp = y;
        piecetype piece = board[xp][yp].status;
        int dx = direction[0];
        int dy = direction[1];

        while(isValidPosition(xp + dx, yp + dy) && board[xp + dx][yp + dy].status != piecetype.EMPTY){
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
                break;
            }
        }
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    //
    private void refreshValid(piecetype type){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++){
                if(isValidPosition(type, i, j)){

                }
            }
    }


    public boolean isValid(int[] move){
        return board[move[0] - 1][move[1] - 1].getStatus() == piecetype.VALID;
    }

    public boolean noValid(){
        boolean ans = true;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(board[i][j].getStatus() == piecetype.VALID)
                    ans = false;
        return ans;
    }

}

