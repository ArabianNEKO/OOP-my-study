package OOPweek6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class shootghost extends JFrame {
    public shootghost() {
        setSize(1000, 563);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        shootghost frame = new shootghost();
        Mypanal panel = new Mypanal();
        panel.addMouseMotionListener(panel);
        frame.add(panel);
        frame.setVisible(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Task(panel), 0, 100);
    }
}

class Mypanal extends JPanel implements MouseMotionListener, MouseListener {
    int i = 0;
    int x = 0;
    int y = 0;

    Font f = new Font("Tahoma", Font.PLAIN, 40);
    Font timeFont = new Font("Arial", Font.BOLD, 60); // ฟอนต์สำหรับแสดงเวลา

    Image bg = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "OOPweek6\\image\\background.jpg");
    Image ghost = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "OOPweek6\\image\\ghost.png");
    Image crosshair = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "OOPweek6\\image\\sight.gif");

    int[] ghostX = new int[5];
    int[] ghostY = new int[5];
    boolean[] ghostAlive = new boolean[5]; // ตัวแปรที่ติดตามสถานะของผี

    long startTime; // เวลาเริ่มเกม
    long endTime;   // เวลาเมื่อผีตัวสุดท้ายตาย
    boolean allGhostsDead = false; // ตัวแปรเพื่อเช็คว่าผีทั้งหมดถูกยิงแล้วหรือไม่

    public Mypanal() {
        for (i = 0; i < 5; i++) {
            ghostX[i] = new Random().nextInt(900);
            ghostY[i] = new Random().nextInt(460);
            ghostAlive[i] = true; // ผียังมีชีวิต
        }
        addMouseListener(this); // เพิ่ม MouseListener ลงใน Panel
        startTime = System.currentTimeMillis(); // จับเวลาเริ่มต้นเกม
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bg, 0, 0, this);
        g.setFont(f);

        // สร้างสี่เหลี่ยมสีน้ำเงินรอบคำว่า Ghost Hunter
        g.setColor(Color.cyan);
        g.fillRect(680, 50, 300, 70);

        g.setColor(Color.RED);
        g.drawString("Ghost Hunter", 700, 100);

        g.setColor(Color.yellow);
        g.drawOval(680, 50, 300, 70);

        g.setColor(Color.cyan);
        g.drawRect(680, 50, 300, 70);

        // วาดผีเฉพาะตัวที่ยังมีชีวิตอยู่
        for (i = 0; i < 5; i++) {
            if (ghostAlive[i]) { // ตรวจสอบว่าผียังมีชีวิตอยู่ไหม
                g.drawImage(ghost, ghostX[i], ghostY[i], ghostX[i] + 100, ghostY[i] + 100, 0, 0, 100, 100, this);
            }
        }

        // วาดเส้นเล็ง
        g.drawImage(crosshair, x, y, this);

        // ถ้าผีทั้งหมดตายแล้ว แสดงเวลาที่ใช้ในการเล่น
        if (allGhostsDead) {
            long timeTaken = (endTime - startTime) / 1000; // คำนวณเวลาเป็นวินาที
            g.setFont(timeFont);
            g.setColor(Color.WHITE);
            g.drawString("Time: " + timeTaken + "s", 400, 300); // แสดงผลที่กลางหน้าจอ
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX() - 50;
        this.y = e.getY() - 50;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Playsound playsound = new Playsound();
        playsound.start();

        // ตรวจสอบว่าคลิกโดนผีตัวไหนหรือไม่
        for (int i = 0; i < 5; i++) {
            if (ghostAlive[i] && // ตรวจสอบว่าผียังมีชีวิตอยู่
                e.getX() >= ghostX[i] && e.getX() <= ghostX[i] + 100 &&
                e.getY() >= ghostY[i] && e.getY() <= ghostY[i] + 100) {
                
                ghostAlive[i] = false; // ยิงผีให้ตาย
                repaint();

                // เช็คว่าผีทุกตัวถูกยิงหมดหรือยัง
                boolean allDead = true;
                for (boolean alive : ghostAlive) {
                    if (alive) {
                        allDead = false;
                        break;
                    }
                }
                
                if (allDead) {
                    allGhostsDead = true; // ตั้งค่าให้แสดงเวลาหลังจากผีทั้งหมดตาย
                    endTime = System.currentTimeMillis(); // จับเวลาเมื่อผีตัวสุดท้ายตาย
                }
            }
        }
    }

    // เมธอดอื่นๆ ที่เหลือสามารถคงไว้เหมือนเดิมได้
    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

class Task extends TimerTask {
    Mypanal panel;

    Task(Mypanal p) {
        this.panel = p;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (panel.ghostAlive[i]) { // เคลื่อนที่ผีเฉพาะตัวที่ยังมีชีวิต
                int randx = new Random().nextInt(40) - 20;
                int randy = new Random().nextInt(40) - 20;
                
                // ปรับตำแหน่งผี
                panel.ghostX[i] += randx;
                panel.ghostY[i] += randy;
                
                // ตรวจสอบให้ผีไม่ออกนอกขอบเขตของ JPanel
                if (panel.ghostX[i] < 0) {
                    panel.ghostX[i] = 0;
                }
                if (panel.ghostX[i] > panel.getWidth() - 100) { // 100 คือความกว้างของผี
                    panel.ghostX[i] = panel.getWidth() - 100;
                }
                if (panel.ghostY[i] < 0) {
                    panel.ghostY[i] = 0;
                }
                if (panel.ghostY[i] > panel.getHeight() - 100) { // 100 คือความสูงของผี
                    panel.ghostY[i] = panel.getHeight() - 100;
                }
            }
        }
        
        panel.repaint();
    }
}

class Playsound extends Thread {
    @Override
    public void run() {
        try {
            File wavFile = new File(System.getProperty("user.dir") + File.separator + "OOPweek6\\image\\gun.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(wavFile);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            Thread.sleep(1000);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
