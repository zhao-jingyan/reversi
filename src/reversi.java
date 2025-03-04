public class reversi {
    public static void main(String[] args){
        input terminal = new input();
        player P1 = new player("Bill_Black", piecetype.BLACK);
        player P2 = new player("Walt_White", piecetype.WHITE);
        board board = new board();
        hotspot hotspot = new hotspot(P1, P2);
        output screen = new output(board, hotspot, P1, P2);

        hotspot.initialize();
        board.clear();

        while(!board.isfull()){
            screen.print();
            int[] move = terminal.getInput();
            hotspot.makeMove(board, move);
            board.refreshValid(hotspot.getChargePlayer().getPiecetype());
            screen.update(board,P1,P2);
        }
        screen.print();
    }
}
