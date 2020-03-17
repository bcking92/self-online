import java.util.*;

public class HelloWorld{

	public static void main(String []args){
         
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
        
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
        
        
		int days = 0;
        
        switch(month){
            
            case 0:
                days = 31;
            case 2:
                days = 31;
            case 4:
                days = 31;
            case 6:
                days = 31;
            case 7:
                days = 31;
            case 9:
                days = 31;
			case 11:
				days = 31;
            case 3:
                days = 30;
            case 5:
                days = 30;
            case 8:
                days = 30;
            case 10:
                days = 30;
            case 1:
                if((year % 4 == 0) && (year % 100 != 0) || (year % 400) == 0)
                    days = 29;
                else
                    days = 28;
        }
            
        
        
        System.out.println(days + " days");
        
        
     }
}