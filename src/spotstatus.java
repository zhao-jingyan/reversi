/*
 * spot status is readed so the game can now what's happening
 * INVALID indicates last move is invalid, a new move needed
 * MOVE means everything is fine
 * END means no more moves
 */

public enum spotstatus {
    INVALID,MOVE,END;
}
