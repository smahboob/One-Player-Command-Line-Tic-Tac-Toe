import java.util.Scanner;
import java.util.Random; 
public class Game{

    public static String [] board = new String []{"1","2","3","4","5","6","7","8","9"};
    public static Scanner userInput;
    public static String playerSymbol; 
    public static String computerSymbol; 
    public static boolean endGame = false;
    public static boolean playerTurn;
    public static String cross = "XXX";
    public static String circle = "OOO";
    public static String choiceofSymbol = "";


    public static void choosePlayerSymbol(){
    
            while (! choiceofSymbol.equalsIgnoreCase("X") && ! choiceofSymbol.equalsIgnoreCase("O"))
            {
                System.out.println("Chose symbol 'X' for first turn or 'O' for second turn:");
                choiceofSymbol = userInput.next();

                if (! choiceofSymbol.equalsIgnoreCase("X") && ! choiceofSymbol.equalsIgnoreCase("O"))
                {
                    System.out.println("Invalid userInput , please try again");
                }
    
            }
                    if (choiceofSymbol.equalsIgnoreCase("X"))
                    {   
                        System.out.println("User takes the first turn");
                        playerTurn = true; 
                    }
                    else
                    {
                        System.out.println("Computer takes the first turn");
                    }

                playerSymbol = choiceofSymbol.toUpperCase();
                computerSymbol = choiceofSymbol.equalsIgnoreCase("X")? "O": "X";
                    
        }

    public static void PlayerMove()
    {
        showGrid();
        boolean validInput = false;

        while(!validInput)
        {    
            System.out.println("Pick a number between (1-9) to place your symbol");
            String enteredString = userInput.next();
            int userChosenNumber = 0;

            try
            {
                userChosenNumber = Integer.parseInt(enteredString);
            }
            catch(NumberFormatException nfe)
            {    
                System.out.println("INVALID INPUT, ENTER A NUMBER ONLY");
                continue;
            }

            if(userChosenNumber < 1 || userChosenNumber > 9){
                System.out.println("INVALID INPUT, ENTER A NUMBER BETWEEN 1 - 9 ONLY");
                continue;
            }

            if(board[userChosenNumber-1].equalsIgnoreCase("X") || board[userChosenNumber-1].equalsIgnoreCase("O")){
                System.out.println("THE LOCATION IS ALREADY CHOSEN, MAKE A NEW SELECTION");
                continue;
            }


            board[userChosenNumber-1] = playerSymbol;
            validInput = true;

        }

        playerTurn = false;
        
    }

    public static void ComputerMove(){

        boolean freeLocation = false;

        while(!freeLocation){
            Random move = new Random(); 
            int computerLocation = move.nextInt(9); 

            if(!board[computerLocation].equalsIgnoreCase("X") && !board[computerLocation].equalsIgnoreCase("O")){
              board[computerLocation] = playerSymbol.equalsIgnoreCase("X")? "O": "X";
              freeLocation = true;
            }

        }
        playerTurn = true;

    }


    public static void showGrid(){
        System.out.println("-------");
        System.out.println("|" + board[0] + "|" + board[1] + "|" + board[2] + "|");
        System.out.println("-------");
        System.out.println("|" + board[3] + "|" + board[4] + "|" + board[5] + "|");
        System.out.println("-------");
        System.out.println("|" + board[6] + "|" + board[7] + "|" + board[8] + "|");
        System.out.println("-------");

    }


public static void checkWinner(){

    if ((board[0]+ board[1]+ board[2]).equals(cross) || (board[0]+ board[4] +board[8]).equals(cross)
    || (board[2]+ board[4]+ board[6]).equals(cross) || (board[3]+ board[4] +board[5]).equals(cross)
    || (board[6]+ board[7]+ board[8]).equals(cross) || (board[1]+ board[4] +board[7]).equals(cross)
    || (board[0]+ board[3]+ board[6]).equals(cross) || (board[2]+ board[5] +board[8]).equals(cross)){
        showGrid();
        System.out.println("Congragulations you Won!");
        endGame = true;
}

    if ((board[0]+ board[1]+ board[2]).equals(circle) || (board[0]+ board[4] +board[8]).equals(circle)
        || (board[2]+ board[4]+ board[6]).equals(circle) || (board[3]+ board[4] +board[5]).equals(circle)
        || (board[6]+ board[7]+ board[8]).equals(circle) || (board[1]+ board[4] +board[7]).equals(circle)
        || (board[0]+ board[3]+ board[6]).equals(circle) || (board[2]+ board[5] +board[8]).equals(circle)){
            showGrid();
            System.out.println("Sorry You Lost!");
            endGame = true;
    }

}

    public static void playGame(){
        while(endGame == false)
        {
            if(playerTurn){
                PlayerMove();
            }
            else{
                ComputerMove();
            }
            checkWinner();
        }
    }

    
    public static void main(String[] args){

        playerTurn = false;

        System.out.println("------------------------------");
        System.out.println("Welcome to the TicTacToe Game");
        System.out.println("------------------------------");

        userInput = new Scanner(System.in);

        choosePlayerSymbol();
        playGame();

        userInput.close();
        
    }

}