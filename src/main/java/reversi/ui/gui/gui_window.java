package reversi.ui.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reversi.core.game.board.piece;
import reversi.core.game.board.piecestatus;
import reversi.core.game.game;

public class gui_window extends Application {
    private game currentGame;
    private Button[][] boardButtons;
    private Label statusLabel;
    private Label scoreLabel;

    @Override
    public void start(Stage primaryStage) {
        // 初始化游戏
        currentGame = new game("Black Player", "White Player");
        
        // 创建主布局
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // 创建状态标签
        statusLabel = new Label("黑方回合");
        scoreLabel = new Label("黑方: 2  白方: 2");
        
        // 创建棋盘
        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(2);
        boardGrid.setVgap(2);
        
        // 初始化按钮数组
        boardButtons = new Button[8][8];
        
        // 创建棋盘按钮
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                final int row = i;
                final int col = j;
                button.setOnAction(e -> handleMove(row, col));
                boardButtons[i][j] = button;
                boardGrid.add(button, j, i);
            }
        }

        // 添加组件到主布局
        root.getChildren().addAll(statusLabel, scoreLabel, boardGrid);
        
        // 创建场景
        Scene scene = new Scene(root);
        
        // 设置舞台
        primaryStage.setTitle("黑白棋");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 更新界面显示
        updateBoard();
    }

    private void handleMove(int row, int col) {
        int[] move = {row, col};
        currentGame.makeMove(move);
        updateBoard();
    }

    private void updateBoard() {
        // 获取棋盘状态
        piece[][] boardState = currentGame.getBoard().getPieceBoard();
        
        // 更新按钮显示
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = boardButtons[i][j];
                piecestatus status = boardState[i][j].getStatus();
                
                switch (status) {
                    case BLACK -> button.setStyle("-fx-background-color: black; -fx-shape: \"M50,25 a25,25 0 1,1 0,50 a25,25 0 1,1 0,-50\"");
                    case WHITE -> button.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-shape: \"M50,25 a25,25 0 1,1 0,50 a25,25 0 1,1 0,-50\"");
                    case VALID -> button.setStyle("-fx-background-color: lightgreen");
                    case EMPTY -> button.setStyle("-fx-background-color: green");
                }
            }
        }

        // 更新分数
        int blackCount = currentGame.getBoard().getBlack();
        int whiteCount = currentGame.getBoard().getWhite();
        scoreLabel.setText(String.format("黑方: %d  白方: %d", blackCount, whiteCount));

        // 更新状态
        String playerName = currentGame.getSpot().getChargePlayer().getName();
        piecestatus playerColor = currentGame.getSpot().getChargePlayer().getPiecetype();
        String colorText = playerColor == piecestatus.BLACK ? "黑方" : "白方";
        statusLabel.setText(colorText + " [" + playerName + "] 回合");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
