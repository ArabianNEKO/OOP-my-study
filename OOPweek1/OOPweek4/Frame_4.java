package OOPweek4;
import java.awt.*;
import javax.swing.*;

public class Frame_4 {
    public static void main(String[] args) {
        Myframe frame = new Myframe();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class Myframe extends JFrame{
Myframe()
{
    setTitle("Multiple Button");
    setSize(300, 300);
    setLocation(500, 500);
    setLayout(new BorderLayout());

    JTextArea textArea = new JTextArea("",100,100);
    add(textArea,BorderLayout.CENTER);
    

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());

    Button btnOpen = new Button("Open File ");
    Button btnClose = new Button("Close File ");
    Button btnClear = new Button("Clear Screen ");
    
    panel.add(btnOpen);
    panel.add(btnClose);
    panel.add(btnClear);

    JPanel panelwest = new JPanel();
    JPanel paneleast = new JPanel();

    add(panelwest,BorderLayout.WEST);
    add(paneleast,BorderLayout.EAST);
    add(panel,BorderLayout.SOUTH);
}
}
