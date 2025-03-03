public class gamelogic{
    gamelogic(){}

    void startup(player p1, player p2, board board){
        p1.status = playerStatus.MOVE;
        p2.status = playerStatus.IDLE;
        board.clear();
        piece.initialize();
    }

    void handleMove(player p1, player p2, board board, int[] move){
        //invalid input
        if(move[0] == -1){
            if(p1.status == playerStatus.MOVE)
                p1.status = playerStatus.INVALID;
            else if(p2.status == playerStatus.MOVE)
                p2.status = playerStatus.INVALID;
        }

        // conflicting move
        else if(board.valid[move[0] - 1][move[1] - 1] == false){
            if(p1.status == playerStatus.MOVE)
                p1.status = playerStatus.INVALID;

            else if(p2.status == playerStatus.MOVE)
                p2.status = playerStatus.INVALID;
        }

        //valid input
        else{
            //move
            if(p1.status == playerStatus.MOVE)
                board.add(p1,move[0],move[1]);
            else if(p2.status == playerStatus.MOVE)
                board.add(p2,move[0],move[1]);
            board.flip(move);
            switchStatus(p1, p2);
        }
    }

    private static void switchStatus(player p1, player p2){
        playerStatus status = p1.status;
        p1.status = p2.status;
        p2.status = status;
    }

    

}