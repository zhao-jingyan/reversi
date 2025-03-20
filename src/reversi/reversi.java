package reversi;

import java.util.ArrayList;
import reversi.core.Game;
import reversi.core.GameMode;
import reversi.core.spot.SpotStatus;
import reversi.ui.console.Input;
import reversi.ui.console.Output;
import reversi.ui.information.InfoType;
import reversi.ui.information.Information;

/*
 * main class, conduct the game
 */

public class reversi {
    public static void main(String[] args){

        //conductors, creating essential objects
        ArrayList<Game> games = new ArrayList<>(); //game[0] is the going game, 1 2 3 are stored games
        initialize(games);

        //input and output
        Input terminal = new Input();
        Output screen = new Output();
        
        //start, print for he first time
        screen.print(games, null);

        //game loop
        while(!isEnd(games)){
            Information info = terminal.getInput();            //get input
            
            if(info.getInfoType() == InfoType.QUIT){
                break;
            }
            else if(info.getInfoType() == InfoType.BOARDNUM){
                games.set(0, games.get(info.getInfo()));              //switch game
            }
            else if(info.getInfoType() == InfoType.NEWGAME){
                games.add(new Game("Bill_Black","Walt_White",info.getGameType()));
            }
            else{
                games.get(0).makeMove(info);                //make move
            }

            screen.print(games, null);                        //print
        }

        //ending, print again
        screen.print(games, null);
    }

    private static void initialize(ArrayList<Game> games){
        games.add(new Game("","",GameMode.PEACE));
        games.add(new Game("Bill_Black","Walt_White",GameMode.PEACE));  
        games.add(new Game("Bill_Black","Walt_White",GameMode.REVERSI));
    }

    private static boolean isEnd(ArrayList<Game> games){
        for(Game game : games){
            if(game.getSpot().getSpotStatus() != SpotStatus.END)
                return false;
        }
        return true;
    }
}