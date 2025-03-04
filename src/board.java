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
        refreshValid(piecetype.BLACK);
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
        board[move[0]][move[1]].add(name);
    }

    boolean isfull(){
        for(piece[] row: board)
            for(piece item: row)
                if(item.status == piecetype.EMPTY || item.status == piecetype.VALID)
                    return false;
        return true;
    }

    //flip the pieces
    void flip(int[] input) {
        //locate the piece
        int x = input[0];
        int y = input[1];

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
        piecetype piece = board[x][y].getStatus();
        int dx = direction[0];
        int dy = direction[1];

        while((xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)//in boarder
            && board[xp + dx][yp + dy].getStatus() == piece.opp()){    //do not meet end            //do not meet same piece
            xp += dx;
            yp += dy;
        
            if(xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8 && 
                board[xp + dx][yp + dy].getStatus() == piece){
                while(xp != x || yp != y){
                    board[xp][yp].flip();
                    xp -= dx;
                    yp -= dy;
                }
                break;
            }
        }
    }

    //
    public void refreshValid(piecetype type){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(isValidPosition(type, i, j))
                    board[i][j].status = piecetype.VALID;
                else if(board[i][j].getStatus() == piecetype.VALID)
                    board[i][j].status = piecetype.EMPTY;
            }
        }
    }

    private boolean isValidPosition(piecetype type, int x, int y){
        if(board[x][y].getStatus() == piecetype.BLACK || board[x][y].getStatus() == piecetype.WHITE){
            return false;
        }
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        for (int[] dir : directions) 
            if(canFlipInDirection(type, x, y, dir))
                return true;
        return false;
    }

    //检查在某个方向上是否可以翻转棋子
    private boolean canFlipInDirection(piecetype type, int x, int y, int[] direction) {
        int xp = x;
        int yp = y;
        int dx = direction[0];
        int dy = direction[1];
        piecetype piece = type;
        piecetype opp = type.opp();
        if(!(xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)
            || board[xp + dx][yp + dy].status != opp)
            return false;
        else{
            xp += dx;
            yp += dy;
            while((xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)//in boarder
            && board[xp + dx][yp + dy].status != piecetype.EMPTY && board[xp + dx][yp + dy].status != piecetype.VALID){    //do not meet end
                if(board[xp + dx][yp + dy].status == piece)
                    return true;
                else if(board[xp + dx][yp + dy].status == opp){
                    xp += dx;
                    yp += dy;
                }
            }
            return false;
        }
    }

    public boolean isValid(int[] move){
        return move[0] != -1 && board[move[0]][move[1]].getStatus() == piecetype.VALID;
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

