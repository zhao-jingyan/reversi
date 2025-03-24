package reversi.ui.console;

import java.io.IOException;
import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.Piece;
import reversi.core.games.game.board.PieceStatus;
import reversi.model.output.OutputInformation;

public class Output {// 静态方法类
    /*
     * 根据输出类型进行不同的处理
     * REFRESH: 刷新界面，clear
     * GAME_OVER: 游戏结束，打印游戏结果
     * QUIT: 退出游戏
     * ALL_GAMES_OVER: 所有游戏结束，打印游戏结果
     * INVALID_INPUT: 无效输入，不clear
     */
    public static void print(OutputInformation output) {
        switch (output.getOutputType()) {
            case REFRESH -> {
                clear();
                printInfo(output);
                System.out.printf(
                        "\n< Coordinates(A1-H8) | Game Num(1-%d) | New Game(peace,reversi) | Pass(pass) | Quit(quit) >\n",
                        output.getGameList().length);
                System.out.printf("[%-10s %c] > ", output.getChargePlayer().getName(),
                        output.getChargePlayer().getPiecetype() == PieceStatus.BLACK ? '○' : '●');
            }
            case GAME_OVER -> {
                clear();
                printInfo(output);
                printGameOver(output);
                System.out.printf("\n< Game Num(1-%d) | New Game(peace,reversi) | Quit(quit) >\n",
                        output.getGameList().length);
                System.out.printf("[%-10s %c] > ", output.getChargePlayer().getName(),
                        output.getChargePlayer().getPiecetype() == PieceStatus.BLACK ? '○' : '●');
            }
            case QUIT -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            case ALL_GAMES_OVER -> {
                clear();
                printInfo(output);
                printGameOver(output);
                System.out.println("\nAll games are over!");
                System.exit(0);
            }
            case INVALID_INPUT -> {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // 打印游戏结束信息
    private static void printGameOver(OutputInformation output) {
        System.out.println("\nGame Over!");
        if (output.getGameMode() == GameMode.REVERSI) {
            if (output.getBoard().getBlack() > output.getBoard().getWhite()) {
                System.out.printf("Player[%s %c] wins!\n", output.getP1Name(),
                        output.getChargePlayer().getPiecetype() == PieceStatus.BLACK ? '○' : '●');
            } else if (output.getBoard().getWhite() > output.getBoard().getBlack()) {
                System.out.printf("Player[%s %c] wins!\n", output.getP2Name(),
                        output.getChargePlayer().getPiecetype() == PieceStatus.BLACK ? '○' : '●');
            } else {
                System.out.println("It's a tie!");
            }
        }
    }

    // 打印游戏信息
    private static void printInfo(OutputInformation output) {
        // head
        System.out.println("  A B C D E F G H");

        for (int row = 0; row < 8; row++) {
            System.out.printf("%d ", row + 1);
            for (Piece item : output.getBoard().getPieceBoard()[row]) {
                switch (item.getStatus()) {
                    case EMPTY -> System.out.printf("· ");
                    case BLACK -> System.out.printf("○ ");
                    case WHITE -> System.out.printf("● ");
                    case VALID -> System.out.printf(output.getGameMode() == GameMode.PEACE ? "· " : "+ ");
                }
            }

            // player info
            switch (row) {
                case 2 -> System.out.printf("   Game %d%-20s", output.getGameNum(), "");
                case 3 -> printPlayerInfo(output, output.getP1Name(), PieceStatus.BLACK, output.getBoard().getBlack());
                case 4 -> printPlayerInfo(output, output.getP2Name(), PieceStatus.WHITE, output.getBoard().getWhite());
                default -> System.out.printf("%-29s", "");
            }

            switch (row) {
                case 0 -> System.out.printf("\n");
                case 1 -> System.out.printf("\n");
                case 2 -> System.out.printf("Game List:\n");
                default -> {
                    if (row - 3 < output.getGameList().length) {
                        System.out.printf("%d. %s\n", row - 2, output.getGameList()[row - 3]);
                    } else {
                        System.out.printf("\n");
                    }
                }
            }
        }

        // 打印剩余的GameList内容
        for (int i = 5; i < output.getGameList().length; i++) {
            System.out.printf("%-47s%d. %s\n", "", i + 1, output.getGameList()[i]);
        }
    }

    // 打印玩家信息
    private static void printPlayerInfo(OutputInformation output, String playerName, PieceStatus pieceType, int score) {
        System.out.printf("   player[%-10s] %c", playerName,
                output.getChargePlayer().getPiecetype() == pieceType ? (pieceType == PieceStatus.BLACK ? '○' : '●')
                        : ' ');
        if (output.getGameMode() != GameMode.PEACE) {
            System.out.printf(" %d%-4s", score, "");
        } else {
            System.out.printf("%-6s", "");
        }
    }

    // 打印错误信息
    public static void printError(Exception e, OutputInformation output) {
        System.err.println(e.getMessage());
        System.out.printf(
                "< Coordinates(A1-H8) | Game Num(1-%d) | New Game(peace,reversi) | Pass(pass) | Quit(quit) > \n",
                output.getGameList().length);
        System.out.printf("[%-10s %c] > ", output.getChargePlayer().getName(),
                output.getChargePlayer().getPiecetype() == PieceStatus.BLACK ? '○' : '●');
    }

    // 清除控制台
    private static void clear() {
        try {
            // 获取操作系统名称
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // 在 Windows 系统中，使用 cls 命令清除控制台
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // 在其他系统中，使用 clear 命令清除控制台
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
        }
    }
}
