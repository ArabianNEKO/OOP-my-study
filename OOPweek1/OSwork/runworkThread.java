package OSwork;
//66011212225 นายสิทธิชัย ศรีนัคเรศ Sec.1//

public class runworkThread {
    public static void main(String[] args) {
        new printA_Z().start();
        new PrintNumbers().start();
        new PrintRandomNumbers().start();
    }
    //อยาก Output เป็นแนวตั่ง ไปแก้ตรง print นะครับ
}
