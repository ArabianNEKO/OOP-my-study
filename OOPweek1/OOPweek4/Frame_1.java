package OOPweek4;
import javax.swing.*;
import java.awt.GridLayout;
public class Frame_1 {
    public static void main(String[] args) {
        //Frame
        JFrame frame = new JFrame("Multiple Buttons");
        
        frame.setLocation(300,300);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Layout
        frame.setLayout(new GridLayout(5,4));

        //Buttons
        for (int i = 0; i < 20; i++) {
            JButton button = new JButton("Button " + (i + 1));

            
            frame.add(button);
        }

        
        frame.setVisible(true);
    }
}
