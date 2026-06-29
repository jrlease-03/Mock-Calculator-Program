package View;
import java.util.TreeMap;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Model.GUIInputHandeler;

public class GUIView {
    JFrame frame;
    TreeMap<Character ,JButton> buttons;
    char[] buttonNames = {'C', '=', '^', '+', '-', '*', '/', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', '(', ')', 'A'};
    JTextField window;

    public GUIView(){
        buttons = new TreeMap<Character, JButton>();
        frame = new JFrame("Calculator");
        frame.setSize(400,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpWindow();
        setUpButtons();

        frame.setVisible(true);
    }

    private void setUpWindow(){
        window = new JTextField();
        Dimension d = new Dimension(frame.getWidth() - 50, (frame.getHeight() / 6) - 10);
        Point p = new Point(20,25);
        Rectangle r = new Rectangle(p, d);
        window.setBounds(r);
        
        Font font = window.getFont();
        Font newFont = new Font(font.getName(), font.getStyle(), 21);
        window.setFont(newFont);
        window.setEditable(false);

        GUIInputHandeler.myKeyListener(window);
        frame.add(window);
    }


    private void setUpButtons(){
        
        buttons.put(buttonNames[0], new JButton("Clear"));
        buttons.put(buttonNames[20], new JButton("Ans"));
        for(int i = 1 ; i < buttonNames.length - 1; i++){
            buttons.put(buttonNames[i], new JButton(Character.toString(buttonNames[i])));
        }

        for(int i = 0; i < buttonNames.length; i++){
            GUIInputHandeler.myButtonListener(buttons.get(buttonNames[i]), window);
        }
        
        Dimension d = new Dimension(frameFourthWidth(1) - 10, frameEighthHeight(1) - 10);
        Point[] p = {
            new Point(frameFourthWidth(3), frameEighthHeight(2) - 20),
            new Point(frameFourthWidth(3), frameEighthHeight(7) - 20),
            new Point(frameFourthWidth(2), frameEighthHeight(2) - 20),
            new Point(frameFourthWidth(3), frameEighthHeight(3) - 20),
            new Point(frameFourthWidth(3), frameEighthHeight(4) - 20),
            new Point(frameFourthWidth(3), frameEighthHeight(5) - 20),
            new Point(frameFourthWidth(3), frameEighthHeight(6) - 20),
            new Point(0, frameEighthHeight(3) - 20),
            new Point(frameFourthWidth(1), frameEighthHeight(3) - 20),
            new Point(frameFourthWidth(2), frameEighthHeight(3) - 20),
            new Point(0, frameEighthHeight(4) - 20),
            new Point(frameFourthWidth(1), frameEighthHeight(4) - 20),
            new Point(frameFourthWidth(2), frameEighthHeight(4) - 20),
            new Point(0, frameEighthHeight(5) - 20),
            new Point(frameFourthWidth(1), frameEighthHeight(5) - 20),
            new Point(frameFourthWidth(2), frameEighthHeight(5) - 20),
            new Point(frameFourthWidth(1), frameEighthHeight(6) - 20),
            new Point(frameFourthWidth(2), frameEighthHeight(6) - 20),
            new Point(0, frameEighthHeight(2) - 20),
            new Point(frameFourthWidth(1), frameEighthHeight(2) - 20),
            new Point(0, frameEighthHeight(6) - 20)
        };
        Rectangle r;

        for(int i = 0; i < buttonNames.length; i++){
            r = new Rectangle(p[i], d);
            buttons.get(buttonNames[i]).setBounds(r);
            frame.add(buttons.get(buttonNames[i]));
        }
    }

    private int frameFourthWidth(int mul){
        return (frame.getWidth() / 4) * mul;
    }

    private int frameEighthHeight(int mul){
        return (frame.getHeight() / 8) * mul;
    }
}
