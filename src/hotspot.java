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

    void afterMove(board board){
        board.refreshValid(player_charge.getPiecetype());
        if(board.noValid() && status == spotstatus.MOVE){
            this.switchSpot();
            board.refreshValid(player_charge.getPiecetype());
            if(board.noValid())
                status = spotstatus.END;
        }
    }

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

    public player getChargePlayer(){
        return player_charge;
    }

    public spotstatus getSpotStatus(){
        return status;
    }

}






