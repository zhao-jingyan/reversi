/*
 * output has two public methods:
 * 1. print(): print the current game status
 * 2. update(): update the game status
 */
import java.io.IOException;

public class output {
    //construct a screen
    output(){
    }
    
    //print all the information
    public void print(game[] games){
        board board = games[0].board;
        player p1 = games[0].spot.getP1();
        player p2 = games[0].spot.getP2();
        hotspot hotspot = games[0].spot;

        //clear
        clear();

        //head
        System.out.printf("Game: %d\n", games[0].gameNum);
        System.out.printf("Black: %d\n",games[0].board.getBlack());
        System.out.printf("White: %d\n",games[0].board.getWhite());

        //first row
        System.out.printf(" ");
        for(int i = 0; i < 8; i++){
            System.out.printf(" %c",(char)('A' + i));
        }
        System.out.printf("\n");

        //board
        for(int row = 0; row < 8; row++){
            System.out.printf("%d ",row + 1);
            for(piece item : board.board[row]){
                switch(item.getStatus()){
                    case EMPTY -> System.out.printf("· ");
                    case BLACK -> System.out.printf("○ ");
                    case WHITE -> System.out.printf("● ");
                    case VALID -> System.out.printf("x ");
                }
            }
            //player info
            switch (row){
                case 3 -> System.out.printf("   player[%s] %c\n" , p1.getName() , hotspot.getChargePlayer().getPiecetype() == piecestatus.BLACK ? '○' : ' ');
                case 4 -> System.out.printf("   player[%s] %c\n" , p2.getName() , hotspot.getChargePlayer().getPiecetype() == piecestatus.WHITE ? '●' : ' ');
                default -> System.out.printf("\n");
            }
        }

        System.out.printf("\n");

        //bottom info
        if(games[1].spot.getSpotStatus() == spotstatus.END &&
           games[2].spot.getSpotStatus() == spotstatus.END &&
           games[3].spot.getSpotStatus() == spotstatus.END){
            System.out.println("All games end!");
        }
        else if(board.isfull() || hotspot.getSpotStatus() == spotstatus.END){
            if(games[0].board.getBlack() > games[0].board.getWhite())
                System.out.println("Black wins!");
            else if(games[0].board.getBlack() < games[0].board.getWhite())
                System.out.println("White wins!");  
            else if(games[0].board.getBlack() == games[0].board.getWhite())
                System.out.println("A tied game!");
            System.out.println("Going to board:");
        }
        else{
            //error info
            if(hotspot.getSpotStatus() == spotstatus.INVALID)
                System.out.println("Invalid postion! Please retry!");

            //reminder
            System.out.printf("Player [%s] please enter your move or switch board:",hotspot.getChargePlayer().getName());
        }
    }

    //clear the screen，generated by llm
    private static void clear() {
        try {
            // 获取操作系统名称
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // 在 Windows 系统中，使用 cls 命令清除控制台
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // 在其他系统中，使用 clear 命令清除控制台
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {}
    }
}