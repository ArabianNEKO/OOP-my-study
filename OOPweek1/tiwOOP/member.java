package tiwOOP;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class member {
    public static void main(String[] args) {
        memberframe memframe = new memberframe();
        memframe.setLocationRelativeTo(null);
        memframe.setVisible(true);
        memframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class memberframe extends JFrame
{
    JLabel infrometiom = new JLabel(" Sittichai Srinakares 66011212225 Sec.2 ");
    Font font = new Font("Arail", Font.BOLD, 20);
    memberframe()
    {
        setTitle("Member");
        setLocationRelativeTo(null);
        setSize(1000, 700);
        setLayout(new BorderLayout());
        infrometiom.setFont(font);
        add(infrometiom,BorderLayout.NORTH);
    }
}