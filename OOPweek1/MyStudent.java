public class MyStudent {
   public static void main(String[] args) {
        Student student1=new Student("Bell","1234",null);
        Student student2=new Student("Kao","1124",student1);

        student1.setClassMate(student2);
        student1.print();
        student2.print();
        
   }
}
