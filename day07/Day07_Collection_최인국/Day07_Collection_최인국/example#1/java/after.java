import java.util.Random;

public class Main
{
    
	public static String getFruit() {
        
		String fruits[] = {"Mango", "Apple", "Orange", "Banana"};
        
		int random = (new Random()).nextInt() & Integer.MAX_VALUE;
        
		int i = random % fruits.length;
        
		return fruits[i];        
	}
    
	public static void main(String[] args) {
	    
	        System.out.println(getFruit());
	}
}
