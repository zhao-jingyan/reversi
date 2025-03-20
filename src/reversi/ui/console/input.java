/*
 * input is a class that contains a string and a scanner
 * it reads the input from the terminal and formats it into a coordinate
 * invalid input will return -1,-1
 */
package reversi.ui.console;

import java.util.Scanner;
import reversi.info.inputinfo.InfoType;

public class Input {
    private final Scanner scanner;

    //constructor
    public Input(){
        scanner = new Scanner(System.in);
    }

    //get the input from the terminal
    public InputResult getInput(){
        // 第一步：获取原始输入
        String rawInput = getRawInput();
        
        // 第二步：判断输入类型
        InfoType infoType = determineType(rawInput);
        
        return new InputResult(infoType, rawInput);
    }
    
    private String getRawInput() {
        while (!scanner.hasNextLine()){} //wait for input
        return scanner.nextLine();
    }

    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")//检查是否是数字 
    private static InfoType determineType(String input) {
        // 检查是否是坐标
        if (input.length() == 2 && 
            ((input.charAt(0) >= 'A' && input.charAt(0) <= 'H') || 
             (input.charAt(0) >= 'a' && input.charAt(0) <= 'h')) &&
            (input.charAt(1) >= '1' && input.charAt(1) <= '8')) {
            return InfoType.COORDINATES;
        }
        // 检查是否是pass
        else if (input.toLowerCase().equals("pass")) {
            return InfoType.PASS;
        }
        // 检查是否是quit
        else if (input.toLowerCase().equals("quit")) {
            return InfoType.QUIT;
        }   
        // 检查是否是newgame
        else if (input.toLowerCase().equals("peace") || input.toLowerCase().equals("reversi")) {
            return InfoType.NEWGAME;
        }
        // 检查是否是boardnum
        try {
            Integer.parseInt(input);
            return InfoType.BOARDNUM;
        } catch (NumberFormatException e) {
            return InfoType.INVALID;
        }
    }

    public static class InputResult {
        private final InfoType infoType;
        private final String rawInput;

        public InputResult(InfoType infoType, String rawInput) {
            this.infoType = infoType;
            this.rawInput = rawInput;
        }

        public InfoType getInfoType() {
            return infoType;
        }

        public String getRawInput() {
            return rawInput;
        }
    }
}


