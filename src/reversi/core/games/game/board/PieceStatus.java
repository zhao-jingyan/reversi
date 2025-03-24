package reversi.core.games.game.board;

/*
 * piecetype is a enum that contains the type of the piece
 * it has a method to get the opposite type
 */
public enum PieceStatus {
    EMPTY, VALID, BLACK, WHITE;
    
    //get the opposite piece type
    public PieceStatus opp() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> this;
        };
    }
}