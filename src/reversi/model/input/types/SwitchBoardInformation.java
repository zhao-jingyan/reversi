package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

/**
 * 切换棋盘信息类
 * 包含要切换到的棋盘编号
 */
public class SwitchBoardInformation implements InputInformation {
    private final int boardNumber;

    private SwitchBoardInformation(int boardNumber) {
        this.boardNumber = boardNumber;
    }

    public static SwitchBoardInformation create(String input) {
        return new SwitchBoardInformation(Integer.parseInt(input));
    }

    @Override
    public InputType getInputType() {
        return InputType.BOARDNUM;
    }

    @Override
    public Object getInfo() {
        return boardNumber;
    }
}