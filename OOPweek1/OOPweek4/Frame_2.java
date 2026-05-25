package OOPweek4;
import javax.swing.*;
import java.awt.GridLayout;
public class Frame_2 {
    public static void main(String[] args) {
        //Frame
        JFrame frame = new JFrame("Multiple Buttons");
        
        frame.setLocation(300,300);
        frame.setSize(500, 500);
        
        
        //Layout
        frame.setLayout(new GridLayout(8,5));

        //Buttons 
        for (int i = 0; i < 20; i++) {
            JButton button = new JButton("Button " + (i + 1));
            JTextArea textArea = new JTextArea();

            
            frame.add(button);
            frame.add(textArea);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
