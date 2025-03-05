/* piece is a class that contains a piece type 
 * number of black and white pieces are set as private static variables
 */
public final class piece {
    piecestatus status;

    //construct an empty piece
    piece(){
        status = piecestatus.EMPTY;
    }

    //make a piece empty
    void remove(){
        status = piecestatus.EMPTY;
    }
    
    //update the move
    void add(player name){
        status = name.getPiecetype();
    }

    //flip the piece
    void flip(){
        if(status == piecestatus.BLACK)
            status = piecestatus.WHITE;
        else if(status == piecestatus.WHITE)
            status = piecestatus.BLACK;
    }

    //get the status of the piece
    piecestatus getStatus(){
        return status;
    }
}