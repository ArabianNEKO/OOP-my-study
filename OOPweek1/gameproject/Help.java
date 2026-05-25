package gameproject;
import javax.swing.*;
import java.awt.*;

public class Help extends JFrame {
    public Help() {
        // ขนาดหน้าต่าง และการตั้งค่าเบื้องต้น
        setSize(1600, 900);
        setLayout(null); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 

        // เพิ่ม panel ที่มีเนื้อหาวิธีเล่น
        Helps panel = new Helps();
        panel.setBounds(0, 0, 1600, 900); // จัดขนาดและตำแหน่งของ panel ให้เต็มหน้าต่าง
        add(panel);

        setVisible(true); 
    }

    public static void main(String[] args) {
        // สร้างหน้าต่าง help เมื่อรันโปรแกรม
        new Help();
    }
}

class Helps extends JPanel {
    public Helps() {
        setBackground(Color.BLACK);
        setLayout(null); 

        // สร้าง JLabel สำหรับหัวข้อ "How to Play"
        JLabel titleLabel = new JLabel("How to Play ");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 58)); 
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // จัดให้อยู่กลางแนวนอน

        // คำนวณตำแหน่งให้อยู่กลางแนวนอน และวางด้านบน
        int titleWidth = 800;
        int titleHeight = 60;
        int panelWidth = 1600;
        int titleX = (panelWidth - titleWidth) / 2; // คำนวณให้อยู่กลางแนวนอน
        int titleY = 50; // ตั้งให้อยู่ด้านบน

        titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight); // กำหนดตำแหน่งของหัวข้อ
        add(titleLabel); 

        // สร้าง JTextArea สำหรับแสดงข้อความวิธีเล่นเกม Pong
        JTextArea helpText = new JTextArea(
           "\n\n1. เกมนี้รองรับผู้เล่น 4 คน\n" +
            "2. ใช้แป้นพิมพ์เพื่อควบคุม Paddle ของคุณ: \n" +
            "   - ผู้เล่นบังคับโดยใช้: ปุ่ม W และ S \n" +
            "   - หรือ ปุ่มลูกศรขึ้นและลงก็ได้\n" +
            "3. เป้าหมายของคุณคือการเด้งลูกบอลและป้องกันไม่ให้ลูกบอลออกนอกสนามฝั่งของคุณ \n" +
            "4. ถ้าลูกบอลเข้าฝั่งคุณ ผู้เล่นคนอื่นจะได้คะแนน \n" +
            "5. เล่นจนกว่าจะมีผู้ชนะที่กำหนดตามคะแนน"
        );
        helpText.setFont(new Font("Tahoma", Font.PLAIN, 24)); 
        helpText.setForeground(Color.WHITE);
        helpText.setBackground(Color.BLACK);
        helpText.setEditable(false); // ป้องกันการแก้ไขข้อความ
        helpText.setOpaque(false); // ทำให้โปร่งแสงเข้ากับพื้นหลัง

        // คำนวณตำแหน่งให้อยู่กลาง
        int textWidth = 1000;
        int textHeight = 600;
        int textX = (panelWidth - textWidth) / 2; // คำนวณให้อยู่กลางแนวนอน
        int textY = titleY + titleHeight + 20; // วางให้ห่างจากหัวข้อ 20 พิกเซล

        helpText.setBounds(textX, textY, textWidth, textHeight); // กำหนดตำแหน่งของ JTextArea
        add(helpText); // เพิ่ม JTextArea เข้าไปใน JPanel

        // สร้างปุ่ม Home Menu และวางไว้ที่มุมซ้ายบน
        JButton homeButton = new JButton("Home Menu");
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 18));  
        homeButton.setBounds(20, 20, 150, 40);      // จัดตำแหน่งของปุ่มให้อยู่มุมซ้ายบน
        homeButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose(); // ปิดหน้าต่าง Help เมื่อคลิกปุ่ม
        });
        add(homeButton); 
    }
}
