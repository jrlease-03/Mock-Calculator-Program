package Model;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIInputHandeler {

    /*
        @param b - a JButton object
        @param f - the JTextField object used for the text display of the calculator

        This method adds a new mouse listener to the passed button object
        the listener will perform certain actions based on the button being pressed

        if it is the clear button, the JTextField will be cleared
        if it is the enter button, the InputHandeler will be given the string from the TextField to be calculated
        if it is an operator button (+,-,*,/,^), that operator will be added to the string in the TextField
        if it is the previous answer button, the last calculated answer will be added to the string surrounded by spaces (0 if its the first calculation)
        if it is any other button (0-9,".","(",")"), that buttons value will be added to the string
    */
    public static void myButtonListener(JButton b, JTextField f){
        b.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e){
                String s = f.getText();
                String bt = b.getText();

            
                if(check(bt, "clear")){
                    s = "";
                }else if(check(bt, "=")){
                    s = Double.toString(InputHandeler.handel(s));
                }else if(check(bt, "+") || check(bt, "-") || check(bt, "*") || check(bt, "/") || check(bt, "^")){
                    s += " " + bt + " ";
                }else if(check(bt, "ans")){
                    s += InputHandeler.getPrev();
                }else{
                    s += bt;
                }

                f.setText(s);
                }
            
        });
    }

    /*
        @param f - The JTextField to add the listener to

        This method is used to add specific key listeners to the JTextField, to allow the keyboard to interact
        with the calculator, but it is designed to prevent letters from being typed

        if the '=' or enter key is typed, the string in the TextField will be sent to the InputHandeler to be calculated
        if the 'c' key is entered (can be capital), the TextField will become blank
        if any operator key is pressed (+,-,*,/,^), then that operator will be added to the TextField surrounded by spaces
        if the 'a' key is entered (can be capital), the previous calculated answer will be added to the TextField (0 if first calculation)
        if any other key is entered (0-9, ".", "(", ")"), it will be added to the TextField
    */
    public static void myKeyListener(JTextField f){
        f.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                String s = f.getText();
                char kc = e.getKeyChar();
            
                if(check(kc, 'c') || check(kc, 'C')){
                    s = "";
                }else if(e.getKeyCode() == KeyEvent.VK_ENTER || check(kc, '=')){
                    s = Double.toString(InputHandeler.handel(s));
                }else if(check(kc, '+') || check(kc, '-') || check(kc, '*') || check(kc, '/') || check(kc, '^')){
                    s += " " + kc + " ";
                }else if(check(kc, 'a')|| check(kc, 'A')){
                    s += InputHandeler.getPrev();
                }else if (checkNum(kc)){
                    s += kc;
                }

                f.setText(s);
            }
        });
    }


    /*
        @param toCheck - The string that is being checked to see if it equals the "against" parameter
        @param against - The desired string for the "toCheck" parameter to be

        @return boolean value true or false depending on if the strings match

        This private method is designed to be a simple check for equality of the two string parameters
    */
    private static boolean check(String toCheck, String against){
        return toCheck.equalsIgnoreCase(against);
    }

    /*
        @param toCheck - The character that is being checked to see if it equals the "against" parameter
        @param against - The desired character for the "toCheck" parameter to be

        @return boolean value true or false depending on if the characters match

        This private method is designed to be a simple check for equality of the two character parameters
    */
    private static boolean check(char toCheck, char against){
        return toCheck == against;
    }

    /*
        @param kc - the character to be checked for equality

        @return a boolean value depending on if the character matches the specific characters

        This private method is designed to be a check for the passed character parameter,
        it checks if it is a number "0-9", a period ".", or a paranthese"(" ")" 
    */
    private static boolean checkNum(char kc){
        boolean answer = ((kc >= 48 && kc <= 57) || (kc == 46) || (kc == 40) || (kc == 41));
        return answer;
    }
    
    
}
