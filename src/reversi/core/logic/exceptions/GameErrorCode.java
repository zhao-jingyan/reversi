package reversi.core.logic.exceptions;

public enum GameErrorCode {
    INVALID_INPUT("<Invalid input!>"),
    ILLEGAL_MOVE("<Illegal move!>"),
    CONFLICTING_MOVE("<A piece is already at this location!>"),
    MAY_NOT_PASS("<Cannot pass when there are valid moves!>"),
    GAME_NOT_FOUND("<Game does not exist!>");

    private final String defaultMessage;

    GameErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}