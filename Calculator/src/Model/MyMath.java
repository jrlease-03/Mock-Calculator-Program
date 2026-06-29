/*
    Name of file: MyMath.java
    Author: Joshua Lease

    Description:
    this is the file I included to handel doing mathematical operations
    it is very basic (and probably not even necessary), but its just to ensure that the correct math is being used at a given time
*/


package Model;
public class MyMath{
    /*
        @param numOne - The first number for the addition
        @param numTwo - The second number for the addition

        the add() method is very simple, it takes two numbers and adds them together, then returns the result
    */
    public static double add(double numOne, double numTwo){
        return numOne + numTwo;
    }

    /*
        @param numOne - The first number for the subtraction
        @param numTwo - The second number for the subtraction

        the sub() method is very simple, it takes two numbers and subtracts the second from the first, then returns the result
    */
    public static double sub(double numOne, double numTwo){
        return numOne - numTwo;
    }

    /*
        @param numOne - The first number for the multiplication
        @param numTwo - The second number for the multiplication

        the mul() method is very simple, it takes two numbers and multiplies them together, then returns the result
    */
    public static double mul(double numOne, double numTwo){
        return numOne * numTwo;
    }

    /*
        @param numOne - The first number for the division
        @param numTwo - The second number for the division

        the div() method is very simple, it takes two numbers and divides the second from the first, then returns the result
    */
    public static double div(double numOne, double numTwo){
        return numOne / numTwo;
    }

    public static double exp(double numOne, double numTwo){
        double answer = numOne;
        for(int i = 1; i < numTwo; i++){
            answer *= numOne;
        }
        return answer;
    }
}