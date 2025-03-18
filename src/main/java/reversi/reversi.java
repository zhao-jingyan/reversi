package reversi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reversi.core.game.board.piece;
import reversi.core.game.board.piecestatus;
import reversi.core.game.game;
import reversi.core.game.spotstatus;

public class reversi extends Application {
    private game[] games;
    private Button[][] boardButtons;
    private Label statusLabel;
    private Label scoreLabel;
    private Button[] switchButtons;

    @Override
    public void start(Stage primaryStage) {
        initializeGames();
        
        // 创建主布局
        VBox root = new VBox(3);
        root.setPadding(new Insets(3));

        // 创建状态标签
        statusLabel = new Label("黑方回合");
        scoreLabel = new Label("黑方: 2  白方: 2");
        
        // 创建棋盘
        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(0);
        boardGrid.setVgap(0);
        
        // 初始化按钮数组
        boardButtons = new Button[8][8];
        
        // 创建棋盘按钮
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = new Button();
                button.setPrefSize(30, 30);
                button.setMinSize(30, 30);
                button.setMaxSize(30, 30);
                final int row = i;
                final int col = j;
                button.setOnAction(e -> handleMove(row, col));
                boardButtons[i][j] = button;
                boardGrid.add(button, j, i);
            }
        }

        // 创建切换游戏按钮
        HBox switchBox = new HBox(5);
        switchButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            final int gameIndex = i + 1;
            Button switchBtn = new Button("游戏 " + gameIndex);
            switchBtn.setOnAction(e -> switchGame(gameIndex));
            switchBtn.setDisable(gameIndex == 1); // 当前游戏按钮禁用
            switchButtons[i] = switchBtn;
            switchBox.getChildren().add(switchBtn);
        }

        // 添加组件到主布局
        root.getChildren().addAll(statusLabel, scoreLabel, boardGrid, switchBox);
        
        // 创建场景
        Scene scene = new Scene(root);
        
        // 设置舞台
        primaryStage.setTitle("REVERSI");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 更新界面显示
        updateBoard();
    }

    private void initializeGames() {
        games = new game[4];
        for(int i = 1; i < 4; i++) {
            games[i] = new game("Bill_Black","Walt_White");
        }
        games[0] = games[1];
    }

    private void handleMove(int row, int col) {
        int[] move = {row, col};
        games[0].makeMove(move);
        updateBoard();
    }

    private void switchGame(int index) {
        games[0] = games[index];
        for (int i = 0; i < 3; i++) {
            switchButtons[i].setDisable(i + 1 == index);
        }
        updateBoard();
    }

    private void updateBoard() {
        // 获取棋盘状态
        piece[][] boardState = games[0].getBoard().getPieceBoard();
        
        // 更新按钮显示
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = boardButtons[i][j];
                piecestatus status = boardState[i][j].getStatus();
                
                switch (status) {
                    case BLACK -> button.setStyle("-fx-background-color: #F5DEB3, black; -fx-border-color: black; -fx-border-width: 1; -fx-content-display: graphic-only; -fx-padding: 5; -fx-background-radius: 0, 50%; -fx-background-insets: 0, 5 5 5 5;");
                    case WHITE -> button.setStyle("-fx-background-color: #F5DEB3, black, white; -fx-border-color: black; -fx-border-width: 1; -fx-content-display: graphic-only; -fx-padding: 5; -fx-background-radius: 0, 50%, 50%; -fx-background-insets: 0, 5 5 5 5, 7 7 7 7;");
                    case VALID -> button.setStyle("-fx-background-color: #F5DEB3, black; -fx-border-color: black; -fx-border-width: 1; -fx-content-display: graphic-only; -fx-padding: 11; -fx-background-radius: 0, 50%; -fx-background-insets: 0, 12 12 12 12;");
                    case EMPTY -> button.setStyle("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1;");
                }
            }
        }

        // 更新分数
        int blackCount = games[0].getBoard().getBlack();
        int whiteCount = games[0].getBoard().getWhite();
        scoreLabel.setText(String.format("黑方: %d  白方: %d", blackCount, whiteCount));

        // 更新状态
        String playerName = games[0].getSpot().getChargePlayer().getName();
        piecestatus playerColor = games[0].getSpot().getChargePlayer().getPiecetype();
        String colorText = playerColor == piecestatus.BLACK ? "黑方" : "白方";
        
        if (games[0].getSpot().getSpotStatus() == spotstatus.END) {
            String winner = blackCount > whiteCount ? "黑方胜利！" : 
                          blackCount < whiteCount ? "白方胜利！" : "平局！";
            statusLabel.setText(winner);
        } else {
            String turnSymbol = playerColor == piecestatus.BLACK ? "●" : "○";
            statusLabel.setText(colorText + " [" + playerName + "] " + turnSymbol + " 回合");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
