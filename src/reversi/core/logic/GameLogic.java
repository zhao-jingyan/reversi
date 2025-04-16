package reversi.core.logic;

import reversi.core.games.GameManager;
import reversi.core.games.game.GameMode;
import reversi.core.logic.exceptions.GameErrorCode;
import reversi.core.logic.exceptions.GameException;
import reversi.model.input.InputInformation;
import reversi.model.output.OutputInfomationFactory;
import reversi.model.output.OutputType;
import reversi.ui.console.Input;
import reversi.ui.console.Output;

public class GameLogic {// 单例类
    private static final GameLogic instance = new GameLogic();
    private static OutputType outputType;

    private GameLogic() {
        outputType = OutputType.REFRESH;

    }

    public static GameLogic getInstance() {
        return instance;
    }

    // 游戏循环
    public static void gameLoop() {
        Output.print(OutputInfomationFactory.create(outputType, GameManager.getInstance()));
        while (logicShouldContinue()) {
            try {
                handleInput(Input.getInput());
                checkGameOver();
                Output.print(OutputInfomationFactory.create(outputType, GameManager.getInstance()));
            } catch (GameException e) {
                outputType = OutputType.INVALID_INPUT;
                Output.printError(e, OutputInfomationFactory.create(outputType, GameManager.getInstance()));
            }
        }
    }

    private static void handleInput(InputInformation input) throws GameException {
        switch (input.getInputType()) {
            case QUIT -> {
                outputType = OutputType.QUIT;
            }
            case BOARDNUM -> {
                // 截获越界的棋盘数
                try {
                    int gameNum = (int) input.getInfo();
                    GameManager.getInstance().switchToGame(gameNum);
                    outputType = OutputType.REFRESH;
                } catch (GameException e) {
                    throw new GameException(GameErrorCode.GAME_NOT_FOUND,
                            "Game " + input.getInfo() + " does not exist");
                }
            }
            case NEWGAME -> {
                GameManager.getInstance().createGame("Bill_Black", "Walt_White", (GameMode) input.getInfo());
                outputType = OutputType.REFRESH;
            }
            case COORDINATES -> {
                GameManager.getInstance().updateCurrentGame(input);
                outputType = OutputType.REFRESH;
            }
            case PASS -> {
                GameManager.getInstance().updateCurrentGame(input);
                outputType = OutputType.REFRESH;
            }
            default -> {
                throw new GameException(GameErrorCode.INVALID_INPUT,
                        "Invalid input");
            }
        }
    }

    private static void checkGameOver() {
        if (outputType != OutputType.QUIT) {
            if (GameManager.getInstance().isCurrentGameOver()) {
                outputType = OutputType.GAME_OVER;
            }
            if (GameManager.getInstance().isAllGamesOver()) {
                outputType = OutputType.ALL_GAMES_OVER;
            }
        }
    }

    private static boolean logicShouldContinue() {
        return outputType != OutputType.QUIT && outputType != OutputType.ALL_GAMES_OVER;
    }

}
