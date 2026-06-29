/*
    Name of program: Calculator
    Name of file: Main.java
    Author: Joshua Lease

    Description:
    This is the Main function for my calculator program, it is meant to be the start of the program

    this file is very barren at the moment, but in the future I plan to add a GUI which will be initiated at this same
    point that the CLI would (Which is right here)

    the only call that this main method does right now jumps to the CLIView.java file to begin the program in CLI mode

*/

public class Main{
    public static void main(String[] args){
        //creates a new instance of a CLI calculator
        if(args.length == 0 || args[0].equalsIgnoreCase("cli")){
            new View.CLIView();
        }
        else if(args[0].equalsIgnoreCase("gui")){
            new View.GUIView();
        }else{
            System.out.println("you messed up starting this calculator, try again XD");
        }
        
    }
}