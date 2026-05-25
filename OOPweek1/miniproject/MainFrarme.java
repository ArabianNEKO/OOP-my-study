package miniproject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

public class MainFrarme extends mainframe {
    public static void main(String[] args) {
        mainframe frame = new mainframe();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class mainframe extends JFrame implements ActionListener{

    private int selectedIndex = -1;
    private boolean isArtificialRainMode = false;
    private JLabel imageLabel = new JLabel();
    private JButton[] gridButtons = new JButton[200];
    
    JPanel panelFunction = new JPanel();
    JPanel showButton = new JPanel();
    JPanel showImage = new JPanel();
    JPanel Status = new JPanel();
    JPanel Simulation = new JPanel();
    

    JButton OpenFile_btn = new JButton("Open File");
    JButton Nutural_Rain = new JButton("Natural Rain");
    JButton Artificial_rain = new JButton("Artificial Rain");
    JButton OK = new JButton("OK");

    JButton green = new JButton("Patient 0-9%");
    JButton yellow = new JButton("Patient 10-19%");
    JButton orange = new JButton("Patient 20-29%");
    JButton red = new JButton("Patient Over 30%");


    JLabel Dust = new JLabel("Dust");
    JLabel Dust_Result = new JLabel("0");
    JLabel Population = new JLabel("Population");
    JLabel Population_Result = new JLabel("0");
    JLabel Healthy = new JLabel("Healthy");
    JLabel Healthy_Result = new JLabel("0");
    JLabel Patient = new JLabel("Patient");
    JLabel Patient_Result = new JLabel("0");
    JLabel PercentPatient = new JLabel("PercentPatient");
    JLabel PercentPatient_Result = new JLabel("0");

    Font btnFont = new Font("Pacifico", Font.BOLD, 16);
    Font statusFont = new Font("Pacifico", Font.BOLD, 16);
    Font labelFont = new Font("Pacifico", Font.BOLD, 12);

    
    JTextField showpath = new JTextField();
    JTextField people = new JTextField();
    JTextField range_number1 = new JTextField();
    JTextField range_number2 = new JTextField();

    

    int[][] pm25Data = new int[10][20];     // ใช้ในการเก็บข้อมูล PM 2.5 ที่อ่านมาจากไฟล์
    int[][] populationData = new int[10][20]; // ใช้เพื่อเก็บข้อมูลประชากรที่ถูกเฉลี่ยในแต่ละปุ่ม
    private double[][] patientDataArray = new double[10][20]; // เก็บจำนวนผู้ป่วย
    private double[][] normalPersonArray = new double[10][20];  // เก็บจำนวนคนปกติ
    private double[][] patientPercentArray = new double[10][20]; // เก็บจำนวน % ผู้ป่วย





mainframe()
{
 setTitle("Menu");
 setSize(1500, 900);
 setLocation(250, 250);
 setLayout(null);
 setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//set-font
Dust.setFont(statusFont);
Dust_Result.setFont(statusFont);
Population.setFont(statusFont);
Population_Result.setFont(statusFont);
Healthy.setFont(statusFont);
Healthy_Result.setFont(statusFont);
Patient.setFont(statusFont);
Patient_Result.setFont(statusFont);
PercentPatient.setFont(statusFont);
PercentPatient_Result.setFont(statusFont);

//PanalFunction
panelFunction.setLayout(null);
panelFunction.setBackground(new Color(246, 234, 203));
panelFunction.setBounds(10,10,1465, 100);


//OpenFile BTN
OpenFile_btn.setBackground(new Color(209, 233, 246));
OpenFile_btn.setBounds(20, 55, 200, 30);
panelFunction.add(OpenFile_btn);
OpenFile_btn.addActionListener(this);

//showfilePath
showpath.setBounds(20, 20, 200, 30);
panelFunction.add(showpath);

//ปุ่มฝนธรรมชาติ
ImageIcon imgNatural_Rain = new ImageIcon("images/Natural_rain.png");
Image imgRain1 = imgNatural_Rain.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//Nutural_Rain.setFont(btnFont);
Nutural_Rain.setIcon(new ImageIcon(imgRain1));
Nutural_Rain.setBounds(400, 20, 180, 65);
Nutural_Rain.setBackground(new Color(209, 233, 246));
panelFunction.add(Nutural_Rain);
Nutural_Rain.addActionListener(this);

//ปุ่มฝนหลวง
ImageIcon imgArtificial_Rain = new ImageIcon("images/Artificial_rain.png");
Image imgRain2 = imgArtificial_Rain.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//Artificial_rain.setFont(btnFont);
Artificial_rain.setIcon(new ImageIcon(imgRain2));
Artificial_rain.setBounds(600, 20, 180, 65);
Artificial_rain.setBackground(new Color(209, 233, 246));
panelFunction.add(Artificial_rain);
Artificial_rain.addActionListener(this);

//ประชากร
people.setBounds(900, 20, 250, 30);
people.setToolTipText("Input Population");

panelFunction.add(people);

//กรอกช่วงของตัวเลข
range_number1.setBounds(900, 55, 100, 30);
range_number1.setToolTipText("Random Input number Start");
range_number2.setBounds(1050, 55, 100, 30);
range_number2.setToolTipText("Random Input number Stop");

panelFunction.add(range_number1);
panelFunction.add(range_number2);

//ปุ่มคำนวณ
OK.setFont(btnFont);
OK.setBackground(new Color(209, 233, 246));
OK.setBounds(1200, 20, 150, 65);
panelFunction.add(OK);
OK.addActionListener(this);  
//=======================================================================================================




//Panel Color

showButton.setBackground(new Color(204, 204, 204));
showButton.setBounds(10,115,300, 200);
showButton.setLayout(new GridLayout(2,2));

//สีปุ่มแสดงคนป่วย
green.setBackground(Color.GREEN);
yellow.setBackground(Color.YELLOW);
orange.setBackground(Color.ORANGE);
red.setBackground(Color.RED);

//add-Button
showButton.add(green);
showButton.add(yellow);
showButton.add(orange);
showButton.add(red);


//=======================================================================================================
// Image for show Town
showImage.setBackground(Color.WHITE);
imageLabel.setBounds(0, 0, 300, 185);
showImage.setBounds(10, 320, 300, 185);
showImage.setLayout(null);
showImage.add(imageLabel);


//=======================================================================================================



//Panel Status
Status.setBackground(new Color(246, 234, 203));
Status.setBounds(10,510,300, 345);
Status.setLayout(new GridLayout(5,2));

//add-Status
Status.add(Dust);
Status.add(Dust_Result);

Status.add(Population);
Status.add(Population_Result);

Status.add(Healthy);
Status.add(Healthy_Result);

Status.add(Patient);
Status.add(Patient_Result);

Status.add(PercentPatient);
Status.add(PercentPatient_Result);

//=======================================================================================================



//Panel Simulations

Simulation.setLayout(new GridLayout(10,20));
Simulation.setBackground(new Color(246, 234, 203));
Simulation.setBounds(325,115,1150, 739);

for (int i = 0; i < 200; i++) {                                 //ทำการสร้างปุ่มจำนวน 200 ปุ่มแล้วเพิ่มปุ่มเหล่านั้นลงใน Simulation panel 
    gridButtons[i] = new JButton();
    gridButtons[i].addActionListener(this);                      // เพิ่ม ActionListener ให้กับแต่ละปุ่ม
    gridButtons[i].setBackground(new Color(209, 233, 246));
    Simulation.add(gridButtons[i]);
}

//=======================================================================================================

//Panel ==> mainFrame
add(panelFunction);
add(showButton);
add(showImage);
add(Status);
add(Simulation);
}



//========================================================================================================


//การอ่าน file  และ selete file 
@Override
public void actionPerformed(ActionEvent e) {

    if(e.getSource()==OpenFile_btn) {
        //select file to open
        JFileChooser fileChooser = new JFileChooser();           //เป็นคอมโพเนนต์ JFileChooser ของ Swing ที่ใช้ให้ผู้ใช้เลือกไฟล์จากระบบไฟล์ของเครื่องคอมพิวเตอร์

        int response = fileChooser.showOpenDialog(null);    //แสดงกล่องโต้ตอบเพื่อให้ผู้ใช้เลือกไฟล์ และเก็บผลลัพธ์ของการเลือกไฟล์ไว้ในตัวแปร response
        if (response == JFileChooser.APPROVE_OPTION) {           //ตรวจสอบว่าผู้ใช้เลือกไฟล์และไม่ยกเลิกการเลือก ถ้า response มีค่าเป็น JFileChooser.APPROVE_OPTION หมายความว่าผู้ใช้ได้เลือกไฟล์แล้ว
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            showpath.setText(file.getPath());      
            readPM25Data(file);
        }
    }

    else if (e.getSource() == OK) {
        if (!people.getText().isEmpty() && range_number1.getText().isEmpty() && range_number2.getText().isEmpty()) {
            avgNoRandomPeople();
        } else if (people.getText().isEmpty() && !range_number1.getText().isEmpty() && !range_number2.getText().isEmpty()) {
            avgButRandomPeople();
        } else {
            JOptionPane.showMessageDialog(this, "Error");
            return;
        }
        processPatient();
    }

    else if(e.getSource()==Nutural_Rain) {
        JOptionPane.showMessageDialog(this, "The natural rain has occurred.");
        for (int row = 0; row < pm25Data.length; row++) {
            for (int col = 0; col < pm25Data[row].length; col++) {
                pm25Data[row][col] -= 50;
                if (pm25Data[row][col] < 0) {
                    pm25Data[row][col] = 0;
                }
            }
        }
        processPatient();
        updateButtonColors();
    }

    else if (e.getSource() == Artificial_rain) {
        isArtificialRainMode = true; 
        JOptionPane.showMessageDialog(this, "Please select the area to apply artificial rain.");
    } 

    else {

        // ตรวจสอบว่าเป็นปุ่มใน gridButtons ที่ถูกกด และ แสดงผลคำนวน
        for (int i = 0; i < gridButtons.length; i++) {
            if (e.getSource() == gridButtons[i]) {
                if (isArtificialRainMode) {
                    selectedIndex = i;
                    int row = selectedIndex / 20;
                    int col = selectedIndex % 20;

                    pm25Data[row][col] *= 0.5;

                    for (int j = -1; j <= 1; j++) {
                        for (int k = -1; k <= 1; k++) {
                            int newRow = row + j;
                            int newCol = col + k;

                            if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 20) {
                                if (!(j == 0 && k == 0)) {
                                    pm25Data[newRow][newCol] *= 0.7;
                                }
                            }
                        }
                    }
                    processPatient();
                    updateButtonColors();
                    updateImage();         // เรียกใช้เพื่ออัปเดตภาพที่แสดงใน JLabel
                    isArtificialRainMode = false; 
                } 
                else { 
                    selectedIndex = i;
                    int row = i / 20;
                    int col = i % 20;
                    Dust_Result.setText(String.valueOf(pm25Data[row][col])); //แสดงข้อมูล PM 2.5 ที่ถูกเก็บไว้ในอาร์เรย์ 
                    Population_Result.setText(String.valueOf(populationData[row][col])); //แสดงข้อมูล จำนวนคนที่อยู่ในช่อง
                    Healthy_Result.setText(String.valueOf((int)normalPersonArray[row][col])); //แสดงข้อมูล ยอดคนสุขภาพดี
                    Patient_Result.setText(String.valueOf((int)patientDataArray[row][col])); //แสดงข้อมูล ยอดคนป่วย 
                    PercentPatient_Result.setText(String.valueOf(patientPercentArray[row][col] + " %")); //แสดงข้อมูล เปอร์เซนคนป่วย ที่ถูกเก็บไว้ในอาร์เรย์ 
                
                    updateImage(); // เรียกใช้เพื่ออัปเดตภาพที่แสดงใน JLabel
                }
            }
        }
    }
}

//========================================================================================================


//line ใช้เก็บข้อมูลที่อ่านมาจากแต่ละบรรทัดของไฟล์

  private void readPM25Data(File file) {  
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int row = 0;                           //row ใช้ติดตามแถวที่กำลังอ่านข้อมูลและเก็บลงในอาร์เรย์ pm25Data
        while ((line = br.readLine()) != null && row < 10) {     //ข้อมูล PM 2.5 จะถูกอ่านจากไฟล์และเก็บในอาร์เรย์นี้
            String[] values = line.split("\\s+");  
            for (int col = 0; col < values.length && col < 20; col++) {       //ลูปนี้จะทำงานจนกว่าจะอ่านข้อมูลจากไฟล์ครบทุกบรรทัดหรือจนกว่า row จะถึงค่า 10
                try {
                    int value = Integer.parseInt(values[col]);         //แปลงข้อมูลที่อ่านจากไฟล์ (ซึ่งเป็น String) เป็น Integer แล้วเก็บลงในอาร์เรย์ pm25Data
                    if (value < 0 )
                    {
                        value = 0;
                    }  
                    pm25Data[row][col] =value;
                } catch (NumberFormatException ex) {     // จัดการกรณีที่ข้อมูลไม่ใช่ตัวเลข
                
                    pm25Data[row][col] = 0; 
                    //JOptionPane.showMessageDialog(this,"input data error" + row + ", col " + col + ": " + values[col]);
                }
            }
            row++;
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}


//======================================================================================================================

//คำนวนประชากร

private void avgNoRandomPeople() {
    try {
        int totalPopulation = Integer.parseInt(people.getText());

        for (int row = 0; row < pm25Data.length; row++) {
            for (int col = 0; col < pm25Data[row].length; col++) {
                populationData[row][col] = totalPopulation;
            }
        }
        JOptionPane.showMessageDialog(this, "Average population is " + totalPopulation);
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Please enter number ");
    }
}



 // คำนวนประชากรแบบสุ่ม

 private void avgButRandomPeople() {
    try {
        int minRange = Integer.parseInt(range_number1.getText());
        int maxRange = Integer.parseInt(range_number2.getText());

        if (minRange > maxRange) {
            JOptionPane.showMessageDialog(this, "Please enter minrange < maxrange ");
            return;
        }

        Random random = new Random();
        
        for (int row = 0; row < pm25Data.length; row++) {
            for (int col = 0; col < pm25Data[row].length; col++) {
                populationData[row][col] = minRange + random.nextInt(maxRange - minRange + 1);
                
            }
        }
        JOptionPane.showMessageDialog(this, " random finish ");
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Please enter valid numbers in the range fields.");
    }
}




//คำนวณผู้ป่วย

private static final int[] PM25_THRESHOLDS = {50, 100, 150, 250};
private static final double[] PATIENT_FACTORS = {0.09, 0.19, 0.29, 0.5};

private void processPatient() {
    for (int row = 0; row < pm25Data.length; row++) {
        for (int col = 0; col < pm25Data[row].length; col++) {

            int pm25Value = pm25Data[row][col];
            int populationValue = populationData[row][col];
            int patientData = 0;
            int normalPerson = 0;
            double patientPercent = 0;

            boolean matched = false;
            for (int i = 0; i < PM25_THRESHOLDS.length; i++) {
                if (pm25Value <= PM25_THRESHOLDS[i]) {
                    if (populationValue > 0) {
                        patientData = (int) (populationValue * ((double) pm25Value / PM25_THRESHOLDS[i]) * PATIENT_FACTORS[i]);
                        normalPerson = populationValue - patientData;
                        patientPercent = (double) (patientData * 100) / populationValue;
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched && populationValue > 0) {
                int i = PM25_THRESHOLDS.length - 1;
                patientData = (int) (populationValue * ((double) pm25Value / PM25_THRESHOLDS[i]) * PATIENT_FACTORS[i]);
                normalPerson = populationValue - patientData;
                patientPercent = (double) (patientData * 100) / populationValue;
            }
            if (populationValue <= 0) {
                patientData = 0;
                normalPerson = 0;
                patientPercent = 0;
            }
            
            if (patientData >= 0) {
                patientDataArray[row][col] = patientData;
            } else {
                patientDataArray[row][col] = 0;
            }
            if (normalPerson <= populationValue) {
                normalPersonArray[row][col] = normalPerson;
            } else {
                normalPersonArray[row][col] = populationValue;
            }
            if (patientPercent >= 0) {
                patientPercentArray[row][col] = Double.parseDouble(String.format("%.2f", patientPercent));   // จัดรูปแบบเป็นทศนิยม 2 ตำแหน่ง
            } else {
                patientPercentArray[row][col] = 0;
            }
        
            //Update รูปภาพ
            
        }
    }
    updateButtonColors();
    updateImage();
}



//อัพเดทสีปุ่มด้วยการอิงข้อมูลจาก patientPercentArray
private void updateButtonColors() {
    for (int row = 0; row < patientPercentArray.length; row++) {
        for (int col = 0; col < patientPercentArray[row].length; col++) {
            int data = row * 20 + col;
            double percent = patientPercentArray[row][col];
            if (percent <= 9) {
                gridButtons[data].setBackground(Color.GREEN);
            } else if (percent <= 19) {
                gridButtons[data].setBackground(Color.YELLOW);
            } else if (percent <= 29) {
                gridButtons[data].setBackground(Color.ORANGE);
            } else {
                gridButtons[data].setBackground(Color.RED);
            }
            
        }
    }
    
}
private void updateImage() {
    // ตรวจสอบว่า selectedIndex ถูกตั้งค่าหรือไม่
    
    if (selectedIndex != -1) {
        int row = selectedIndex / 20;
        int col = selectedIndex % 20;
        double percent = patientPercentArray[row][col];

        // เลือกภาพตามเปอร์เซ็นต์
        if (percent <= 9) {
            imageLabel.setIcon(new ImageIcon("images/dust lv.1.jpg"));
        } else if (percent <= 19) {
            imageLabel.setIcon(new ImageIcon("images/dust lv.2.jpg"));
        } else if (percent <= 29) {
            imageLabel.setIcon(new ImageIcon("images/dust lv.3.jpg"));
        } else {
            imageLabel.setIcon(new ImageIcon("images/dust lv.4.jpg"));
        }
        
    }
}


}

//========================================================================================================