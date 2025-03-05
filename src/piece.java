/* piece is a class that contains a piece type 
 * number of black and white pieces are set as private static variables
 */
public final class piece {
    piecetype status;
    private static int black = 0;
    private static int white = 0;

    //construct an empty piece
    piece(){
        status = piecetype.EMPTY;
    }

    //initialize the number of pieces
    static void initialize(){
        black = 2;
        white = 2;
    }
    
    //get the number of black pieces
    static int getBlackNum(){
        return black;
    }

    //get the number of white pieces
    static int getWhiteNum(){
        return white;
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

    //flip the piece
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

    //get the status of the piece
    piecetype getStatus(){
        return status;
    }
}