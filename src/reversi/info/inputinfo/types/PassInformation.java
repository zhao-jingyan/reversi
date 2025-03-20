package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class PassInformation extends InputInformation {
    private final String rawInput;

    private PassInformation(String rawInput) {
        this.rawInput = rawInput;
    }

    public static PassInformation create(String input) {
        return new PassInformation(input);
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.PASS;
    }

    @Override
    public Object getInfo() {
        return null;
    }

    public String getRawInput() {
        return rawInput;
    }
} 