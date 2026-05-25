package tiwOOP;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main {
    public static void main(String[] args) {
        myframe frame = new myframe();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}

class myframe extends JFrame implements ActionListener
{
    JPanel panalfuction = new JPanel();
    JPanel showcolor = new JPanel();
    JPanel showimage = new JPanel();
    JPanel status = new JPanel();
    JPanel showbutton = new JPanel();

    // Panal ข้างบน
    JButton openfile = new JButton("OpenFile"); 
    JTextField filepath = new JTextField();

    JButton Natural_Rain = new JButton("Natural Rain");
    JButton Artificial_Rain = new JButton("Artificial Rain");

    JTextField people = new JTextField();
    JTextField range_num1 = new JTextField();
    JTextField range_num2 =new JTextField();

    JButton ok = new JButton("ok");

    //// Panal ดูเกรณ์
    JButton green = new JButton("Patient 0-9 %");
    JButton yellow = new JButton("Patient 10-19 %");
    JButton orange = new JButton("Patient 20-29 %");
    JButton red = new JButton("Patient Over 30 %");

    // Panal แสดงรูปภาพ
    JLabel imagLabel = new JLabel();

    //// แสดง Status 
    JLabel dust = new JLabel("Dust");
    JLabel dust_result = new JLabel("0");
    JLabel population = new JLabel("Population");
    JLabel pp_result = new JLabel("0");
    JLabel healthy = new JLabel("Healthy");
    JLabel healthy_result = new JLabel("0");
    JLabel patient = new JLabel("Patient");
    JLabel patiant_result = new JLabel("0");
    JLabel perpatient = new JLabel("PercenPatient");
    JLabel perpatient_result = new JLabel("0.00 %");

    // index
    int seleteindex = -1;

    // Panal แสดงปุ่ม
    JButton[] gridbutton = new JButton[200];
    
    // Array เก็บค่าข้อมูล
    int[][] pm25data = new int[10][20];
    int[][] populationdata = new int[10][20];

  myframe ()
{
    setTitle("Menu");
    setLocation(250,250);
    setSize(1500, 900);
    setLayout(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Panal ข้างบน 
    panalfuction.setBounds(10,10,1465,100);
    panalfuction.setBackground(Color.GRAY);
    panalfuction.setLayout(null);
    
    openfile.setBounds(20, 55, 200, 30);;
    openfile.setBackground(Color.white);
    openfile.addActionListener(this);

    filepath.setBounds(20, 20, 200, 30);

    Natural_Rain.setBounds(400, 20, 180, 65);
    Natural_Rain.setBackground(Color.white);

    Artificial_Rain.setBounds(600,20,180,65);
    Artificial_Rain.setBackground(Color.white);

    people.setBounds(900, 20, 250, 30);

    range_num1.setBounds(900, 55, 100, 30);
    range_num2.setBounds(1050, 55, 100, 30);

    ok.setBounds(1200, 20, 150, 65);
    ok.setBackground(Color.white);

    panalfuction.add(ok);
    panalfuction.add(people);
    panalfuction.add(range_num1);
    panalfuction.add(range_num2);
    panalfuction.add(Natural_Rain);
    panalfuction.add(Artificial_Rain);
    panalfuction.add(filepath);
    panalfuction.add(openfile);

    add(panalfuction);



    // Panal ดูเกรณ์
    showcolor.setBounds(10,115,300,200);
    showcolor.setBackground(Color.GRAY);
    showcolor.setLayout(new GridLayout(2,2));

    green.setBackground(Color.green);
    yellow.setBackground(Color.yellow);
    orange.setBackground(Color.orange);
    red.setBackground(Color.red);

    showcolor.add(green);
    showcolor.add(yellow);
    showcolor.add(orange);
    showcolor.add(red);
    add(showcolor);

    //Panal แสดงรูปภาพ
    showimage.setBounds(10, 320,300, 200);
    showimage.setBackground(Color.WHITE);
    showimage.setLayout(null);

    add(showimage);

    // แสดง Status 
    status.setBounds(10, 510, 300, 345);
    status.setBackground(Color.GRAY);
    status.setLayout(new GridLayout(5,2));

    status.add(dust);
    status.add(dust_result);
    status.add(population);
    status.add(pp_result);
    status.add(healthy);
    status.add(healthy_result);
    status.add(patient);
    status.add(patiant_result);
    status.add(perpatient);
    status.add(perpatient_result);

    add(status);

    // Panal แสดงปุ่ม 
    showbutton.setBounds(325, 115, 1150, 739);
    showbutton.setBackground(Color.GRAY);
    showbutton.setLayout(new GridLayout(10,20));

    for (int i=0; i<200; i++)
    {
        gridbutton[i] =new JButton();
        showbutton.add(gridbutton[i]);
    }
    add(showbutton);
}


@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == openfile)
    {
        JFileChooser fileChooser = new JFileChooser();
        int report = fileChooser.showOpenDialog(null);
        if (report == JFileChooser.APPROVE_OPTION)
        {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            filepath.setText(file.getPath());
            readfile(file);
        }
    }
    else if(e.getSource() == ok)
    {
      
    }
  
}

//////////////////////////////////////////////////////////////////////////////////
// readfile
void readfile(File file)
{
    try (BufferedReader bfd = new BufferedReader(new FileReader(file))){
        String line;
        int row = 0;
        while ((line = bfd.readLine()) != null && row <10)
        {
            String[] value = line.split("\\s+");
            for (int col=0; col<value.length && col <20;col++)
            {
                try {
                    int data = Integer.parseInt(value[col]);
                    if (data < 0 )
                    {
                        data = 0;
                    }
                    pm25data[row][col] = data;
                } catch (Exception ex) {
                    pm25data[10][20] = 0;
                }
            }
            row++;
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



}


