package reversi.model.input;

import reversi.model.input.types.*;

/*
 * Information is an abstract class that contains the information from the input
 * it has a method to get the type of the information
 */

public abstract class InputInformation{
    protected InputInformation() {
    }

    // 获取输入类型
    public abstract InputType getInputType();
    
    // 获取信息
    public abstract Object getInfo();

    // 工厂方法，根据类型创建对应的信息对象，可以被input调用
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
