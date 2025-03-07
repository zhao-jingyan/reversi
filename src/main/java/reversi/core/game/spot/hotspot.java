/*
 * hotspot is a class that contains two players, one is in spot, the other is not
 * inspired by civilization VI, having a mode called "hot spot" mode, in which player take turns to take action
 * so player contains only information, while hotspot is the one that takes action
 * it has a status to indicate what's happening
 * it has two importantmethods:
 * 2. makeMove(): make a move
 * 3. tryToSwap(): try to swap the spot, if no valid move, end the game
 */

package reversi.core.game.spot;

import reversi.core.game.board.board;
import reversi.core.game.board.piecestatus;
import reversi.core.game.spotstatus;

public class hotspot {
    private final player p1;
    private final player p2;
    private player player_charge;
    private player player_idle;
    private spotstatus status;

    //construct a hotspot, black player stay in spot
    public hotspot(String p1Name, String p2Name){
        this.p1 = new player(p1Name, piecestatus.BLACK);
        this.p2 = new player(p2Name, piecestatus.WHITE);
        player_charge = this.p1;
        player_idle = this.p2;
        status = spotstatus.MOVE;
    }

    //initialize the spot so black is in spot
    public void initialize(){
        if(player_charge.getPiecetype() == piecestatus.WHITE)
            this.switchSpot();

    }

    //try to swap the spot, if no valid move, end the game
    public void tryToSwap(board board){
        board.refreshValid(player_charge.getPiecetype());
        if(board.noValid() && status == spotstatus.MOVE){
            this.switchSpot();
            board.refreshValid(player_charge.getPiecetype());
            if(board.noValid())
                status = spotstatus.END;
        }
    }

    //make a move
    public void makeMove(board board, int[] move){
            if(board.isValid(move)){
                board.add(player_charge.getPiecetype(),move);
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

    //get the player p1
    public player getP1(){
        return p1;
    }

    //get the player p2
    public player getP2(){
        return p2;
    }

}