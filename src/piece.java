public final class piece {
    pieceStatus status;
    public static int black = 0;
    public static int white = 0;
    //construct an empty piece
    piece(){
        status = pieceStatus.EMPTY;
    }

    static void initialize(){
        black = 2;
        white = 2;
    }
    
    //make a piece empty
    void remove(){
        status = pieceStatus.EMPTY;
    }
    
    //update the move
    void add(player name){
            status = name.symbolPiece;
            if(name.symbolPiece == pieceStatus.BLACK)
                black++;
            else if(name.symbolPiece == pieceStatus.WHITE)
                white++;
    }

    void flip(){
        if(status == pieceStatus.BLACK){
            status = pieceStatus.WHITE;
            black--;
            white++;
        }
        else if(status == pieceStatus.WHITE){
            status = pieceStatus.BLACK;
            white--;
            black++;
        }
    }

}

