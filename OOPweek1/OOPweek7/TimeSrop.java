package OOPweek7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TimeSrop {
    public static void main(String[] args) {
        Mywatch frame = new Mywatch();
        frame.setVisible(true);
    }
}

class Mywatch extends JFrame {
    JLabel lbtime = new JLabel("00:00:00:000", SwingConstants.CENTER); // ตั้งค่าให้อยู่ตรงกลางแนวนอน
    JButton btStartStop = new JButton(" Start/Stop ");
    MywatchThread thread;
    boolean flag = false;

    Mywatch() {
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // ตั้งค่าให้อยู่ตรงกลางแนวตั้งและแนวนอน
        lbtime.setHorizontalAlignment(SwingConstants.CENTER); // แนวนอน
        lbtime.setVerticalAlignment(SwingConstants.CENTER);   // แนวตั้ง
        
        add(lbtime, BorderLayout.CENTER);
        add(btStartStop, BorderLayout.SOUTH);

        btStartStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btStartStopClicked(e);
            }
        });
        this.thread = new MywatchThread(lbtime);
        this.thread.start();
        this.thread.setFlag(true);
        flag = true;
    }

    private void btStartStopClicked(ActionEvent e) {
        if (flag == false) {
            this.thread.setFlag(true);
            this.flag = true;
        } else {
            this.thread.setFlag(false);
            this.flag = false;
        }
    }
}

class MywatchThread extends Thread {
    JLabel label;
    private boolean flag = false;

    MywatchThread(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        int counter = 0;
        int mil = 0;
        int sec = 0;
        int min = 0;
        int hour = 0;
        while (true) {
            if (flag) {
                this.label.setText(String.format("%02d:%02d:%02d:%03d", hour, min, sec, mil));
                counter++;
                if (counter >= 1000) {
                    sec++;
                    counter = 0;
                }
                mil = counter;

                if (sec >= 60) {
                    min++;
                    sec = 0;
                }

                if (min >= 60) {
                    hour++;
                    min = 0;
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
