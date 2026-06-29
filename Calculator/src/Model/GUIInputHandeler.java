package Model;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIInputHandeler {

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



    private static boolean check(String toCheck, String against){
        return toCheck.equalsIgnoreCase(against);
    }

    private static boolean check(char toCheck, char against){
        return toCheck == against;
    }

    private static boolean checkNum(char kc){
        boolean answer = ((kc >= 48 && kc <= 57) || (kc == 46) || (kc == 40) || (kc == 41));
        return answer;
    }
    // public static void numberButtonListeners(JButton b, JTextField f){
    //     b.addMouseListener(new MouseAdapter(){
    //         @Override
    //         public void mouseReleased(MouseEvent e){
    //             String expression = f.getText();
    //             expression += b.getText();
    //             f.setText(expression);
    //         }
    //     });

    //     f.addKeyListener(new KeyAdapter() {
    //         @Override
    //         public void keyReleased(KeyEvent k){
    //             if(k.getKeyChar() == b.getText().charAt(0)){
    //                 String expression = f.getText();
    //                 expression += b.getText();
    //                 f.setText(expression);
    //             }
    //         }
    //     });
    // }

    // public static void operatorButtonListeners(JButton b, JTextField f){
    //     b.addMouseListener(new MouseAdapter(){
    //         @Override
    //         public void mouseReleased(MouseEvent e){
    //             String expression = f.getText();
    //             expression += " " + b.getText() + " ";
    //             f.setText(expression);
    //         }
    //     });

    //     f.addKeyListener(new KeyAdapter() {
    //         @Override
    //         public void keyReleased(KeyEvent k){
    //             if(k.getKeyChar() == b.getText().charAt(0)){
    //                 String expression = f.getText();
    //                 expression += " " + b.getText() + " ";
    //                 f.setText(expression);
    //             }
    //         }
    //     });
    // }

    // public static void clearButtonListener(JButton b, JTextField f){
    //     b.addMouseListener(new MouseAdapter(){
    //         @Override
    //         public void mouseReleased(MouseEvent e){
    //             f.setText("");
                
    //         }
    //     });

    //     f.addKeyListener(new KeyAdapter() {
    //         @Override
    //         public void keyReleased(KeyEvent k){
    //             if(k.getKeyChar() == 'c' || k.getKeyChar() == 'C'){
    //                 f.setText("");
    //             }
    //         }
    //     });
    // }

    // public static void answerButtonListener(JButton b, JTextField f){
    //     b.addMouseListener(new MouseAdapter(){
    //         @Override
    //         public void mouseReleased(MouseEvent e){
    //             String expression = f.getText();
    //             double answer = InputHandeler.handel(expression);
    //             f.setText(Double.toString(answer));
    //         }
    //     });

    //     f.addKeyListener(new KeyAdapter() {
    //         @Override
    //         public void keyReleased(KeyEvent k){
    //             if(k.getKeyChar() == '=' || k.getKeyCode() == KeyEvent.VK_ENTER){
    //                 String expression = f.getText();
    //                 double answer = InputHandeler.handel(expression);
    //                 f.setText(Double.toString(answer));
    //             }
    //         }
    //     });
    // }
    
}
