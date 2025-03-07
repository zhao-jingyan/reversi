package reversi.core.game.board;

/*
 * piecetype is a enum that contains the type of the piece
 * it has a method to get the opposite type
 */
public enum piecestatus {
    EMPTY, BLACK, WHITE, VALID;

    //get the opposite piece type
    public piecestatus opp(){
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> this;
        };
    }
}