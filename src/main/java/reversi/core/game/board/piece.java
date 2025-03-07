package reversi.core.game.board;

/* piece is a class that contains a piece type 
 * number of black and white pieces are set as private static variables
 */
public final class piece {
    private piecestatus status;

    //construct a piece
    //can only be accessed by board
    piece(){
        status = piecestatus.EMPTY;
    }

    //add a piece
    void add(piecestatus status){
        this.status = status;
    }

    void addWhite(){
        status = piecestatus.WHITE;
    }

    void addBlack(){
        status = piecestatus.BLACK;
    }

    //remove a piece
    void remove(){
        status = piecestatus.EMPTY;
    }

    void targetValid(){
        status = piecestatus.VALID;
    }

    //flip a piece
    void flip(){
        if(status == piecestatus.WHITE || status == piecestatus.BLACK)
        status = status.opp();
    }

    //get the status of the piece
    public piecestatus getStatus(){
        return status;
    }
}