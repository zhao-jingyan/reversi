package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

public class MoveInformation extends InputInformation {
    private final int[] coordinates;

    private MoveInformation(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public static MoveInformation create(String input) {
        int[] coordinates = new int[2];
        coordinates[1] = input.charAt(0) - 'A';
        coordinates[0] = input.charAt(1) - '1';
        return new MoveInformation(coordinates);
    }

    @Override
    public InputType getInputType() {
        return InputType.COORDINATES;
    }

    @Override
    public int[] getInfo() {
        return coordinates;
    }
}