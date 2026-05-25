package OOPweek2;

public class Person {
    public String name;
    public char gender;
    private String telno;
    private Person fan;
    private Person gig[] = new Person[2];

    Person (String name)
    {
      this.name = name;
    }

    Person (String name, char gender, String telno)
    {
       this.name=name;
       this.gender=gender;
       this.telno=telno;
    }

    public void setTelno(String telno)
    {
      this.telno=telno;
    }

    public String getPhone(Person requester)
    {
      if (this.gender != requester.gender)
      {
         return this.telno;
      }
      else{
         return "ม่ายบอก"; 
      }
    }

    public String toString()
    {
       return name;
    }

    public void setFan(Person requester)
    {
      if (this.gender != requester.gender)
      {
         if (this.fan == null)
         {
            this.fan=requester;
         }
         else 
         {
            System.out.println("ผมมีแฟนอยู่แล้ว");
         }
      }
      else
      {
         System.out.println("เดี๋ยวฟ้าผ่า!!!!! เป็นแฟนกันไม่ได้นะ");
      }
    }
    public void getFan(Person requester)
    {
      if (this.fan==requester)
      {
         System.out.println("ก็คุณไง");
      }
      else
      {
         System.out.println("ผมโสด");
      }
    }

    public void setgig(Person requester)
    {
        if(gig[0]==null)
        {
         gig[0]=requester;
        }
        else if (gig[1]==null)
        {
         gig[1]=requester;
        }
        else
        {
         System.out.println("2 คน พอแล้ว ดูแลไม่ไหว");
        }
    }

    public void getgig(Person requester)
    {
      if (this.gender == requester.gender)
      {
         System.out.println(gig[0]+"และ"+gig[1]);
      }
      else 
      {
         System.out.println("ไม่มีกิ๊กสักคนเลย");
      }
    }
    
    public void removeGig(Person requester)
    {
      for (int i=0; i<gig.length; i++)
      {
          if (gig[i]==requester)
          {
            gig[i]=null;
          }
      }
    }

    public void removeGig()
    {
      for (int i=0; i<gig.length; i++)
      {      
            gig[i]=null;
      }
    }

   public void getPersoninfo()
   {
      String personGender ="";
      if(this.gender=='M')
      {
         personGender ="Male";
      }
      else
         {
            personGender ="Female";
         }
      System.out.println(" Name : "+this.name+" Gender : "+personGender+" Phone : "+this.telno+" Fan Name : "+this.fan.name+" Gig Name :"+gig[0]+" and "+gig[1]);   
   }
}
