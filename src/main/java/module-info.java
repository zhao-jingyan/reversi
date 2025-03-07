module reversi {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    
    exports reversi;
    exports reversi.ui.gui;
    exports reversi.core.game;
    exports reversi.core.game.board;
    exports reversi.core.game.spot;
    exports reversi.ui.console;
} 