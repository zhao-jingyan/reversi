package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class SwitchBoardInformation extends InputInformation {
    private final Integer boardNum;

    private SwitchBoardInformation(Integer boardNum) {
        this.boardNum = boardNum;
    }

    public static SwitchBoardInformation create(String input) {
        return new SwitchBoardInformation(Integer.valueOf(input));
    }

    @Override
    public InputType getInputType() {
        return InputType.BOARDNUM;
    }

    @Override
    public Object getInfo() {
        return boardNum;
    }
}