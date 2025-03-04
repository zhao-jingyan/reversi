public class player {
    private final String name;
    private final piecetype piecetype; 

    //construct a player with given name and pieceType
    player(String given_name,piecetype symbol){
        name = given_name;
        piecetype = symbol;
    }

    public String getName(){
        return name;
    }

    public piecetype getPiecetype(){
        return piecetype;
    }
}
