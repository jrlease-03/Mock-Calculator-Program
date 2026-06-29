/*
    Name of file: CLIView.java
    Author: Joshua Lease

    Description: This file is the start of the CLI calculator processes

    currently this file checks to see if the input is "help", "exit", or a "computable sequence"
    in this case a "computable sequence" is a string that contains only operators and numbers
    If there is a typo the program will through an exception and stop running


    I would like to split the functionality between the view and controller folders at some point


*/


package View;
import java.util.Scanner;

public class CLIView {
    //used to get input from users
    Scanner input;
    //will be true if the user has opted to end the program
    boolean end = false;

    /*
        This is the constructor of the CLIView
        the constructor initializes the scanner to read user input, then immediately calls initiate()
    */
    public CLIView(){
        input = new Scanner(System.in);
        initiate();
    }

    /*
        initiate() is the backbone of the CLIView

        this method calls question() to print the prompt to the user
        the program then waits for the user to give input

        responding with "help" will call the helpSheet() method to display how the program works
        responding with "exit" will change the end global var to True
        responding with anything else will trigger Model.InputHandeler.handel to attempt to compute the input
            this will run even if it is not a valid expression to be computed

        All of this, from calling to question till now will repeat until end is set to True
        once the loop is broken the program will automatically close
    */
    public void initiate(){
        do{
            //prints the question to the terminal
            question();
            //reads the input from the user
            String answer = input.nextLine();

            //checks to see if the input is "help", "exit", or an "expression"
            if(answer.contains("help")){
                //prints the helpsheet to the terminal
                helpSheet();
            }else if(answer.contains("exit")){
                //will end the program by ending the while loop
                end = true;
            }else{
                //will calculate the answer to the "expression" given then print it
                System.out.println("Answer is -->"+ Model.InputHandeler.handel(answer));
            }
        }while(!end);
    }

    /*
        this is the method the is called that will continually print the prompt till the user ends the program
    */
    public void question(){
        System.out.print("Welcome to calculator (type help for help the helpsheet) -->");
    }

    /*
        this is the method that prints the functionality of the calculator when the user requests it
    */
    public void helpSheet(){
        System.out.println("How to use a calculator 101:");
        System.out.println("in order to end the calculator program type \"exit\"");
        System.out.println("start typing numbers along with the operators {+,-,*,/} to calculate anything");
        System.out.println("Functionality:");
        System.out.println("\tbasic math");
        System.out.println("\tparenthese handeling");
        System.out.println("\tprevious answer handeling(can do \"+ 1\" or type \"a\" or \"A\" in an expression)\n");
        System.out.println("\texponents");
    }
}
