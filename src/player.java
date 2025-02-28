public class player {
    String name;
    pieceStatus symbolPiece; 
    playerStatus status;

    //construct a player with given name and pieceType
    player(String given_name,pieceStatus symbol){
        name = given_name;
        symbolPiece = symbol;
        status = playerStatus.IDLE;
    }

    public String showName(){
        return name;
    }

    void restoreStatus(){
        if(status == playerStatus.INVALID)
            status = playerStatus.MOVE;
    }
}
