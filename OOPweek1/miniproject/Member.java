package miniproject;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class Member {
    public static void main(String[] args) {
        About frame = new About();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class About extends JFrame {
    JLabel name1 = new JLabel("Sittichai Srinakares 66011212225 Sec.2");
    JLabel name2 = new JLabel("Pattanapong Puthirat 66011212116 Sec.2");
    JLabel name3 = new JLabel("Yanawarud Saenkaew 66011212255 Sec.1");

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JButton but1 = new JButton();
    JButton but2 = new JButton();
    JButton but3 = new JButton();

    Font labelFont = new Font("Arial", Font.PLAIN, 20);

    public About() {
        setTitle("ABOUT");
        setLocationRelativeTo(null);
        setSize(1000, 700);
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //ใส่รูป/เพิ่มรูป
        ImageIcon imgIcon1 = new ImageIcon("images/Member1.jpg");
        ImageIcon imgIcon2 = new ImageIcon("images/Member2.jpg");
        ImageIcon imgIcon3 = new ImageIcon("images/Member3.jpg");

        //Set ขนาดรูปภาพ
        Image img1 = imgIcon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image img2 = imgIcon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image img3 = imgIcon3.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        //Set รูปลงปุ่ม
        but1.setIcon(new ImageIcon(img1));
        but2.setIcon(new ImageIcon(img2));
        but3.setIcon(new ImageIcon(img3));

        //SetFont
        name1.setFont(labelFont);
        name2.setFont(labelFont);
        name3.setFont(labelFont);

        //Panel 1
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(null);
        panel1.setBackground(new Color(246, 234, 203));
        name1.setBounds(200, 10, 500, 150);
        but1.setBounds(600, 10, 150, 150);
        panel1.add(name1);
        panel1.add(but1);

        //Pane150
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(null);
        panel2.setBackground(new Color(246, 234, 203));
        name2.setBounds(200, 10, 500, 150);
        but2.setBounds(600, 10, 150, 150);
        panel2.add(name2);
        panel2.add(but2);

        //Panel 3
        panel3.setBackground(Color.lightGray);
        panel3.setLayout(null);
        panel3.setBackground(new Color(246, 234, 203));
        name3.setBounds(200, 10, 500, 150);
        but3.setBounds(600, 10, 150, 150);
        panel3.add(name3);
        panel3.add(but3);

        //เพิ่มpanelเข้าไปในframe
        add(panel1);
        add(panel2);
        add(panel3);
    }
}