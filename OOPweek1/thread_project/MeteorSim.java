package thread_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MeteorSim extends JFrame {
    // Constructor สำหรับสร้างหน้าต่างหลักของโปรแกรม
    public MeteorSim(int numMeteors) {
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // จัดให้อยู่ตรงกลางหน้าจอ

        // สร้าง panel ที่จะใช้แสดงผลอุกกาบาต
        Mypanal panel = new Mypanal(numMeteors);
        add(panel);
        setVisible(true); // แสดงหน้าต่าง
    }

    public static void main(String[] args) {
        // รับจำนวนอุกกาบาตจากผู้ใช้ผ่าน Dialog
        String input = JOptionPane.showInputDialog(null, "input number for meteors", "จำนวนอุกกาบาต", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int numMeteors = Integer.parseInt(input); // แปลงค่าจาก String เป็น Integer
                new MeteorSim(numMeteors); // สร้างหน้าต่าง
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "please input NUMBER", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cancel", "ยกเลิก", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

class Mypanal extends JPanel {
    public Meteor[] meteors;
    private JLabel gifLabel;

    // Constructor สำหรับสร้าง panel ที่แสดงอุกกาบาต
    Mypanal(int numMeteors) {
        setSize(785, 765);
        setLayout(null);
        setBackground(Color.BLACK); // กำหนดสีพื้นหลังเป็นสีดำ

        meteors = new Meteor[numMeteors];
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int centerX = panelWidth / 2;
        int centerY = panelHeight / 2;
        int radius = 200; // รัศมีการกระจายของอุกกาบาต

        // สร้างอุกกาบาตจำนวนตามที่ผู้ใช้ระบุ
        for (int i = 0; i < meteors.length; i++) {
            int startX = centerX + new Random().nextInt(radius * 2) - radius; // ตำแหน่ง X เริ่มต้น
            int startY = centerY + new Random().nextInt(radius * 2) - radius; // ตำแหน่ง Y เริ่มต้น
            meteors[i] = new Meteor(this, startX, startY);
            setUniqueSpeed(meteors, meteors[i]);                    // ตั้งค่าความเร็วที่ไม่ซ้ำกัน
            meteors[i].start();                                     // เริ่ม thread ของอุกกาบาต
        }

        gifLabel = new JLabel();
        gifLabel.setBounds(0, 0, 100, 100);
        gifLabel.setVisible(false);
        add(gifLabel);

        // ตรวจสอบการคลิกเมาส์เพื่อกำจัดอุกกาบาต
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    for (int i = 0; i < meteors.length; i++) {
                        if (meteors[i] != null && meteors[i].checkHit(e.getX(), e.getY())) {
                            meteors[i].removeMeteor();               // ลบอุกกาบาตที่ถูกคลิก
                            meteors[i] = null;                      // ตั้งค่าให้เป็น null
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Meteor meteor : meteors) {
            if (meteor != null) {
                meteor.draw(g); // วาดอุกกาบาตแต่ละลูก
            }
        }
    }

    // แสดง GIF เมื่ออุกกาบาตถูกทำลาย
    public void showGif(String gifPath, int x, int y) {
        gifLabel.setIcon(new ImageIcon(gifPath));
        gifLabel.setBounds(x, y, 100, 100);
        gifLabel.setVisible(true);
        Timer timer = new Timer(500, e -> gifLabel.setVisible(false)); // ตั้งเวลาให้ GIF
        timer.setRepeats(false);
        timer.start();
    }

    // ฟังก์ชันตั้งค่าความเร็วที่ไม่ซ้ำกันให้กับอุกกาบาตแต่ละลูก
    private void setUniqueSpeed(Meteor[] meteors, Meteor currentMeteor) {
        Random rand = new Random();
        int newSpeed;
        boolean uniqueSpeed;

        do {
            uniqueSpeed = true;
            newSpeed = rand.nextInt(5) + 1; // สุ่มความเร็วใหม่
            for (Meteor meteor : meteors) {
                if (meteor != null && meteor != currentMeteor && meteor.getSpeed() == newSpeed) {
                    uniqueSpeed = false; // ถ้าความเร็วซ้ำกัน
                    break;
                }
            }
        } while (!uniqueSpeed);

        currentMeteor.setSpeed(newSpeed); // ตั้งค่าความเร็วใหม่ให้กับอุกกาบาต
    }
}

class Meteor extends Thread {
    private int x, y, dx, dy;
    private int size = 50;
    private Image image;
    private Mypanal panel;
    private boolean isVisible = true;
    private Random rand = new Random();

    public Meteor(Mypanal panel, int startX, int startY) {
        this.panel = panel;
        this.x = startX;
        this.y = startY;
        this.dx = (rand.nextInt(2) == 0 ? 1 : -1) * (rand.nextInt(5) + 1); // สุ่มทิศทางและความเร็ว X
        this.dy = (rand.nextInt(2) == 0 ? 1 : -1) * (rand.nextInt(5) + 1); // สุ่มทิศทางและความเร็ว Y
        loadRandomImage(); // โหลดรูปภาพอุกกาบาตแบบสุ่ม
    }

    // โหลดรูปภาพอุกกาบาตแบบสุ่ม
    private void loadRandomImage() {
        String[] imageFiles = {
            "thread_project/images/1.png",
            "thread_project/images/2.png",
            "thread_project/images/3.png",
            "thread_project/images/4.png",
            "thread_project/images/5.png",
            "thread_project/images/6.png",
            "thread_project/images/7.png",
            "thread_project/images/8.png",
            "thread_project/images/9.png",
            "thread_project/images/10.png"
        };
        int index = rand.nextInt(imageFiles.length); // สุ่มรูป
        image = new ImageIcon(imageFiles[index]).getImage();
    }

    public void run() {
        while (!isInterrupted()) {                      // ทำงานจนกว่าจะถูกหยุด
            move();                                     // อัปเดตตำแหน่ง
            panel.repaint();                            // สั่งให้วาดใหม่
            try {
                Thread.sleep(16);                       // หน่วงเวลา 60 เฟรม
            } catch (InterruptedException e) {
                return;                                 // ถ้าถูกขัดจังหวะให้หยุดทำงาน
            }
        }
    }

    // ฟังก์ชันสำหรับการย้ายตำแหน่งของอุกกาบาต
    public void move() {
        x += dx;        // อัปเดตตำแหน่ง X
        y += dy;        // อัปเดตตำแหน่ง Y

        // ตรวจสอบการชนกับขอบของ panel และทำให้อุกกาบาตไม่หลุดจากกรอบ
        if (x < 0) {
            x = 0;                                      // ถ้าชนขอบซ้าย ให้ตั้งค่า x = 0
           change();                                    // เปลี่ยนความเร็ว และทิศทาง
        } else if (x > panel.getWidth() - size) {
            x = panel.getWidth() - size;                // ถ้าชนขอบขวา ให้อยู่ในกรอบ
          change();
        }
        if (y < 0) {
            y = 0;                                      // ถ้าชนขอบบน ให้ตั้งค่า y = 0
           change();
        } else if (y > panel.getHeight() - size) {
            y = panel.getHeight() - size;               // ถ้าชนขอบล่าง ให้อยู่ในกรอบ
          change();
        }

        // ตรวจสอบการชนกันระหว่างอุกกาบาต
        for (Meteor other : panel.meteors) {
            if (other != null && other != this && checkCrash(other)) {
                resolveCrash(other); // แก้ไขการชน
            }
        }
    }

    // แก้ไขเมื่ออุกกาบาตชนกัน
    private void resolveCrash(Meteor other) {
        // ตรวจสอบทิศทางของอุกกาบาตลูกปัจจุบันและลูกที่ชน
        dx = -dx;   // เปลี่ยนทิศทาง
        dy = -dy;
        other.dx = -other.dx;
        other.dy = -other.dy;

         // เคลื่อนที่เล็กน้อยเพื่อหลีกเลี่ยงการติดอยู่ในลูปการชน
        x += dx * 2; // ขยับออกหลังชน
        y += dy * 2;
        other.x += other.dx * 2;
        other.y += other.dy * 2;

        change();
        other.change();
    }

    // เปลี่ยนทิศทางเมื่อมีการชน
    private void change() {
        int direction = rand.nextInt(8); // 8 ทิศทาง
        int speed = rand.nextInt(5) + 1; // สุ่มความเร็ว
        switch (direction) {
            case 0: //ขวา
                dx = speed;  // ความเร็วในแนวนอน
                dy = 0;         // ความเร็วในแนวตั้ง
                break;   
            case 1: //ซ้าย
                dx = -speed;
                dy = 0;
                break;
            case 2: //ขึ้น
                dx = 0;
                dy = -speed;
                break;
            case 3: //ลง
                dx = 0;
                dy = speed;
                break;
            case 4: //ขวาเฉียงขึ้น
                dx = speed;
                dy = -speed;
                break;
            case 5: //ขวาเฉียงลง
                dx = speed;
                dy = speed;
                break;
            case 6: //ซ้ายเฉียงขึ้น
                dx = -speed;
                dy = -speed;
                break;
            case 7: //ซ้ายเฉียงลง
                dx = -speed;
                dy = speed;
                break;
        }
    }

    // ตรวจสอบว่ามีการชนกับอุกกาบาตตัวอื่นหรือไม่
    private boolean checkCrash(Meteor other) {
        int buffer = 4;  // ระยะขอบสำหรับการชน
        int currentLeft = x - buffer;
        int currentRight = x + size + buffer;
        int currentTop = y - buffer;
        int currentBottom = y + size + buffer;

        int otherLeft = other.x - buffer;
        int otherRight = other.x + other.size + buffer;
        int otherTop = other.y - buffer;
        int otherBottom = other.y + other.size + buffer;

        return (currentLeft < otherRight && currentRight > otherLeft &&
                currentTop < otherBottom && currentBottom > otherTop);
    }

    // วาดอุกกาบาต
    public void draw(Graphics g) {
        if (isVisible) {
            g.drawImage(image, x, y, size, size, null); // วาดอุกกาบาตที่ตำแหน่ง (x, y) ขนาด 50x50
        }
    }

    // ตรวจสอบว่าถูกคลิกหรือไม่
    public boolean checkHit(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size; 
    }

    // ลบอุกกาบาตและแสดง GIF
    public void removeMeteor() { 
        panel.showGif("thread_project/images/bomb.gif", x, y);
        isVisible = false;
        interrupt(); // หยุด thread
    }

    // คืนค่าความเร็วของอุกกาบาต
    public int getSpeed() {
        return Math.abs(dx); // คืนค่าความเร็วที่เป็นบวก
    }

    // ตั้งค่าความเร็วของอุกกาบาต
    public void setSpeed(int speed) {
        this.dx = this.dx < 0 ? -speed : speed;  // ถ้า dx เป็นลบให้กำหนดความเร็วลบ
        this.dy = this.dy < 0 ? -speed : speed;  // ถ้า dy เป็นลบให้กำหนดความเร็วลบ
    }
}

