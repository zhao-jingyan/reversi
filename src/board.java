public class board {
    int width;
    int height;
    int round;
    piece[][] board;
    boolean[][] valid;
    //construct an empty board with given size
    board(int given_width,int given_height){
        width = given_width;
        height = given_height;
        board = new piece[height][width];
        valid = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                valid[i][j] = true;
            }
        }
        round = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new piece();
                board[i][j].makeEmpty();
            }
        }
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
                item.makeEmpty();
        board[3][3].status = pieceStatus.WHITE;
        board[4][4].status = pieceStatus.WHITE;      
        board[4][3].status = pieceStatus.BLACK;
        board[3][4].status = pieceStatus.BLACK;
    }

    //add a move, gamelogic will make sure the input is secure
    void add(player name , int row , int col){
        board[row - 1][col - 1].update(name);
        valid[row - 1][col - 1] = false;
        round++;
    }

    boolean isfull(){
        boolean ans = true;
        for(piece[] row: board)
            for(piece item: row)
                if(item.status == pieceStatus.EMPTY)
                    ans = false;
        return ans;
    }

    // boolean iswin(){
    //     //winning condition met
    //     return true;
    // }

    // void flip(){
    //     //flip the pieces
    // }


}
