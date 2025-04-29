package com.reversi.model.input;

/**
 * 输入信息接口
 * 定义了输入信息的基本接口
 */
public interface InputInformation {
    // 获取输入类型
    InputType getInputType();

    // 获取信息
    Object getInfo();
}
