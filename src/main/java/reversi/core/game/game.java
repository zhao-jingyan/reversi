package reversi.core.game;

import reversi.core.game.board.board;
import reversi.core.game.spot.hotspot;

public class game {
    private static int count = 0;
    private final int gameNum;
    private final board board;
    private final hotspot spot;

    public game(String p1Name, String p2Name){
        count++;
        gameNum = count; 
        board = new board();
        spot = new hotspot(p1Name, p2Name);

        //initialize game
        spot.initialize();
        board.clear();  
    }

    public void makeMove(int[] move){
        spot.makeMove(board, move);
        spot.tryToSwap(board);  
    }

    public board getBoard(){
        return board;
    }

    public hotspot getSpot(){
        return spot;
    }

    public int getGameNum(){
        return gameNum;
    }
}