package OOPweek3;

//ข้อ 3 
class Family {
    public static void main(String[] args) {
        People beckham = new People("Beckham",'M',"Office");
        People victoria = new People("Victoria",'F',"Home");

        Children blooklyn = new Children("Blooklyn",'M',"msu",beckham,victoria);
        Children harper = new Children("Harper",'F',"msu",beckham,victoria);

        System.out.println(" My Name is "+blooklyn+" and this is "+harper);
        System.out.println(" My Dad is "+blooklyn.getFather()+" and Mother is "+blooklyn.getMother());

        System.out.println(" My Workspace is "+beckham.getWorkspace());

        System.out.println(blooklyn.getWorkspace());

        if(harper.equals(victoria)==true)
        {
            System.out.println(" รักแม่ที่สุดเลย สุขสันวันแม่ครับ ");
        }
        else
        {
            System.out.println(" ไม่ใช่ผม ");
        }
        
        System.out.println(blooklyn.respectTo(beckham));
        System.out.println(harper.respectTo(victoria));
    }
   }
