package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class InvalidInformation extends InputInformation {
    private final String rawInput;

    private InvalidInformation(String rawInput) {
        this.rawInput = rawInput;
    }

    public static InvalidInformation create(String input) {
        return new InvalidInformation(input);
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.INVALID;
    }

    @Override
    public Object getInfo() {
        return null;
    }

    public String getRawInput() {
        return rawInput;
    }
} 