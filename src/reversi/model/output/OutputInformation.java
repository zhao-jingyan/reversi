package reversi.model.output;

import reversi.core.games.GameManager;
import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.Board;
import reversi.core.games.game.spot.Player;

public class OutputInformation {
    private final Board board;
    private final int gameNum;
    private final String p1name;
    private final String p2name;
    private final Player chargeplayer;
    private final GameMode gameMode;
    private final GameMode[] gameList;
    private final OutputType outputType;

    public OutputInformation(GameManager instance, OutputType outputType) {
        this.board = instance.getCurrentBoard();
        this.gameNum = instance.getCurrentGame().getGameNum();
        this.p1name = instance.getCurrentGame().getSpot().getP1().getName();
        this.p2name = instance.getCurrentGame().getSpot().getP2().getName();
        this.chargeplayer = instance.getCurrentGame().getSpot().getChargePlayer();
        this.gameMode = instance.getCurrentGame().getGameMode();
        this.gameList = instance.getGameList();
        this.outputType = outputType;
    }

    public Board getBoard() {
        return board;
    }

    public int getGameNum() {
        return gameNum;
    }

    public String getP1Name() {
        return p1name;
    }

    public String getP2Name() {
        return p2name;
    }

    public Player getChargePlayer() {
        return chargeplayer;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public GameMode[] getGameList() {
        return gameList;
    }

    public OutputType getOutputType() {
        return outputType;
    }

}
