
const user = 'ssafy'
const title = 'this is title'
const body = 'this is body'
const image = ''

function postCore(user, title, body, image){

	//implements required
}

function post(){

	if(user == ''){
		console.log('로그인 해주세요')
		return
	}
	else if(title == ''){
		console.log('제목을 입력해 주세요')
		return
	}
	else if(body == ''){
		console.log('내용을 입력해 주세요')
		return
	}
	else if(image == ''){
		console.log('이미지를 첨부해 주세요')
		return
	}

	return postCore(user, title, body, image)
}

post()

