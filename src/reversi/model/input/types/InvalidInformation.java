package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class InvalidInformation extends InputInformation {

    private InvalidInformation() {
    }

    public static InvalidInformation create() {
        return new InvalidInformation();
    }

    @Override
    public InputType getInputType() {
        return InputType.INVALID;
    }

    @Override
    public Object getInfo() {
        return null;
    }
}