/*
 * player is a class that contains a name and a piece type
 * all the actions are conducted by hotspot, so player only contains information
 */
public class player {
    private final String name;
    private final piecetype piecetype; 

    //construct a player with given name and pieceType
    player(String given_name,piecetype symbol){
        name = given_name;
        piecetype = symbol;
    }

    //get the name of the player
    public String getName(){
        return name;
    }

    //get the piece type of the player
    public piecetype getPiecetype(){
        return piecetype;
    }
}