package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class SwitchBoardInformation extends InputInformation {
    private final Integer boardNum;
    private final String rawInput;

    private SwitchBoardInformation(Integer boardNum, String rawInput) {
        this.boardNum = boardNum;
        this.rawInput = rawInput;
    }

    public static SwitchBoardInformation create(String input) {
        try {
            @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
            Integer boardNum = Integer.parseInt(input);
            return new SwitchBoardInformation(boardNum, input);
        } catch (NumberFormatException e) {
            return new SwitchBoardInformation(null, input);
        }
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.BOARDNUM;
    }

    @Override
    public Object getInfo() {
        return boardNum;
    }

    public Integer getBoardNum() {
        return boardNum;
    }

    public String getRawInput() {
        return rawInput;
    }
} 