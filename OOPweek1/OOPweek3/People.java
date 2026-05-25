package OOPweek3;
//ข้อ 1
class People {
    public String name;
    public char gender;
    private String workspace;

    People(String name,char gender)
    {
        this.name=name;
        this.gender=gender;
    }
    People(String name,char gender,String workspace)
    {
        this(name,gender);
        this.workspace=workspace;
    }
    
    public String getWorkspace()
    {
        return this.workspace;
    }

    public String toString()
   {
    return this.name;
   }


}
//ข้อ 2
   class Children extends People implements GoodChild{
   private People father;
   private People mother;
   private String school;

   Children(String name,char gender ,People father, People mother)
   {
     super(name, gender);
     this.father=father;
     this.mother=mother;
   }

   Children(String name,char gender ,String school,People father, People mother)
   {
    this(name,gender,father,mother);
    this.school=school;
   }

   public String toString()
   {
    if (this.gender=='M')
    {
        return this.name +" (Boy)";
    }
    else
    {
        return this.name +" (Girl)";
    }
   }

   public People getFather()
   {
     return father;
   }
   public People getMother()
   {
     return mother;
   }

   public String getWorkspace()
    {
        return " ยังทำงานไม่ได้ ตอนนี้เรียนอยู๋ที่ : "+this.school;
    }

    public boolean equals(People mother)
    {
      if(this.mother==mother)
      {
        return true;
      }
      else{
        return false;
      }
    }

    @Override
    public String respectTo(People people) {
      String who= " ";
      if(this.father == people)
      {
         who = "Father";
      }
      else if (this.mother == people)
      {
        who = "Mother";
      }
      
      if (this.gender == 'M')
      {
        return " สวัสดีครับ "+who;
      }
      else 
      {
        return " สวัสดีค่ะ "+who;
      }
      
    }
}

interface GoodChild 
{
  String respectTo (People peoele);
}



