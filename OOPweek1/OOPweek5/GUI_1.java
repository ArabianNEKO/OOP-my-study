package OOPweek5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI_1 {
    public static void main(String[] args) {
        Myframe frame = new Myframe();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class Myframe extends JFrame{

Myframe()
{
    setSize(300, 100);
    setLocation(500, 500);
    setLayout(new GridLayout(0,1));
    
    JTextField textField = new JTextField();
    add(textField);

    JButton button = new JButton("OK");
    add(button);


    button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button)
            {
              setTitle(textField.getText());
            }
        }
        
    });

}

}




