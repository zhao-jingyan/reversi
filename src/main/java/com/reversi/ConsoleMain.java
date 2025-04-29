package com.reversi;

import com.reversi.core.logic.GameLogic;
import com.reversi.ui.console.ConsoleInput;
import com.reversi.ui.console.ConsoleOutput;

public class ConsoleMain {
    public static void main(String[] args) {
        // 设置控制台输入输出
        GameLogic.setInputOutput(new ConsoleInput(), new ConsoleOutput());
        // 游戏主循环
        GameLogic.gameLoop();
    }
} 