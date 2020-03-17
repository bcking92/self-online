
user = 'ssafy'
title ='title'
body = 'this is body'
image = ''


def postCore(user, title, body, image):
    
    """ implemented required """
    
    return True
    
def post():
    
    if(user == ''):
        print("로그인해 주세요")
        return False
    elif(title == ''):
        print("제목을 입력해 주세요")
        return False
    elif(body == ''):
        print("내용을 입력해 주세요")
        return False
    elif(image == ''):
        print("이미지를 첨부해 주세요")
        return False

    return postCore(user, title, body, image)
    

post()
