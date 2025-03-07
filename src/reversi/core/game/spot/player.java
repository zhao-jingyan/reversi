package reversi.core.game.spot;

import reversi.core.game.board.piecestatus;

/*
 * player is a class that contains a name and a piece type
 * all the actions are conducted by hotspot, so player only contains information
 */
public class player {
    private final String name;
    private final piecestatus piecetype; 

    //construct a player with given name and pieceType
    player(String given_name,piecestatus symbol){
        name = given_name;
        piecetype = symbol;
    }

    //get the name of the player
    public String getName(){
        return name;
    }

    //get the piece type of the player
    public piecestatus getPiecetype(){
        return piecetype;
    }
}