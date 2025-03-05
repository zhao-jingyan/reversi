/*
 * input is a class that contains a string and a scanner
 * it reads the input from the terminal and formats it into a coordinate
 * invalid input will return -1,-1
 */
import java.util.Scanner;

public class input {
    String inputString;
    Scanner scanner;

    //constructor
    input(){
        inputString = "";
        scanner = new Scanner(System.in);
    }

    //get the input from the terminal
    public int[] getInput(){
        while (!scanner.hasNextLine()){} //wait for input
        inputString = scanner.nextLine();
        int [] coordinates = new int[2];
        if (String_is_valid(inputString))
            coordinates = formatCoordinate(inputString);
        else{
            coordinates[0] = -1;
            coordinates[1] = -1;
        }
        return coordinates;
    }
    
    //check if the input is valid
    private static boolean String_is_valid(String testee){
        //a~h A~H 1~8 are valid inputs
        boolean valid = testee.length() == 2 &&
               (testee.charAt(1) <= '8' && testee.charAt(1) >= '1') &&
              ((testee.charAt(0) <= 'H' && testee.charAt(0) >= 'A') ||
               (testee.charAt(0) <= 'h' && testee.charAt(0) >= 'a'));
        return valid;
    }

    //format the input into a coordinate
    private static int[] formatCoordinate(String input){
        int[] ans = new int[2]; //first is row num; second is col num
        ans[0] = input.charAt(1) - '1';
        if(input.charAt(0) <= 'H' && input.charAt(0) >= 'A')
            ans[1] = input.charAt(0) - 'A';
        else if(input.charAt(0) <= 'h' && input.charAt(0) >= 'a')
            ans[1] = input.charAt(0) - 'a';
        return ans;
    }
}