package reversi.core.games.game.spot;

import reversi.core.games.game.board.PieceStatus;

/*
 * player is a class that contains a name and a piece type
 * all the actions are conducted by hotspot, so player only contains information
 */
public class Player {
    private final String name;
    private final PieceStatus piecetype;

    // construct a player with given name and pieceType
    public Player(String name, PieceStatus piecetype) {
        this.name = name;
        this.piecetype = piecetype;
    }

    // get the name of the player
    public String getName() {
        return name;
    }

    // get the piece type of the player
    public PieceStatus getPiecetype() {
        return piecetype;
    }
}