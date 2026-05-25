package gameproject;
import javax.swing.*;
import java.awt.*;

public class Lobby extends JFrame {
    Lobby() {
        setSize(1600, 900);
        setLayout(null); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Lobbypanel panel = new Lobbypanel();
        panel.setBounds(0, 0, 1600, 900); // จัดขนาดและตำแหน่งของ panel ให้เต็มหน้าต่าง
        add(panel);

        setVisible(true); 
    }

    public static void main(String[] args) {
        new Lobby();
    }
}

class Lobbypanel extends JPanel {
    Lobbypanel() {
        setBackground(Color.BLACK);
        setLayout(null); 

        // Title Label
        JLabel titleLabel = new JLabel(" Lobby ");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 58)); 
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(400, 50, 800, 60); // ตำแหน่งกลางจอ
        add(titleLabel);

        // Home Menu Button
        JButton homeButton = new JButton("Home Menu");
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        homeButton.setBounds(20, 20, 150, 40);
        homeButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose(); // ปิดหน้าต่างเมื่อคลิกปุ่ม
        });
        add(homeButton);

        // Player Labels and Status Bars
        String[] players = {"Player 1", "Player 2", "Player 3", "Player 4"};
        Color[] colors = {Color.RED, Color.ORANGE, Color.BLUE, Color.GREEN};

        for (int i = 0; i < players.length; i++) {
            // วาดกรอบหลังผู้เล่น (เพิ่มระยะห่าง)
            JPanel playerPanel = new JPanel();
            playerPanel.setBackground(Color.WHITE);
            playerPanel.setBounds(400, 200 + (i * 100), 300, 50); // ตำแหน่งกรอบผู้เล่น
            playerPanel.setLayout(null); 
            add(playerPanel);

            JLabel playerLabel = new JLabel(players[i]);
            playerLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
            playerLabel.setForeground(Color.BLACK);
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLabel.setBounds(0, 0, 300, 50); // ป้ายกำกับอยู่กลางกรอบ
            playerPanel.add(playerLabel);

            // Status bar สีด้านขวา (ขยับไปทางขวา)
            JPanel colorBar = new JPanel();
            colorBar.setBackground(colors[i]);
            colorBar.setBounds(820, 200 + (i * 100), 300, 50); // ขยับไปทางขวา
            add(colorBar);
        }

        // "YOU" Label for Player 3
        JLabel youLabel = new JLabel("YOU");
        youLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        youLabel.setForeground(Color.WHITE);
        youLabel.setHorizontalAlignment(SwingConstants.CENTER);
        youLabel.setBounds(700, 400, 50, 50); // ตำแหน่ง Player 3
        add(youLabel);

        // Start Button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.BOLD, 28));
        startButton.setBounds(600, 650, 400, 50); // ตำแหน่งปุ่ม Start
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
