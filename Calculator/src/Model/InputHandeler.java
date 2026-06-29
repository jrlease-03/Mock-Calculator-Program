/*
    Name of file: InputHandeler.java
    Author: Joshua Lease

    Description:
    This is the main brain of the calculator program, it is what parses through the input string to pull out operators and numbers
    to calculate answers

    in order to achive this goal the program follows a "simple" flow of:
        getting the input (being called by another file in the program)
        breaking down the input into usable data (breakdown() method)
        calculating the answer of the usable data (compute() method)

    this flow utilizes two ArrayLists to keep track of the data: the numbers array and the operators array
    of these array "types", there is two for the main program to use, and two that breakdown() will utilize when it encounters a parenthese

    any time a parenthese is encountered, it is treated like it's own input until it is fully calculated, the result will replace the parenthese in the
    original input (or if layered parentheses, then will be in that calculation) to continue calculation
*/



package Model;
import java.util.ArrayList;
public class InputHandeler {
    //The two arrays that handel the numbers and operators of the overall input
    private static ArrayList<Double> nums;
    private static ArrayList<String> ops;

    private static double prevAnswer = 0;

    //is called from the CLIView file to compute the input
    public static double handel(String input){
        //initialization of the lists
        nums = new ArrayList<Double>();
        ops = new ArrayList<String>();

        //reads the string from the input and places numbers in the "nums" array and operators in the "ops" array
        breakdown(input, nums, ops);

        //does math based on the operators in the "ops" array, then the resulting number is returned to the CLIView file to be printed
        prevAnswer = compute(nums, ops);
        return prevAnswer;
    }


    /*
        @param input - the input string given to the method call
        @param num - the number array given to the method call
        @param op - the operator array given to the method call

        The breakdown() method is used to (quite literally) break down the input given into numbers and operators in order to be used for math
    */
    private static void breakdown(String input, ArrayList<Double> num, ArrayList<String> op){
        //ArrayLists that will be used if a parenthese is found
        ArrayList<Double> parenNums;
        ArrayList<String> parenOps;


        //used to store the seperated input at the first operator found in the given input
        String[] s = new String[2];
        //break point for loop, used since the loop is only to find the first operator
        loop:
        for(int i = 0; i < input.length(); i++){
            //determins which operator is at this position, if it is at this position
            switch(input.charAt(i)){
                case '+':
                    //splits input, found in the other cases
                    s = input.split("[+]", 2);

                    //if an expression like " * 2" is given, will provide the answer from the previous calculation
                    s[0] = (s[0].isBlank()) ? Double.toString(prevAnswer) : s[0];
                    //method call to provide the left handed number and the operator to the "num" and "op" arrays
                    //found in the other cases with different operators provided
                    addToArray(num, op, Double.parseDouble(s[0]), "+");
                    //breaks the loop since the first operator was found, found in the other cases
                    break loop;
                case '-':
                    s = input.split("[-]", 2);
                    s[0] = (s[0].isBlank()) ? Double.toString(prevAnswer) : s[0];
                    addToArray(num, op, Double.parseDouble(s[0]), "-");
                    break loop;
                case '*':
                    s = input.split("[*]", 2);
                    s[0] = (s[0].isBlank()) ? Double.toString(prevAnswer) : s[0];
                    addToArray(num, op, Double.parseDouble(s[0]), "*");
                    break loop;
                case '/':
                    s = input.split("[/]", 2);
                    s[0] = (s[0].isBlank()) ? Double.toString(prevAnswer) : s[0];
                    addToArray(num, op, Double.parseDouble(s[0]), "/");
                    break loop;
                case '(':
                    //initialization of the ArrayLists for this parenthese
                    parenNums = new ArrayList<Double>();
                    parenOps = new ArrayList<String>();
                    //finds the end of this parenthese, accounts for imbeded parenthese operations
                    int end = endParen(input, i + 1);

                    //recursive call to breakdown only what's in the parentheses, and is given this parentheses ArrayLists
                    breakdown(input.substring(i + 1,end), parenNums, parenOps);
                    //computes the resulting numbers from this parenthese breakdown
                    double an = compute(parenNums, parenOps);

                    //acts as a split by placing the result of the parenthese operation with the rest of the inital input after the end parenthese
                    //into the part of the array that gets checked to see if there is more input to breakdown
                    s[1] = an + input.substring(end + 1);
                    break loop;
                case '^':
                    s = input.split("\\^", 2);
                    s[0] = (s[0].isBlank()) ? Double.toString(prevAnswer) : s[0];
                    addToArray(num, op, Double.parseDouble(s[0]), "^");
                    break loop;

                case 'a': case 'A':
                    //places the previous claculated answer into the string in place of "A" to be calculated
                    s[1] = prevAnswer + input.substring(i + 1);
                    break loop;
            }
        }

        //checks to see if a split or parenthese operation happened
        if(s[1] != null){
            //since an operation occured, there is more data from the initial input that must be operated on
            //makes a recursive call on the ramaining input passing along the ArrayLists
            breakdown(s[1], num, op);
        }else{
            //since no other operations were detected in this part of the input, all thats left is to account for the remaining number (base case)
            //turns remaining number from input to a double and adds it to the given ArrayList
            num.add(Double.parseDouble(input));
        }
        
    }

