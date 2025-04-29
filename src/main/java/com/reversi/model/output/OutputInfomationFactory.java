package com.reversi.model.output;

import com.reversi.core.games.GameManager;
import com.reversi.core.games.game.Game;
import com.reversi.core.games.game.GameMode;
import com.reversi.core.games.game.board.Board;
import com.reversi.core.games.game.spot.Player;
import com.reversi.model.output.components.BoardInfo;
import com.reversi.model.output.components.GameInfo;

/**
 * 输出信息工厂类
 * 负责创建不同类型的输出信息对象
 */
public class OutputInfomationFactory {
    /**
     * 创建刷新游戏状态的输出信息
     * @param gameManager 游戏管理器
     * @return 输出信息对象
     */
    public static OutputInformation create(OutputType type, GameManager gameManager) {
        Game game = gameManager.getCurrentGame();
        Board board = game.getBoard();
        int currentRound = board.getCurrentRound();
        String player1Name = game.getPlayer1().getName();
        String player2Name = game.getPlayer2().getName();
        Player chargePlayer = game.getChargePlayer();
        int currentGameNumber = gameManager.getCurrentGameNumber();
        int totalGames = gameManager.getTotalGames();
        GameMode[] gameList = gameManager.getGameList();

        BoardInfo boardInfo = new BoardInfo(board, player1Name, player2Name, 
                                          chargePlayer, currentGameNumber, currentRound, 
                                          board.getWhite(), board.getBlack());
        GameInfo gameInfo = new GameInfo(gameList, currentGameNumber, totalGames, game.getGameMode());

        return new OutputInformation(boardInfo, gameInfo, type);
    }
} 