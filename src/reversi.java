/*
 * main class, conduct the game
 */

public class reversi {
    public static void main(String[] args){
        //conductors, creating essential objects
        board board = new board();
        player P1 = new player("Bill_Black", piecetype.BLACK);
        player P2 = new player("Walt_White", piecetype.WHITE);
        hotspot spot = new hotspot(P1, P2);
        input terminal = new input();
        output screen = new output();

        //initialize game
        spot.initialize();
        board.clear();
        
        //main loop
        while(!board.isfull() && spot.getSpotStatus() != spotstatus.END){
            screen.print(board,P1,P2,spot);
            int[] move = terminal.getInput();
            spot.makeMove(board, move);
            spot.tryToSwap(board);
        }

        //ending, print again
        screen.print(board,P1,P2,spot);
    }
}
