package com.reversi.model.input.types;

import com.reversi.model.input.InputInformation;
import com.reversi.model.input.InputType;

/**
 * 无效输入信息类
 * 表示输入无效或无法识别
 */
public class InvalidInformation implements InputInformation {
    private InvalidInformation() {
    }

    public static InvalidInformation create() {
        return new InvalidInformation();
    }

    @Override
    public InputType getInputType() {
        return InputType.INVALID;
    }

    @Override
    public Object getInfo() {
        return null;
    }
}