/*
 * hotspot is a class that contains two players, one is in spot, the other is not
 * inspired by civilization VI, having a mode called "hot spot" mode, in which player take turns to take action
 * so player contains only information, while hotspot is the one that takes action
 * it has a status to indicate what's happening
 * it has two importantmethods:
 * 2. makeMove(): make a move
 * 3. tryToSwap(): try to swap the spot, if no valid move, end the game
 */

public class hotspot {
    private player player_charge;
    private player player_idle;
    private spotstatus status;

    //construct a hotspot, black player stay in spot
    hotspot(player p1, player p2){
        if(p1.getPiecetype() == piecetype.BLACK){
            player_charge = p1;
            player_idle = p2;
        }
        else if(p2.getPiecetype() == piecetype.WHITE){
            player_idle = p1; 
            player_charge = p2;
        }
        status = spotstatus.MOVE;
    }

    //initialize the spot so black is in spot
    void initialize(){
        if(player_charge.getPiecetype() == piecetype.WHITE)
            this.switchSpot();

    }

    //try to swap the spot, if no valid move, end the game
    void tryToSwap(board board){
        board.refreshValid(player_charge.getPiecetype());
        if(board.noValid() && status == spotstatus.MOVE){
            this.switchSpot();
            board.refreshValid(player_charge.getPiecetype());
            if(board.noValid())
                status = spotstatus.END;
        }
    }

    //make a move
    void makeMove(board board, int[] move){
            if(board.isValid(move)){
                board.add(player_charge,move);
                board.flip(move);
                this.switchSpot();
                status = spotstatus.MOVE;
            }
            else{
                status = spotstatus.INVALID;
            }
        }
    
    //switch player_charge and player_idle
    private void switchSpot(){
            player tmp = player_charge;
            player_charge = player_idle;
            player_idle = tmp;
    }

    //get the player in spot
    public player getChargePlayer(){
        return player_charge;
    }

    //get the status of the spot
    public spotstatus getSpotStatus(){
        return status;
    }
}