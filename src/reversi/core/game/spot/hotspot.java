/*
 * hotspot is a class that contains two players, one is in spot, the other is not
 * inspired by civilization VI, having a mode called "hot spot" mode, in which player take turns to take action
 * so player contains only information, while hotspot is the one that takes action
 * it has a status to indicate what's happening
 * it has two importantmethods:
 * 2. makeMove(): make a move
 * 3. handleNoValidMoves(): check if the current player has no valid moves, if so, switch to the other player
 */

package reversi.core.game.spot;

import reversi.core.game.board.Board;
import reversi.core.game.board.PieceStatus;
import reversi.info.inputinfo.types.MoveInformation;

public class HotSpot {
    private final Player p1;
    private final Player p2;
    private Player chargePlayer;
    private SpotStatus status;

    //construct a hotspot, black player stay in spot
    public HotSpot(String p1Name, String p2Name){
        this.p1 = new Player(p1Name, PieceStatus.BLACK);
        this.p2 = new Player(p2Name, PieceStatus.WHITE);
        chargePlayer = this.p1;
        status = SpotStatus.MOVE;
    }

    //initialize the spot so black is in spot
    public void initialize(){
        chargePlayer = p1;
        status = SpotStatus.MOVE;
    }

    //make a move
    public void makeMove(Board board, MoveInformation info){
        if(board.isValid(info.getInfo())){
            board.add(chargePlayer.getPiecetype(),info.getInfo());
            board.flip(info.getInfo());
            chargePlayer = (chargePlayer == p1) ? p2 : p1;
            board.refreshValid(chargePlayer.getPiecetype());
            status = SpotStatus.MOVE;
            handleNoValidMoves(board);
        }
        else {
            status = SpotStatus.INVALID;
        }
    }

    public void pass(Board board){
        //NOVALID means opposite player has a move, which is checked by handleNoValidMoves, or the game would end
        chargePlayer = (chargePlayer == p1) ? p2 : p1;
        board.refreshValid(chargePlayer.getPiecetype());
        status = SpotStatus.MOVE;
        handleNoValidMoves(board);
    }

    //check if the current player has no valid moves, if so, switch to the other player
    private void handleNoValidMoves(Board board){
        if(board.noValid()){
            //switch pos and check the other player
            chargePlayer = (chargePlayer == p1) ? p2 : p1;
            board.refreshValid(chargePlayer.getPiecetype());
            if(board.noValid())
                status = SpotStatus.END;
            //switch back
            else {
                chargePlayer = (chargePlayer == p1) ? p2 : p1;
                board.refreshValid(chargePlayer.getPiecetype());
                status = SpotStatus.NOVALID;
                //waiting for pass
            }
        }
    }

    //get the player in spot
    public Player getChargePlayer(){
        return chargePlayer;
    }

    //get the status of the spot
    public SpotStatus getSpotStatus(){
        return status;
    }

    //get the player p1
    public Player getP1(){
        return p1;
    }

    //get the player p2
    public Player getP2(){
        return p2;
    }

}