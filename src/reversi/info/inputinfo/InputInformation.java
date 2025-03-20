package reversi.info.inputinfo;

import reversi.info.inputinfo.types.InvalidInformation;
import reversi.info.inputinfo.types.MoveInformation;
import reversi.info.inputinfo.types.NewGameInformation;
import reversi.info.inputinfo.types.PassInformation;
import reversi.info.inputinfo.types.QuitInformation;
import reversi.info.inputinfo.types.SwitchBoardInformation;

/*
 * Information is an abstract class that contains the information from the input
 * it has a method to get the type of the information
 */
public abstract class InputInformation {
    protected InputInformation() {
    }

    public abstract InfoType getInfoType();
    
    // 统一的获取信息方法
    public abstract Object getInfo();

    // 工厂方法，根据类型创建对应的信息对象
    public static InputInformation create(InfoType type, String input) {
        return switch (type) {
            case COORDINATES -> MoveInformation.create(input);
            case PASS -> PassInformation.create(input);
            case NEWGAME -> NewGameInformation.create(input);
            case BOARDNUM -> SwitchBoardInformation.create(input);
            case QUIT -> QuitInformation.create(input);
            case INVALID -> InvalidInformation.create(input);
        };
    }
}
