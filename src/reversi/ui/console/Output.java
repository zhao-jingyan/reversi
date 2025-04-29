package reversi.ui.console;

import java.io.IOException;

import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.Piece;
import reversi.core.games.game.board.PieceStatus;
import reversi.core.logic.exceptions.GameErrorCode;
import reversi.core.logic.exceptions.GameException;
import reversi.model.output.OutputInformation;

public class Output {
    public static void print(OutputInformation output) {
        switch (output.getOutputType()) {
            case REFRESH -> {
                clear();
                printInfo(output);
                System.out.printf(
                        "\n< Coordinates(A1-H8) | Game Num(1-%d) | New Game(peace,reversi,gomoku) | Pass(pass) | Quit(quit) >\n",
                        output.getGameInfo().getGameList().length);
                System.out.printf("[%-10s %c]%s> ", output.getBoardInfo().getChargePlayerName(),
                        output.getBoardInfo().getChargePieceType() == PieceStatus.BLACK ? '○' : '●',
                        output.getBoardInfo().getBoard().isWaitingForPass() ? " (should pass) " : " ");
            }
            case GAME_OVER -> {
                clear();
                printInfo(output);
                printGameOver(output);
                System.out.printf("\n< Game Num(1-%d) | New Game(peace,reversi,gomoku) | Quit(quit) >\n",
                        output.getGameInfo().getGameList().length);
                System.out.printf("[%-10s %c] > ", output.getBoardInfo().getChargePlayerName(),
                        output.getBoardInfo().getChargePieceType() == PieceStatus.BLACK ? '○' : '●');
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
            default -> throw new IllegalArgumentException("Unexpected value: " + output.getOutputType());
        }
    }

    // 打印游戏结束信息
    private static void printGameOver(OutputInformation output) {
        System.out.println("\nGame Over!");
        if (output.getGameInfo().getCurrentGameMode() == GameMode.REVERSI) {
            System.out.printf("Player[%s ○]: %d\n", output.getBoardInfo().getPlayer1Name(), output.getBoardInfo().getBlack());
            System.out.printf("Player[%s ●]: %d\n", output.getBoardInfo().getPlayer2Name(), output.getBoardInfo().getWhite());
        }
        if(output.getBoardInfo().getWinner() != null) {
            switch (output.getBoardInfo().getWinner()) {
                case BLACK -> System.out.printf("Player[%s ○] wins!\n", output.getBoardInfo().getPlayer1Name());
                case WHITE -> System.out.printf("Player[%s ●] wins!\n", output.getBoardInfo().getPlayer2Name());
                default -> System.out.println("It's a tie!");
            }
        }
    }

    // 打印游戏信息
    private static void printInfo(OutputInformation output) {
        // head
        System.out.println("  A B C D E F G H");

        for (int row = 0; row < 8; row++) {
            System.out.printf("%d ", row + 1);
            for (Piece item : output.getBoardInfo().getBoard().getPieceBoard()[row]) {
                switch (item.getStatus()) {
                    case EMPTY -> System.out.printf("· ");
                    case BLACK -> System.out.printf("○ ");
                    case WHITE -> System.out.printf("● ");
                    case VALID -> System.out.printf(output.getGameInfo().getCurrentGameMode() == GameMode.REVERSI ? "+ " : "· ");
                }
            }

            // player info
            switch (row) {
                case 2 -> System.out.printf("   Game %d%-20s", output.getBoardInfo().getCurrentGameNumber(), "");
                case 3 -> printPlayerInfo(output, output.getBoardInfo().getPlayer1Name(), PieceStatus.BLACK, output.getBoardInfo().getBlack());
                case 4 -> printPlayerInfo(output, output.getBoardInfo().getPlayer2Name(), PieceStatus.WHITE, output.getBoardInfo().getWhite());
                case 5 -> {
                    if(output.getGameInfo().getCurrentGameMode() == GameMode.GOMOKU) {
                        System.out.printf("   Current Round: %-11d", output.getBoardInfo().getCurrentRound());
                    }
                    else {
                        System.out.printf("%-29s", "");
                    }
                }
                default -> System.out.printf("%-29s", "");
            }

            switch (row) {
                case 0 -> System.out.printf("\n");
                case 1 -> System.out.printf("\n");
                case 2 -> System.out.printf("Game List:\n");
                default -> {
                    if (row - 3 < output.getGameInfo().getGameList().length) {
                        System.out.printf("%d. %s\n", row - 2, output.getGameInfo().getGameList()[row - 3]);
                    } else {
                        System.out.printf("\n");
                    }
                }
            }
        }

        // 打印剩余的GameList内容
        for (int i = 5; i < output.getGameInfo().getGameList().length; i++) {
            System.out.printf("%-47s%d. %s\n", "", i + 1, output.getGameInfo().getGameList()[i]);
        }
    }

    // 打印玩家信息
    private static void printPlayerInfo(OutputInformation output, String playerName, PieceStatus pieceType, int score) {
        System.out.printf("   player[%-10s] %c", playerName,
                output.getBoardInfo().getChargePieceType() == pieceType ? (pieceType == PieceStatus.BLACK ? '○' : '●')
                        : ' ');
        if (output.getGameInfo().getCurrentGameMode() == GameMode.REVERSI) {
            System.out.printf(" %-2d%-3s", score, "");
        } else {
            System.out.printf("%-6s", "");
        }
    }

    public static void printError(GameException e, OutputInformation output) {
        switch (e.getCode()) {
            case GameErrorCode.GAME_ALREADY_OVER -> {
                System.err.println(e.getMessage());
            }
            case GameErrorCode.GAME_NOT_FOUND -> {
                System.err.println(e.getMessage());
            }
            case GameErrorCode.ILLEGAL_MOVE -> {
                System.err.println(e.getMessage());
            }
            case GameErrorCode.INVALID_INPUT -> {
                System.err.println(e.getMessage());
            }
            default -> {
                System.err.println(e.getMessage());
            }
        }
        System.out.printf(
            "\n< Coordinates(A1-H8) | Game Num(1-%d) | New Game(peace,reversi,gomoku) | Pass(pass) | Quit(quit) >\n",
            output.getGameInfo().getGameList().length);
        System.out.printf("[%-10s %c]%s> ", output.getBoardInfo().getChargePlayerName(),
        output.getBoardInfo().getChargePieceType() == PieceStatus.BLACK ? '○' : '●',
        output.getBoardInfo().getBoard().isWaitingForPass() ? " (should pass) " : " ");
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
