public class game {
    private static int count = 0;
    public int gameNum;
    board board;
    hotspot spot;

    game(String p1Name, String p2Name){
        count++;
        gameNum = count; 
        board = new board();
        spot = new hotspot(new player(p1Name, piecestatus.BLACK), new player(p2Name, piecestatus.WHITE));

        //initialize game
        spot.initialize();
        board.clear();  
    }

    void makeMove(int[] move){
        spot.makeMove(board, move);
        spot.tryToSwap(board);  
    }
}