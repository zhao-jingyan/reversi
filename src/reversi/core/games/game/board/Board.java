package reversi.core.games.game.board;

import reversi.core.games.game.GameMode;

/*
 * Board is an abstract class that contains a 2D array of pieces
 * It defines the basic board operations and structure
 * Different game types can extend this class to implement their own board logic
 */
public abstract class Board {
    protected final Piece[][] board;  //store the piece info
    protected int white;
    protected int black;
    
    //construct an empty board with given size
    protected Board(){
        //new board
        board = new Piece[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = new Piece();

        //clear board
        this.clear();
    }

    // 工厂方法，根据类型创建对应的棋盘实例
    public static Board createBoard(GameMode mode) {
        switch (mode) {
            case PEACE -> {
                return new PeaceBoard();
            }
            case REVERSI -> {
                return new ReversiBoard();
            }
            default -> throw new IllegalArgumentException("Unknown board type: " + mode);
        }
    }

    //clear the board - template method
    public final void clear(){
        //initialize the number of pieces
        white = 0;
        black = 0;

        //clear the board
        for(Piece[] row: board)
            for(Piece item: row)
                item.remove();

        //initialize board according to game type
        initializeBoard();

        //refresh the valid positions
        refreshValid(PieceStatus.BLACK);
    }

    //initialize board according to game type
    protected abstract void initializeBoard();
    
    //refresh valid positions
    public abstract void refreshValid(PieceStatus type);

    //flip the pieces
    public abstract void flip(int[] move);

    //adding a move, gamelogic will make sure the input is secure
    public void add(PieceStatus status ,int[] move){
        board[move[0]][move[1]].add(status);
        if(status == PieceStatus.WHITE)
            white++;
        else if(status == PieceStatus.BLACK)
            black++;
    }
    
    //checking board status
    //check if the move is landed on a valid position
    public boolean isValid(int[] move){
        return move[0] != -2 && board[move[0]][move[1]].getStatus() == PieceStatus.VALID;
    }
    
    //check if there is no valid position
    public boolean noValid(){
        boolean ans = true;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if(board[i][j].getStatus() == PieceStatus.VALID)
                    ans = false;
        return ans;
    }
    
    //check if the board is full
    public boolean isfull(){
        for(Piece[] row: board)
            for(Piece item: row)
                if(item.getStatus() == PieceStatus.EMPTY || item.getStatus() == PieceStatus.VALID)
                    return false;
        return true;
    }

    //get the number of white pieces
    public int getWhite(){
        return white;
    }

    //get the number of black pieces
    public int getBlack(){
        return black;
    }

    public Piece[][] getPieceBoard(){
        return board;
    }

    public PieceStatus getPieceStatus(int[] move){
        return board[move[0]][move[1]].getStatus();
    }
}