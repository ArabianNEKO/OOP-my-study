public class MyMain {
    public static void main(String[] args) {
        MyDate date1=new MyDate();
        MyDate data2=new MyDate(5,7,2021);

        date1.setDay(8);
        date1.setMonth(1);
        date1.setYear(2021);
        date1.print();

        date1.setDay(29);
        date1.setMonth(2);
        date1.setYear(2021);
        date1.print();

        data2.print();
    }
}
