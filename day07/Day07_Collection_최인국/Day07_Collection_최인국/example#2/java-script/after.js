
const user = 'ssafy'
const title = 'this is title'
const body = 'this is body'
const image = ''

function postCore(user, title, body, image){

	//implements required
}

function post(){

	const checkList = [
		[user, '로그인 해주세요'],
		[title, '제목을 입력해 주세요'],
		[body, '내용을 입력해 주세요'],
		[image, '이미지를 첨부해 주세요']]
	

	for(const v of checkList){
		if(v[0] == ''){
			console.log(v[1])
			return
		}
	}

	return postCore(user, title, body, image)
}

post()

