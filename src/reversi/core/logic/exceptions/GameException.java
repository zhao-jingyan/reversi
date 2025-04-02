package reversi.core.logic.exceptions;

/**
 * 游戏异常类
 * 表示游戏过程中可能发生的各种错误
 * 
 * 异常说明：
 * 1. 包含错误代码和错误信息
 * 2. 用于在游戏逻辑中传递错误信息
 */
public class GameException extends RuntimeException {
    private final GameErrorCode code;  // 错误代码

    /**
     * 构造函数
     * @param code 错误代码
     * @param message 错误信息
     */
    public GameException(GameErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 获取错误代码
     * @return 错误代码
     */
    public GameErrorCode getCode() {
        return code;
    }
}
