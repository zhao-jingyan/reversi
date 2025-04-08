package reversi.core.games.game.board;

import reversi.core.games.game.GameMode;
import reversi.core.games.game.board.types.GomokuBoard;
import reversi.core.games.game.board.types.PeaceBoard;
import reversi.core.games.game.board.types.ReversiBoard;

/**
 * 棋盘基类
 * 定义了棋盘的基本结构和操作
 * 
 * 棋盘状态说明：
 * 1. 棋盘大小为8x8
 * 2. 每个位置可以是以下状态之一：
 *    - EMPTY: 空位置
 *    - BLACK: 黑子
 *    - WHITE: 白子
 *    - VALID: 有效落子位置
 * 3. 棋盘维护黑白子的数量统计
 * 4. 棋盘维护当前回合数
 * 5. 棋盘维护获胜者信息
 */
public abstract class Board {
    // 棋盘属性
    protected Piece[][] board;  // 存储棋子信息
    protected int white;        // 白子数量
    protected int black;        // 黑子数量
    protected int round;        // 当前回合数
    protected PieceStatus winner;  // 获胜者

    // 方向常量
    protected static final int[][] DIRECTIONS = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
        {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };
    
    /**
     * 构造函数
     * 初始化棋盘状态：
     * 1. 创建8x8的棋盘
     * 2. 初始化所有位置为空
     * 3. 设置回合数为1
     * 4. 清空棋盘
     */
    protected Board() {
        round = 1;
        board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Piece();
            }
        }
        this.clear();
    }

    /**
     * 创建指定类型的棋盘
     * @param mode 游戏模式
     * @return 对应类型的棋盘实例
     * @throws IllegalArgumentException 如果游戏模式不支持
     */
    public static Board createBoard(GameMode mode) {
        return switch (mode) {
            case PEACE -> new PeaceBoard();
            case REVERSI -> new ReversiBoard();
            case GOMOKU -> new GomokuBoard();
            default -> throw new IllegalArgumentException("Unknown board type: " + mode);
        };
    }

    /**
     * 清空棋盘
     * 1. 重置黑白子数量
     * 2. 清空所有位置
     * 3. 初始化棋盘
     * 4. 刷新有效位置
     */
    public final void clear() {
        white = 0;
        black = 0;
        for (Piece[] row : board) {
            for (Piece item : row) {
                item.setEmpty();
            }
        }
        initializeBoard();
        refreshValid(PieceStatus.BLACK);
    }

    /**
     * 检查移动是否有效
     * @param move 移动位置
     * @return 是否有效
     */
    public boolean isValid(int[] move) {
        return move[0] != -2 && board[move[0]][move[1]].getStatus() == PieceStatus.VALID;
    }

    /**
     * 初始化棋盘
     * 由子类实现具体的初始化逻辑
     */
    protected abstract void initializeBoard();

    /**
     * 刷新有效位置
     * @param type 当前玩家执黑/白
     */
    public abstract void refreshValid(PieceStatus type);

    /**
     * 更新棋盘状态
     * @param move 移动位置
     * @param type 棋子类型
     */
    public abstract void update(int[] move, PieceStatus type);

    /**
     * 检查游戏是否结束
     * @return 是否结束
     */
    public abstract boolean isOver();

    /**
     * 检查是否需要跳过回合
     * @return 是否需要跳过
     */
    public abstract boolean isWaitingForPass();

    /**
     * 添加棋子
     * @param status 棋子状态
     * @param move 落子位置
     */
    protected void add(PieceStatus status, int[] move) {
        board[move[0]][move[1]].add(status);
        if (status == PieceStatus.WHITE) {
            white++;
        } else if (status == PieceStatus.BLACK) {
            black++;
        }
    }

    /**
     * 检查棋盘是否已满
     * @return 是否已满
     */
    protected boolean isfull() {
        for (Piece[] row : board) {
            for (Piece item : row) {
                if (item.getStatus() == PieceStatus.EMPTY || item.getStatus() == PieceStatus.VALID) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查坐标是否在棋盘范围内
     * @param x 横坐标
     * @param y 纵坐标
     * @return 是否在范围内
     */
    protected boolean isInBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * 检查并设置获胜者
     */
    protected void checkWinner() {
        if (white > black) {
            winner = PieceStatus.WHITE;
        } else if (black > white) {
            winner = PieceStatus.BLACK;
        } else {
            winner = PieceStatus.EMPTY;
        }
    }

    // Getters
    public int getWhite() { return white; }
    public int getBlack() { return black; }
    public int getCurrentRound() { return round; }
    public Piece[][] getPieceBoard() { return board; }
    public PieceStatus getPieceStatus(int[] move) { return board[move[0]][move[1]].getStatus(); }
    public PieceStatus getWinner() { return winner; }
}