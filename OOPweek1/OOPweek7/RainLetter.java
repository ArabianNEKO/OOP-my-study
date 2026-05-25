package OOPweek7;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RainLetter  {
    public static void main(String[] args) {
        Rain frame = new Rain();
        frame.setVisible(true);
    }
}

class Rain extends JFrame
{
    JLabel []letter = new JLabel[26]; 
    MyraidThread []myraidThread = new MyraidThread[26];

    JButton StartStop = new JButton(" Start/Stop ");
    Rain()
    {
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        for (int i=0;i<letter.length;i++)
        {
            letter[i] = new JLabel(""+(char)(i+97));
            letter[i].setSize(20,20);
            letter[i].setLocation(((i+1)*25), 10);
            Color color = new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
            letter[i].setForeground(color);
            add(letter[i]);

            StartStop.setBounds(300, 410, 150, 30);
            add(StartStop);

            myraidThread[i] = new MyraidThread(letter[i]);
            myraidThread[i].setSleep(new Random().nextInt(300)+20);
            myraidThread[i].start();

        }
        StartStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               startstop();
            }
         
        });
    }
    
    private void startstop() {
        for (int i=0 ;i<letter.length;i++)
        {
            myraidThread[i].setFlag(!myraidThread[i].isFlag());
        }
    }
}

class MyraidThread extends Thread 
{
    JLabel letters;
    int sleep = 0 ;
    boolean flag = true;
   
    MyraidThread(JLabel letters)
    {
        this.letters = letters;
    }
    @Override
    public void run() {
        while (true) {
            if (isFlag()) {
                letters.setLocation(letters.getX(), letters.getY() + 1);
    
                if (letters.getY() > 400) {
                    letters.setLocation(letters.getX(), 10);
                }
            }
    
            try {
                // ถ้า flag เป็น false จะทำการรออยู่ตรงนี้
                if (!isFlag()) {
                    Thread.sleep(100); // รอเล็กน้อยเพื่อให้ไม่เปลือง CPU
                } else {
                    Thread.sleep(this.sleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int getSleep() {
        return sleep;
    }
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}


