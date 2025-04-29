package com.reversi.model.input.types;

import com.reversi.model.input.InputInformation;
import com.reversi.model.input.InputType;

/**
 * 跳过信息类
 * 表示玩家选择跳过当前回合
 */
public class PassInformation implements InputInformation {
    private PassInformation() {
    }

    public static PassInformation create() {
        return new PassInformation();
    }

    @Override
    public InputType getInputType() {
        return InputType.PASS;
    }

    @Override
    public Object getInfo() {
        return null;
    }
}