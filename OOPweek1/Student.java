public class Student {
    String name;
    String id;
    Student classMate;

    Student(String Name, String Id , Student ClassMate )
    {
       name=Name;
       id=Id;
       classMate=ClassMate;
    }

    void setClassMate(Student sm)
    {
        classMate=sm;
    }

    void print()
    {
        System.out.println(" Name "+name+" ID "+id+" ClassMate "+classMate.name);
    }
}
                                                