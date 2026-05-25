package tiwOOP;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class menu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new manuframe().setVisible(true);
        });
    }
}

class manuframe extends JFrame implements ActionListener
{
    JLabel title = new JLabel("PM.2.5");
    
    JButton start = new JButton("Start");
    JButton about = new JButton("About");
    JButton exit = new JButton("Exit");

    Font font = new Font("Arail", Font.BOLD, 50);
    manuframe()
    {
        setTitle("PM.2.5");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title.setBounds(400, 180, 180, 70);
        title.setFont(font);

        start. setBounds(400, 280, 180, 70);
        start.setBackground(Color.WHITE);
        start.addActionListener(this);
        start.setFont(font);

        about.setBounds(400, 380, 180, 70);
        about.setBackground(Color.WHITE);
        about.addActionListener(this);
        about.setFont(font);

        exit.setBounds(400, 480, 180, 70);
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        exit.setFont(font);

        add(title);
        add(start);
        add(about);
        add(exit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == start)
       {
         myframe menu = new myframe();
         menu.setLocationRelativeTo(null);
         menu.setVisible(true);
       }
       else if (e.getSource() == about)
       {
        memberframe memframe = new memberframe();
        memframe.setLocationRelativeTo(null);
        memframe.setVisible(true);
       }
       else if (e.getSource() == exit)
       {
        System.exit(0);
       }
    }
}
