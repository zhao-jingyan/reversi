package reversi.core.games.game.board;

/* piece is a class that contains a piece type 
 * number of black and white pieces are set as private static variables
 */
public class Piece {
    private PieceStatus status;

    //construct a piece
    //can only be accessed by board
    public Piece() {
        status = PieceStatus.EMPTY;
    }

    //add a piece
    public void add(PieceStatus status) {
        this.status = status;
    }

    public void addBlack() {
        this.status = PieceStatus.BLACK;
    }

    public void addWhite() {
        this.status = PieceStatus.WHITE;
    }

    //remove a piece
    public void remove() {
        this.status = PieceStatus.EMPTY;
    }

    public void targetValid() {
        this.status = PieceStatus.VALID;
    }

    //flip a piece
    public void flip() {
        if (status == PieceStatus.BLACK)
            status = PieceStatus.WHITE;
        else if (status == PieceStatus.WHITE)
            status = PieceStatus.BLACK;
    }

    //get the status of the piece
    public PieceStatus getStatus() {
        return status;
    }
}