package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class QuitInformation extends InputInformation {
    private final String rawInput;

    private QuitInformation(String rawInput) {
        this.rawInput = rawInput;
    }

    public static QuitInformation create(String input) {
        return new QuitInformation(input);
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.QUIT;
    }

    @Override
    public Object getInfo() {
        return null;
    }

    public String getRawInput() {
        return rawInput;
    }
} 