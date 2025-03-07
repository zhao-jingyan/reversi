package reversi;

import reversi.core.game.game;
import reversi.core.game.spotstatus;
import reversi.ui.console.input;
import reversi.ui.console.output;

/*
 * main class, conduct the game
 */

public class reversi {
    public static void main(String[] args){
        //conductors, creating essential objects
        game[] games = new game[4]; //game[0] is the going game, 1 2 3 are stored games
        for(int i = 1; i < 4; i++)
            games[i] = new game("Bill_Black","Walt_White");
        games[0] = games[1];

        //input and output
        input terminal = new input();
        output screen = new output();
        
        //start, print for he first time
        screen.print(games);

        //game loop
        while(games[0].getSpot().getSpotStatus() != spotstatus.END ||
              games[1].getSpot().getSpotStatus() != spotstatus.END ||
              games[2].getSpot().getSpotStatus() != spotstatus.END){

            int[] move = terminal.getInput();            //get input

            if(move[0] == -1)
                games[0] = games[move[1]];              //switch game
            else
                games[0].makeMove(move);                //make move

            screen.print(games);                        //print
        }

        //ending, print again
        screen.print(games);
    }
}