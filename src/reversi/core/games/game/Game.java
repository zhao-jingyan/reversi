package reversi.core.games.game;

import reversi.core.games.game.board.Board;
import reversi.core.games.game.board.PieceStatus;
import reversi.core.games.game.spot.HotSpot;
import reversi.core.games.game.spot.Player;
import reversi.core.logic.exceptions.GameErrorCode;
import reversi.core.logic.exceptions.GameException;

/**
 * 游戏类，代表一个游戏实例
 * 
 * 游戏状态说明：
 * 1. 每个游戏实例包含一个棋盘和两个玩家
 * 2. 游戏状态与棋盘状态同步
 * 3. 游戏结束后，update方法会抛出GameException
 * 4. 游戏维护当前回合的玩家信息
 */
public class Game {
    // 游戏信息
    private final int gameNum;      // 游戏编号
    private final GameMode gameMode;  // 游戏模式
    private final Board board;      // 棋盘
    private final HotSpot spot;     // 玩家管理器
    private boolean isOver;         // 游戏是否结束

    /**
     * 构造函数
     * @param gameNum 游戏编号
     * @param p1Name 玩家1名称
     * @param p2Name 玩家2名称
     * @param gameMode 游戏模式
     */
    public Game(int gameNum, String p1Name, String p2Name, GameMode gameMode) {
        this.gameNum = gameNum;
        this.board = Board.createBoard(gameMode);
        this.spot = new HotSpot(p1Name, p2Name);
        this.gameMode = gameMode;
        this.isOver = false;
        board.clear();
    }

    /**
     * 执行游戏动作
     * 注意：一旦游戏结束（isOver为true），此方法将抛出GameException
     * 
     * @param coordinate 落子坐标
     * @throws GameException 如果游戏已经结束或移动无效
     */
    public void update(int[] coordinate) throws GameException {
        if (isOver) {
            throw new GameException(GameErrorCode.GAME_ALREADY_OVER, "This game is already over!");
        } else {
            try {
                spot.makeMove(board, coordinate);
                isOver = board.isOver();  // 同步Board的结束状态
            } catch (GameException e) {
                throw e;
            }
        }
    }

    // Getters
    public int getGameNumber() { return gameNum; }
    public GameMode getGameMode() { return gameMode; }
    public Board getBoard() { return board; }
    public HotSpot getSpot() { return spot; }
    public boolean isOver() { return isOver; }
    public Player getPlayer1() { return spot.getPlayer1(); }
    public Player getPlayer2() { return spot.getPlayer2(); }
    public Player getChargePlayer() { return spot.getChargePlayer(); }
    public PieceStatus getWinner() { return board.getWinner(); }
}