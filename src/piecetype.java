//stands for three status of the piece
public enum piecetype {
    WHITE,BLACK,EMPTY,VALID;

    public piecetype opp(){  //generated by llm
        return switch(this) {
            case WHITE -> BLACK;
            case BLACK -> WHITE;
            case EMPTY, VALID -> EMPTY;
        };
    }
}
