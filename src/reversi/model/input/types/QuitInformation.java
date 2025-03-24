package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class QuitInformation extends InputInformation {

    private QuitInformation() {
    }

    public static QuitInformation create() {
        return new QuitInformation();
    }

    @Override
    public InputType getInputType() {
        return InputType.QUIT;
    }

    @Override
    public Object getInfo() {
        return null;
    }

}