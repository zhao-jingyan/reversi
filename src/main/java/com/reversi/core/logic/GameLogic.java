package com.reversi.core.logic;

import com.reversi.core.games.GameManager;
import com.reversi.core.games.game.GameMode;
import com.reversi.core.logic.exceptions.GameErrorCode;
import com.reversi.core.logic.exceptions.GameException;
import com.reversi.model.input.InputInformation;
import com.reversi.model.output.OutputInfomationFactory;
import com.reversi.model.output.OutputType;
import com.reversi.ui.Input;
import com.reversi.ui.Output;

public class GameLogic {// 单例类
    private static final GameLogic instance = new GameLogic();
    private static OutputType outputType;
    private static Input input;
    private static Output output;

    private GameLogic() {
        outputType = OutputType.REFRESH;
    }

    public static GameLogic getInstance() {
        return instance;
    }

    public static void setInputOutput(Input input, Output output) {
        GameLogic.input = input;
        GameLogic.output = output;
    }

    // 游戏循环
    public static void gameLoop() {
        output.print(OutputInfomationFactory.create(outputType, GameManager.getInstance()));
        while (logicShouldContinue()) {
            try {
                handleInput(input.getInput());
                checkGameOver();
                output.print(OutputInfomationFactory.create(outputType, GameManager.getInstance()));
            } catch (GameException e) {
                outputType = OutputType.INVALID_INPUT;
                output.printError(e, OutputInfomationFactory.create(outputType, GameManager.getInstance()));
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
            case COORDINATES, PASS-> {
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
