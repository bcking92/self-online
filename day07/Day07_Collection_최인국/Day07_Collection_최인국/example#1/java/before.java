import java.util.Random;

public class Main
{
    
	public static String getFruit() {
        
		int random = (new Random()).nextInt() & Integer.MAX_VALUE;
        
		int i = random % 4;
        
		switch(i) {
            
			case 0:
				return "Mango";
			case 1:
				return "Apple";
			case 2:
				return "Orange";
			case 3:
				return "Banana";
			default:
				return null;
		}
	}
    
	public static void main(String[] args) {
	    
		System.out.println(getFruit());
	}
}
