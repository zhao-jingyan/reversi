public final class piece {
    piecetype status;
    public static int black = 0;
    public static int white = 0;
    //construct an empty piece
    piece(){
        status = piecetype.EMPTY;
    }

    static void initialize(){
        black = 2;
        white = 2;
    }
    
    //make a piece empty
    void remove(){
        status = piecetype.EMPTY;
    }
    
    //update the move
    void add(player name){
            status = name.getPiecetype();
            if(name.getPiecetype() == piecetype.BLACK)
                black++;
            else if(name.getPiecetype() == piecetype.WHITE)
                white++;
    }

    void flip(){
        if(status == piecetype.BLACK){
            status = piecetype.WHITE;
            black--;
            white++;
        }
        else if(status == piecetype.WHITE){
            status = piecetype.BLACK;
            white--;
            black++;
        }
    }

    piecetype getStatus(){
        return status;
    }
}

