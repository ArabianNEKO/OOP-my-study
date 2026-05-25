package OOPweek5;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_2 {
    public static void main(String[] args) {
        MyKoon frame = new MyKoon();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class MyKoon extends JFrame {
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton button = new JButton("OK");

    MyKoon() {
        setTitle("Multiplication table");
        setSize(500, 500);
        setLocation(500, 500);
        setLayout(new BorderLayout());

        add(textField, BorderLayout.NORTH);

        textArea.setPreferredSize(new Dimension(200, 200));
        add(textArea, BorderLayout.CENTER);

        add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click(e);
            }
        });
    }

    void click(ActionEvent e) {
        String text = textField.getText();
        System.out.println(text);
        try {
            int num = Integer.parseInt(text);
            textArea.setText("");
            for (int i = 1; i <= 12; i++) {
                textArea.append(num + " x " + i + " = " + num * i + "\n");
            }
        } catch (Exception ex) {
            textArea.setText("Input Number Error!!");
        }
    }
}


