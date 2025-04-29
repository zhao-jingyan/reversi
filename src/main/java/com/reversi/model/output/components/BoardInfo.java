package com.reversi.model.output.components;

import com.reversi.core.games.game.board.Board;
import com.reversi.core.games.game.board.PieceStatus;
import com.reversi.core.games.game.spot.Player;

/**
 * 棋盘信息类
 * 包含棋盘状态和当前棋局信息
 */
public class BoardInfo {
    private final Board board;
    private final String player1Name;
    private final String player2Name;
    private final Player chargePlayer;
    private final int currentGameNumber;
    private final int currentRound;
    private final int white;
    private final int black;
    public BoardInfo(Board board, String player1Name, String player2Name, 
                    Player chargePlayer, int currentGameNumber, int currentRound, int white, int black) {
        this.board = board;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.chargePlayer = chargePlayer;
        this.currentGameNumber = currentGameNumber;
        this.currentRound = currentRound;
        this.white = white;
        this.black = black;
    }

    // Getter方法
    public Board getBoard() { return board; }
    public String getPlayer1Name() { return player1Name; }
    public String getPlayer2Name() { return player2Name; }
    public int getCurrentGameNumber() { return currentGameNumber; }
    public int getCurrentRound() { return currentRound; }
    public int getWhite() { return white; }
    public int getBlack() { return black; }
    public PieceStatus getWinner() { return board.getWinner(); }
    public String getChargePlayerName() { return chargePlayer.getName(); }
    public PieceStatus getChargePieceType() { return chargePlayer.getPiecetype(); }
} 