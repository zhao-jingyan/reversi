public class player {
    String name;
    int priority; //1 stands for first and black, 2 stands for second and white;
    playerStatus status;

    //construct a player with given name and pieceType
    player(String given_name,int given_priority){
        name = given_name;
        priority = given_priority;
        status = playerStatus.IDLE;
    }

    public String showName(){
        return name;
    }

    public int showPriority(){
        return priority;
    }

    void restoreStatus(){
        if(status == playerStatus.INVALID)
            status = playerStatus.MOVE;
    }
}
