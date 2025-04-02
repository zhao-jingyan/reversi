package reversi.core.logic.exceptions;

/**
 * 游戏错误代码枚举
 * 定义了游戏中可能出现的各种错误类型
 * 
 * 错误类型说明：
 * 1. INVALID_INPUT: 无效输入
 * 2. ILLEGAL_MOVE: 非法移动
 * 3. CONFLICTING_MOVE: 冲突移动
 * 4. MAY_NOT_PASS: 不能跳过
 * 5. GAME_NOT_FOUND: 游戏不存在
 * 6. GAME_ALREADY_OVER: 游戏已结束
 */
public enum GameErrorCode {
    INVALID_INPUT("<Invalid input!>"),           // 无效输入
    ILLEGAL_MOVE("<Illegal move!>"),            // 非法移动
    CONFLICTING_MOVE("<A piece is already at this location!>"),  // 冲突移动
    MAY_NOT_PASS("<Cannot pass when there are valid moves!>"),   // 不能跳过
    GAME_NOT_FOUND("<Game does not exist!>"),    // 游戏不存在
    GAME_ALREADY_OVER("<This game is already over!>");  // 游戏已结束

    private final String defaultMessage;  // 默认错误信息

    /**
     * 构造函数
     * @param defaultMessage 默认错误信息
     */
    GameErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    /**
     * 获取默认错误信息
     * @return 默认错误信息
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }
}