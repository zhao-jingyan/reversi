package reversi.model.input.types;

import reversi.model.input.InputInformation;
import reversi.model.input.InputType;

/**
 * 退出信息类
 * 表示玩家选择退出游戏
 */
public class QuitInformation implements InputInformation {
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