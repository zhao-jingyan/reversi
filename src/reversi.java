public class reversi {
    public static void main(String[] args){
        gamelogic game = new gamelogic();
        input terminal = new input();
        player P1 = new player("Bill_Black", 1);
        player P2 = new player("Walt_White", 2);
        board board = new board(8,8);
        output screen = new output(board, P1, P2);
        game.startup(P1, P2, board);
        while(true){
            screen.print();
            if(board.isfull())
                break;
            P1.restoreStatus();
            P2.restoreStatus();
            int[] move = terminal.getInput();
            game.handleMove(P1, P2, board, move);
            screen.update(board,P1,P2);
        }
    }
}
