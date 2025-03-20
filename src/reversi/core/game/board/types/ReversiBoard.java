package reversi.core.game.board.types;

import reversi.core.game.board.Board;
import reversi.core.game.board.PieceStatus;

/*
 * ReversiBoard implements the board for ReversiGame
 * It implements the flipping logic for Reversi game
 */
public class ReversiBoard extends Board {
    public ReversiBoard() {
        super();
    }

    @Override
    protected void initializeBoard() {
        // 在黑白棋中，初始化时需要在中心放置四个棋子
        white += 2;
        black += 2;
        board[3][3].addWhite();
        board[4][4].addWhite();      
        board[4][3].addBlack();
        board[3][4].addBlack();
    }

    @Override
    public void flip(int[] input) {
        //locate the piece
        int x = input[0];
        int y = input[1];
        
        //set 8 directions
        int[][] directions = {
            {1, 0}, {-1,  0}, {0,  1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        
        //flip the pieces
        for(int[] dir : directions){
            this.flipbeam(dir, x, y);
        }
    }

    //flip a line of pieces
    private void flipbeam(int[] direction, int x, int y) {
        //set the variables
        int xp = x;
        int yp = y;
        PieceStatus piece = board[x][y].getStatus();
        int dx = direction[0];
        int dy = direction[1];
        
        //flip the pieces
        while((xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)//in boarder
        && board[xp + dx][yp + dy].getStatus() == piece.opp()){           //do not meet same piece
            xp += dx;
            yp += dy;
            
            //going back and flip the pieces
            if(xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8 && board[xp + dx][yp + dy].getStatus() == piece){
                while(xp != x || yp != y){
                    if(board[xp][yp].getStatus() == PieceStatus.BLACK){
                        black--;
                        white++;
                    }
                    else if(board[xp][yp].getStatus() == PieceStatus.WHITE){
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

    @Override
    public void refreshValid(PieceStatus type) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //check if the position is valid
                if(isValidPosition(type, i, j))
                    board[i][j].targetValid();
                //the originally valid position is no longer valid, then set it to empty
                else if(board[i][j].getStatus() == PieceStatus.VALID)
                    board[i][j].remove();
            }
        }
    }

    protected boolean isValidPosition(PieceStatus type, int x, int y) {
        if(board[x][y].getStatus() == PieceStatus.BLACK || board[x][y].getStatus() == PieceStatus.WHITE)
            return false;
        int[][] directions = {
            {1, 0}, {-1,  0}, {0,  1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };
        for (int[] dir : directions)
            if(canFlipInDirection(type, x, y, dir))
                return true;
        return false;
    }

    private boolean canFlipInDirection(PieceStatus type, int x, int y, int[] direction) {
        //set the variables
        int xp = x;
        int yp = y;
        int dx = direction[0];
        int dy = direction[1];
        PieceStatus piece = type;
        PieceStatus opp = type.opp();

        if(!(xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)|| board[xp + dx][yp + dy].getStatus() != opp)//not in boarder or not having an opposite piece in line
            return false;
        else{
            xp += dx;
            yp += dy;
            while((xp + dx >= 0 && xp + dx < 8 && yp + dy >= 0 && yp + dy < 8)  //in boarder
            && board[xp + dx][yp + dy].getStatus() != PieceStatus.EMPTY && board[xp + dx][yp + dy].getStatus() != PieceStatus.VALID){   //do not meet empty or valid
                if(board[xp + dx][yp + dy].getStatus() == piece)
                    return true;
                else if(board[xp + dx][yp + dy].getStatus() == opp){
                    xp += dx;
                    yp += dy;
                }
            }
            return false;
        }
    }
} 