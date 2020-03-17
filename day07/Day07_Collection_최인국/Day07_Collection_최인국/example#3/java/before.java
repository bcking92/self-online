public class Control
{
    
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
	    
	    
		if(key == 'w'){
			forward();
	    }
		else if(key == 's'){
			backward();
		}
		else if(key == 'a'){
			left();
		}
		else if(key == 'd'){
			right();
		}
	}


	public static void main(String[] args) {
		
        Control Control = new Control();
	        
        Control.move('w');
        Control.move('a');
	}
}
