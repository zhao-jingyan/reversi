public class reversi {
    public static void main(String[] args){
        gamelogic game = new gamelogic();
        input terminal = new input();
        player P1 = new player("Bill_Black", pieceStatus.BLACK);
        player P2 = new player("Walt_White", pieceStatus.WHITE);
        board board = new board();
        output screen = new output(board, P1, P2);
        
        game.startup(P1, P2, board);
        while(!board.isfull()){
            screen.print();
            P1.restoreStatus();
            P2.restoreStatus();
            int[] move = terminal.getInput();
            game.handleMove(P1, P2, board, move);
            screen.update(board,P1,P2);
        }
        screen.print();
    }
}
