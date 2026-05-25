package OOPweek5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
public class GUI_3 {
    //ใช้ panal
    public static void main(String[] args) {
        MyframeColor frame = new MyframeColor();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class MyframeColor extends JFrame
{
    JPanel panel =new JPanel();
    JTextField textField = new JTextField();
    MyframeColor()
    {
        setSize(512, 512);
        setLocation(500, 500);
        setLayout(new BorderLayout());

        add(textField,BorderLayout.NORTH);

        panel.setSize(512,512);
        panel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
              int x=e.getX();
              int y=e.getY();
              textField.setText(" x= "+x+" y= "+y+" Color=( r= "+x/2+" g= "+y/2+" b= "+(x+y)/4+" ) " );
              Color color= new Color(x/2,y/2,(x+y)/4);
              panel.setBackground(color);
            }
            
        });
        add(panel,BorderLayout.CENTER);
    }
}
