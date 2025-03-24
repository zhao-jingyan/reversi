package reversi.model.input.types;

import reversi.core.games.game.GameMode;
import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class NewGameInformation extends InputInformation {
    private final GameMode gameMode;

    private NewGameInformation(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public static NewGameInformation create(String input) {
        GameMode gameMode = GameMode.valueOf(input.toUpperCase());
        return new NewGameInformation(gameMode);
    }

    @Override
    public InputType getInputType() {
        return InputType.NEWGAME;
    }

    @Override
    public GameMode getInfo() {
        return gameMode;
    }

} 