import java.io.IOException;

public class output {
    board board;//get a copy of gameboard
    player p1;
    player p2;

    //construct a screen
    output(board given_board, player given_p1, player given_p2){
        board = given_board;
        p1 = given_p1;
        p2 = given_p2;
    }

    //print all the information
    public void print(){
    //clear
        clear();
    //head
        System.out.printf("Black: %d\n",board.black);
        System.out.printf("White: %d\n",board.white);
    //first row
        System.out.printf(" ");
        for(int i = 0; i < board.width; i++){
            System.out.printf(" %c",(char)('A' + i));
        }
        System.out.printf("\n");

    //board
        for(int row = 0; row < board.height; row++){
            System.out.printf("%d ",row + 1);
            for(piece item : board.board[row]){
                switch(item.status){
                    case EMPTY -> System.out.printf("· ");
                    case BLACK -> System.out.printf("○ ");
                    case WHITE -> System.out.printf("● ");
                }
            }
            //player info
            if(row == board.height/2 - 1)
                System.out.printf("   player[%s] %c\n",p1.name,p1.status == playerStatus.IDLE ? ' ' : '○');
            else if(row == board.height/2)
                System.out.printf("   player[%s] %c\n",p2.name,p2.status == playerStatus.IDLE ? ' ' : '●');
            else
                System.out.printf("\n");
        }

        System.out.printf("\n");
    //bottom info
        if(board.isfull()){
            if(board.black > board.white)
                System.out.println("Black wins!");
            else if(board.black < board.white)
                System.out.println("White wins!");  
            else if(board.black == board.white)
                System.out.println("A tied game!");      
        }
        else{
        //error info
            if(p1.status == playerStatus.INVALID || p2.status == playerStatus.INVALID)
                System.out.println("Invalid postion! Please retry!");

            //reminder
            System.out.printf("Player [%s] please enter your move:",p1.status != playerStatus.IDLE ? p1.name : p2.name);
        }
    }

    //update screen info
    public void update(board given_board, player given_p1, player given_p2){
        board = given_board;
        p1 = given_p1;
        p2 = given_p2;
    }

    public static void clear() {
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
        } catch (IOException | InterruptedException e) {
        }
    }
}
