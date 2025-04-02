package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

/**
 * 移动信息类
 * 包含移动的坐标信息
 */
public class MoveInformation implements InputInformation {
    private final int[] move;

    private MoveInformation(int[] move) {
        this.move = move;
    }

    public static MoveInformation create(String input) {
        int[] coordinates = new int[2];
        coordinates[1] = input.charAt(0) - 'A';
        coordinates[0] = input.charAt(1) - '1';
        return new MoveInformation(coordinates);
    }

    @Override
    public InputType getInputType() { return InputType.COORDINATES; }
    @Override
    public Object getInfo() { return move; }
}