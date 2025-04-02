package reversi.model.input;

import reversi.model.input.types.*;

/**
 * 输入信息工厂类
 * 负责创建不同类型的输入信息对象
 */
public class InputInformationFactory {
    public static InputInformation create(InputType type, String input) {
        input = input.toUpperCase();
        return switch (type) {
            case COORDINATES -> MoveInformation.create(input);
            case PASS -> PassInformation.create();
            case NEWGAME -> NewGameInformation.create(input);
            case BOARDNUM -> SwitchBoardInformation.create(input);
            case QUIT -> QuitInformation.create();
            case INVALID -> InvalidInformation.create();
        };
    }
} 