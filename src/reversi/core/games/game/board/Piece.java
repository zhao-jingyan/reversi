package reversi.core.games.game.board;

/**
 * 棋子类
 * 表示棋盘上的一个位置
 */
public class Piece {
    // 棋子属性
    private PieceStatus status;

    // construct a piece
    // can only be accessed by board
    public Piece() {
        status = PieceStatus.EMPTY;
    }

    // 棋子操作
    public void add(PieceStatus status) { this.status = status; }
    public void addBlack() { this.status = PieceStatus.BLACK; }
    public void addWhite() { this.status = PieceStatus.WHITE; }
    public void remove() { this.status = PieceStatus.EMPTY; }
    public void targetValid() { this.status = PieceStatus.VALID; }
    public void flip() {
        if (status == PieceStatus.BLACK)
            status = PieceStatus.WHITE;
        else if (status == PieceStatus.WHITE)
            status = PieceStatus.BLACK;
    }

    // Getters
    public PieceStatus getStatus() { return status; }
}