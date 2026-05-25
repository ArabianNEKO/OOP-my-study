package OOPweek2;

public class DemoPerson {
    public static void main(String[] args) {
        Person dome =new Person("Dome");
        dome.gender='M';
        dome.setTelno("0982254128");
        
        Person ken =new Person("Ken",'M',"0894465730");

        Person ploy =new Person("Ploy");
        ploy.gender='F';
        ploy.setTelno("0874465523");

        Person may =new Person("May",'F',"0915585746");
        Person ann =new Person("Ann",'F',"0814456299");
        Person fon =new Person("Fon",'F',"0887605691");

        System.out.println(dome.getPhone(ploy));

        dome.getFan(ploy);
        dome.setFan(ploy);
        dome.getFan(ploy);

       dome.getFan(may);
       dome.setFan(may);
       dome.setgig(may);

       dome.setgig(ann);
       dome.setgig(fon);

       dome.getgig(ken);
       dome.getgig(fon);

       dome.removeGig(ann);
       dome.getgig(ken);

       dome.getPersoninfo();
    }
}
