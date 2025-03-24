package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class PassInformation extends InputInformation {
    private PassInformation() {
    }

    public static PassInformation create() {
        return new PassInformation();
    }

    @Override
    public InputType getInputType() {
        return InputType.PASS;
    }

    @Override
    public Object getInfo() {
        return null;
    }
} 