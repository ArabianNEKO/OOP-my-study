package miniproject;
import javax.swing.*;     
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JButton btn_start = new JButton("START");
    JButton btn_about = new JButton("ABOUT");
    JButton btn_exit = new JButton("EXIT");
    

    public Menu() {
        setTitle("Miniproject PM 2.5");
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(246, 234, 203));
        setLocationRelativeTo(null);
        JLabel label = new JLabel("PM 2.5");

        Font buttonFont = new Font("Arail", Font.BOLD, 20);
        Font labelFont = new Font("Arail", Font.BOLD, 50);

        ImageIcon imgIcon1 = new ImageIcon("images/city-logo.png");
        Image img1 = imgIcon1.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        ImageIcon cityIcon = new ImageIcon(img1);
        JLabel icon1 = new JLabel(cityIcon);
        icon1.setBounds(100, 370, 800, 400); //ตำแหน่งและขนาดภาพ

        ImageIcon imgIcon2 = new ImageIcon("images/pm2.5.png");
        Image img2 = imgIcon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon pmIcon = new ImageIcon(img2);
        JLabel icon2 = new JLabel(pmIcon);
        icon2.setBounds(415, 70, 150, 150); //ตำแหน่งและขนาดภาพ

        label.setBounds(410, 180, 180, 70);
        label.setFont(labelFont);

        //set font
        btn_start.setFont(buttonFont);
        btn_about.setFont(buttonFont);
        btn_exit.setFont(buttonFont);

        //ปรับขนาดปุ่ม
        btn_start.setBounds(400, 280, 180, 70);
        btn_start.setBackground(new Color(209, 233, 246));

        btn_about.setBounds(400, 380, 180, 70);
        btn_about.setBackground(new Color(209, 233, 246));

        btn_exit.setBounds(400, 480, 180, 70);
        btn_exit.setBackground(new Color(209, 233, 246));

        btn_start.addActionListener(this);
        btn_about.addActionListener(this);
        btn_exit.addActionListener(this);

        //add to frame
        add(btn_start);
        add(btn_about);
        add(btn_exit);
        add(label);
        add(icon1);
        add(icon2);
    }

    //Event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_start) {
            MainFrarme main = new MainFrarme();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        } else if (e.getSource() == btn_about) {
            About aboutPage = new About(); 
            aboutPage.setLocationRelativeTo(null);
            aboutPage.setVisible(true);
        } else if (e.getSource() == btn_exit) {
            System.exit(0);
        }
    }

    // จากนี้เริ่มต้นการทำงานหลังจากเปิดหน้าต่าง Menu
    // สามารถเรียกใช้หน้าต่างอื่น ๆ หรือเริ่มกระบวนการอื่นๆ ตามต้องการ
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}