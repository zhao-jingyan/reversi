public class piece {
    pieceStatus status;

    //construct an empty piece
    piece(){
        status = pieceStatus.EMPTY;
    }

    //make a piece empty
    void makeEmpty(){
        status = pieceStatus.EMPTY;
    }
    
    
    //update the move
    void update(player name){
        if(name.priority == 1)
            status = pieceStatus.BLACK;
        else if(name.priority == 2)
            status = pieceStatus.WHITE;
    }

}

