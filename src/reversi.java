public class reversi {
    public static void main(String[] args){
        gamelogic game = new gamelogic();
        input terminal = new input();
        player P1 = new player("Bill_Black", piecetype.BLACK);
        player P2 = new player("Walt_White", piecetype.WHITE);
        board board = new board();
        hotspot hotspot = new hotspot(P1, P2);
        output screen = new output(board, hotspot, P1, P2);

        game.startup(P1, P2, board);
        while(!board.isfull()){
            screen.print();
            int[] move = terminal.getInput();
            game.handleMove(P1, P2, board, move);
            screen.update(board,P1,P2);
        }
        screen.print();
    }
}
