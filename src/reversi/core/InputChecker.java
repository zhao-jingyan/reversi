package reversi.core;

import reversi.core.game.Game;
import reversi.core.game.GameManager;
import reversi.core.game.spot.SpotStatus;
import reversi.information.InfoType;

public class InputChecker {
    public static boolean validateInput(InfoType type, String input) {
        return switch (type) {
            case COORDINATES -> validateMove(input);
            case PASS -> validatePass();
            case BOARDNUM -> validateBoardNum(input);
            case NEWGAME -> true;
            case QUIT -> true;
            default -> false;
        };
    }

    private static boolean validateMove(String input) {
        if (input == null || input.length() != 2) {
            return false;
        }
        Game currentGame = GameManager.getInstance().getCurrentGame();
        if (currentGame == null) {
            return false;
        }
        int[] coordinates = parseCoordinates(input);
        return currentGame.getBoard().isValid(coordinates);
    }

    private static boolean validatePass() {
        Game currentGame = GameManager.getInstance().getCurrentGame();
        if (currentGame == null) {
            return false;
        }
        return currentGame.getSpotStatus() == SpotStatus.NOVALID;
    }

    private static boolean validateBoardNum(String input) {
        try {
            int boardNum = Integer.parseInt(input);
            return boardNum > 0 && boardNum <= GameManager.getInstance().getTotalGames();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int[] parseCoordinates(String input) {
        int[] coordinates = new int[2];
        coordinates[0] = input.charAt(1) - '1';
        if (input.charAt(0) <= 'H' && input.charAt(0) >= 'A') {
            coordinates[1] = input.charAt(0) - 'A';
        } else if (input.charAt(0) <= 'h' && input.charAt(0) >= 'a') {
            coordinates[1] = input.charAt(0) - 'a';
        }
        return coordinates;
    }
} 