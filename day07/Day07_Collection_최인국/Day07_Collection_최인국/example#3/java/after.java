import java.util.HashMap;
import java.lang.reflect.*;



public class Control
{
    
	private HashMap<Character, Method> dict = new HashMap<Character, Method>();
    
    
	public Control(){
        
		try{
			Class cls = Class.forName("Control");
	    
			dict.put('w', cls.getMethod("forward"));
			dict.put('d', cls.getMethod("backward"));
			dict.put('a', cls.getMethod("left"));
			dict.put('d', cls.getMethod("right"));
		}
		catch(Throwable e){
            
		}
	}
    
    
	public void forward() {

		System.out.println("move forward");
	}
	
	public void backward() {

		System.out.println("move backward");
	}
	
	public void left() {

		System.out.println("move left");
	}
	
	public void right() {

		System.out.println("move right");
	}


	public void move(char key) {
	    
		Method method = this.dict.get(key);
	    
		if(method == null)
			return;
	    
		try{
			method.invoke(this);
		}
		catch(Throwable e){
            
		}
	}


	public static void main(String[] args) {
					     
		Control Control = new Control();
		
		Control.move('w');
		Control.move('a');
	}
}
