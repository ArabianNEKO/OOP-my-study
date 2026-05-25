public class MyDate {
    int day;
    int month;
    int year;
    
    MyDate()
    {
       day=1;
       month=1;
       year=2020;
    }

    MyDate(int Day, int Month ,int Year)
    {
      day=Day;
      month=Month;
      year=Year;
    }

    void setDay(int d)
    {
        day=d;
        if (d>31)
        {
            System.out.println("Error");
            day=0;
        }
    }
    void setMonth(int m)
    {
        month=m;
        if (m>12)
        {
            System.out.println("Error");
            month=0;
        }
    }
    void setYear(int y)
    {
        year=y;
    }

    int getDay()
    {
        return day;
    }
    int getMonth()
    {
        return month;
    }
    int getYear()
    {
        return year;
    }

    void print()
    {
        System.out.println(getDay()+"/"+getMonth()+"/"+getYear());
    }
}
