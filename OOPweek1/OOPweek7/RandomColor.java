package OOPweek7;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.util.Random;

public class RandomColor  {
  public static void main(String[] args) {
    Mypanel frame = new Mypanel();
    frame.setVisible(true);
  }
}

class Mypanel extends JFrame {
    JLabel []labels = new JLabel[4];
    Mypanel(){
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2));
        for (int i=0;i<labels.length; i++)
        {
            labels[i] =  new JLabel();
            int rand = 0;
            if(i==0){
                labels[i].setText("Change Color 5 sec");
                rand =5;
            }
            else if (i==1)
            {
                labels[i].setText("Change Color 10 sec");
                rand = 10;
            }
            else if(i==2)
            {
                rand = new Random().nextInt(5);
                labels[i].setText("Change Color "+rand+" sec");
            }
            else if (i==3)
            {
                rand = new Random().nextInt(5)+5;
                labels[i].setText("Change Color "+rand+" sec");
            }
            Mythread th = new Mythread(labels[i], rand);
            th.start();
            add(labels[i]);
        }
    }
}

class Mythread extends Thread
{
    JLabel label;
    int delay = 0;

    Mythread(JLabel label, int delay )
    {
        this.label = label;
        this.delay = delay;
    }
    @Override
    public void run() {
        while (true) {
            Color color = new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
            label.setForeground(color);
            try {
                Thread.sleep(delay*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}