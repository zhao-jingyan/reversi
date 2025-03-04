public class reversi {
    public static void main(String[] args){
        input terminal = new input();
        player P1 = new player("Bill_Black", piecetype.BLACK);
        player P2 = new player("Walt_White", piecetype.WHITE);
        board board = new board();
        hotspot spot = new hotspot(P1, P2);
        output screen = new output(board, spot, P1, P2);

        spot.initialize();
        board.clear();
        
        while(!board.isfull() && spot.getSpotStatus() != spotstatus.END){
            screen.print();
            int[] move = terminal.getInput();
            spot.makeMove(board, move);
            spot.afterMove(board);
            screen.update(board,P1,P2,spot);
        }
        screen.print();

    }
}
