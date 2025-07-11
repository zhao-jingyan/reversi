/*
 * input is a class that contains a string and a scanner
 * it reads the input from the terminal and formats it into a coordinate
 * invalid input will return -1,-1
 */
package com.reversi.ui.console;

import java.util.Scanner;

import com.reversi.model.input.InputInformation;
import com.reversi.model.input.InputInformationFactory;
import com.reversi.model.input.InputType;
import com.reversi.ui.Input;

public class ConsoleInput implements Input {// 静态方法类
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public InputInformation getInput() {
        // 第一步：读取输入
        while (!scanner.hasNextLine()) {
        } // wait for input
        String rawInput = scanner.nextLine();
        // 第二步：判断输入类型
        InputType infoType = determineType(rawInput);

        // 第三步：根据输入类型创建对应的信息对象
        return InputInformationFactory.create(infoType, rawInput);

    }

    // 判断输入类型
    private static InputType determineType(String input) {
        // 检查是否是坐标
        if (input.length() == 2 &&
                ((input.charAt(0) >= 'A' && input.charAt(0) <= 'H') ||
                        (input.charAt(0) >= 'a' && input.charAt(0) <= 'h'))
                &&
                (input.charAt(1) >= '1' && input.charAt(1) <= '8')) {
            return InputType.COORDINATES;
        }
        // 检查是否是pass
        else if (input.toLowerCase().equals("pass")) {
            return InputType.PASS;
        }
        // 检查是否是quit
        else if (input.toLowerCase().equals("quit")) {
            return InputType.QUIT;
        }
        // 检查是否是newgame
        else if (input.toLowerCase().equals("peace") || input.toLowerCase().equals("reversi") || input.toLowerCase().equals("gomoku")) {
            return InputType.NEWGAME;
        }
        // 检查是否是boardnum
        else {
            try {
                if (Integer.parseInt(input) >= 1) {
                    return InputType.BOARDNUM;
                } else {
                    return InputType.INVALID;
                }
            } catch (NumberFormatException e) {
                return InputType.INVALID;
            }
        }
    }
}
