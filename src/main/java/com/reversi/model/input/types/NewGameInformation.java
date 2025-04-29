package com.reversi.model.input.types;

import com.reversi.core.games.game.GameMode;
import com.reversi.model.input.InputInformation;
import com.reversi.model.input.InputType;
/**
 * 新游戏信息类
 * 包含新游戏的模式信息
 */
public class NewGameInformation implements InputInformation {
    private final GameMode mode;

    private NewGameInformation(GameMode mode) {
        this.mode = mode;
    }

    public static NewGameInformation create(String input) {
        return new NewGameInformation(GameMode.valueOf(input.toUpperCase()));
    }

    @Override
    public InputType getInputType() { 
        return InputType.NEWGAME; 
    }

    @Override
    public Object getInfo() { 
        return mode; 
    }
}