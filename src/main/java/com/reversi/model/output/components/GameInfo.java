package com.reversi.model.output.components;

import com.reversi.core.games.game.GameMode;

/**
 * 游戏信息类
 * 包含游戏列表和游戏编号等信息
 */
public class GameInfo {
    private final GameMode[] gameList;
    private final int currentGameNumber;
    private final GameMode currentGameMode;
    private final int totalGamesNumber;

    public GameInfo(GameMode[] gameList, int currentGameNumber, int totalGamesNumber, GameMode currentGameMode) {
        this.gameList = gameList;
        this.currentGameNumber = currentGameNumber;
        this.totalGamesNumber = totalGamesNumber;
        this.currentGameMode = currentGameMode;
    }

    // Getter方法
    public GameMode[] getGameList() { return gameList; }
    public int getCurrentGameNumber() { return currentGameNumber; }
    public int getTotalGamesNumber() { return totalGamesNumber; }
    public GameMode getCurrentGameMode() { return currentGameMode; }
} 