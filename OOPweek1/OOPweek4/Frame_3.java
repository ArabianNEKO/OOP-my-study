package OOPweek4;

import javax.swing.*;

import java.awt.Color;
import java.util.Random;
public class Frame_3 {
    public static void main(String[] args) {
         JFrame frame = new JFrame("Multiple Buttons");
        
        frame.setLocation(500,500);
        frame.setSize(600, 600);

        //Layout 
        frame.setLayout(null);

        Random random = new Random();
        //Buttons
        for (int i = 0; i < 50; i++) {
            JButton button = new JButton("" + (i + 1));

            //ดึงข้อมูล ตวามสูงความกว้างมาลบ ไม่ให้เกินขอบ
            int x = random.nextInt(frame.getWidth() - 100); 
            int y = random.nextInt(frame.getHeight() - 100); 
            button.setBounds(x, y, 50, 30); 

            // Set random color
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            //ผสมสี
            button.setBackground(new Color(r, g, b));

            
            frame.add(button);
        }

        
        frame.setVisible(true);
    }
}
