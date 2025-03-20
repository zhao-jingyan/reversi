package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class NewGameInformation extends InputInformation {
    private final String gameMode;
    private final String rawInput;

    private NewGameInformation(String gameMode, String rawInput) {
        this.gameMode = gameMode;
        this.rawInput = rawInput;
    }

    public static NewGameInformation create(String input) {
        return new NewGameInformation(input, input);
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.NEWGAME;
    }

    @Override
    public Object getInfo() {
        return gameMode;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getRawInput() {
        return rawInput;
    }
} 