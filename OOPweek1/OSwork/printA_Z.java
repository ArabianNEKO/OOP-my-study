package OSwork;
//66011212225 นายสิทธิชัย ศรีนัคเรศ Sec.1//

public class printA_Z extends Thread {
    public void run() {
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.print(c + " ");
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException  e) {}
        }
        System.out.print(" It done A-Z ");
    }
}

class PrintNumbers extends Thread {
    public void run() {
        for (int i = 1; i <= 24; i++) {
            System.out.print(i + " ");
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException  e) {}
        }
        System.out.print(" It done Numeber ");
    }
}

class PrintRandomNumbers extends Thread {
    public void run() {
        java.util.Random randomnum = new java.util.Random();
        for (int i = 0; i < 20; i++) {
            System.out.print((randomnum.nextInt(9) + 1) + " ");
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException  e) {}
        }
        System.out.print(" It done Random ");
    }
}
