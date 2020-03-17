import java.util.Random;


public class Main
{
	private String user = "ssafy";
	private String title = "this is title";
	private String body = "this is body";
	private String image = "";
    
	public boolean post() {
	    
		if(this.user == ""){
			System.out.println("로그인해 주세요");
			return false;
		}
		else if(this.title == ""){
			System.out.println("제목을 입력해 주세요");
			return false;
		}
		else if(this.body == ""){
			System.out.println("내용을 입력해 주세요");
			return false;
		}
		else if(this.image == ""){
			System.out.println("이미지를 첨부해 주세요");
			return false;
		}
        
		return postCore(this.user, this.title, this.body, this.image);
	}

	private boolean postCore(String user, String title, String body, String image){
    
		return true;
		//implements required
	}


	public static void main(String[] args) {
	    
		new Main().post();	   		
	}
}
