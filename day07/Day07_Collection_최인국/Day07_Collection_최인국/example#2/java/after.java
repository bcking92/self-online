public class Main
{
	private String user = "ssafy";
	private String title = "this is title";
	private String body = "this is body";
	private String image = "";
    
	public boolean post() {
	    
		String checkList[][] = {{this.user, "로그인해 주세요"}, {this.title, "제목을 입력해 주세요"}, {this.body, "내용을 입력해 주세요"}, {this.image, "이미지를 첨부해 주세요"}};
	    
		for (String i [] : checkList) {
	        
			if(i[0] == "") {
	            
				System.out.println(i[1]);
				return false;
			}
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