    /*
        @param num - The ArrayList given to add the number (n) to
        @param op - The ArrayList given to add the operator (o) to
        @param n - The provided number to be added
        @param o - The provided operator to be added
        addToArray is essentially a helper function to make the breakdown() method work with parentheses

        takes the given Arraylists, and adds the coresponding data to that list
    */
    private static void addToArray(ArrayList<Double> num, ArrayList<String> op, double n,String o){
        num.add(n);
        op.add(o);
    }


    /*
        @param input - The full input string of the program
        @param start - The starting point to begin searching for the end parenthese

        endParen() is the method that locates the end of the parenthese operation
        the start parameter is given the spot in the input right after the parenthese that called this method,
        to avoid skipping it's own end

        if no end is found, -1 is returned, will hopefully be used for error handeling in the future
    */
    private static int endParen(String input, int start){
        //how many parenthese ends to skip till the correct one is found
        int skips = 0;
        for(int i = start; i < input.length(); i++){
            //checks to see which end of the parenthese it comes across
            if(input.charAt(i) == '('){
                //being in this block means an imbeded parenthese was found, so an end will need to be skipped
                skips++;
            }
            else if(input.charAt(i) == ')'){
                //being in this block means a group of parentheses is being closed
                if(skips != 0){
                    //being in this block means this is not the correct end for the parenthese
                    skips--;
                }else{
                    //being in this block means that there is no need to continue searching since the end has been found
                    return i;
                }
            }
        }
        //if not enough end parentheses are given, this will return
        return -1;
    }


    /*
        @param num - The provided number array to do computations with
        @param op - the provided operator array to do computations with

        the compute() method looks at the given arrays and begins doing that math required
    */
    private static double compute(ArrayList<Double> num, ArrayList<String> op){
        //used to mark the index in the num array that is currently being used
        int index = 0;
        //if only 1 number is provided, then no computations are required
        if(!op.isEmpty()){
            //loop to go through each operator to perform math
            for(int i = 0; i < op.size(); i++){
                switch (op.get(i)){
                    case "+":
                        //since math will be applied to index and index + 1 of the numbers array, the result is set in index + 1
                        //this keeps the loop flowing smoothly as the new result will be used in the next calculation
                        //sets index + 1 to the result of the MyMath call for (index, index + 1), found in the other cases
                        num.set(index+1, MyMath.add(num.get(index), num.get(index+1)));
                        //increases the index for the num array, found in the other cases
                        index++;
                        break;
                    case "-":
                        num.set(index+1, MyMath.sub(num.get(index), num.get(index+1)));
                        index++;
                        break;
                    case "*":
                        num.set(index+1, MyMath.mul(num.get(index), num.get(index+1)));
                        index++;
                        break;
                    case "/":
                        num.set(index+1, MyMath.div(num.get(index), num.get(index+1)));
                        index++;
                        break;
                    case"^":
                        num.set(index+1, MyMath.exp(num.get(index), num.get(index+1)));
                        index++;
                        break;
                }
            }
        }
        //returns (what should be) the last number in the num array, which should be the fully calculated expression
        return num.get(index);
    }

    public static double getPrev(){
        return prevAnswer;
    }
}
