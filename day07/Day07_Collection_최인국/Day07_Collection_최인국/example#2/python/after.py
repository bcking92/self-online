
user = 'ssafy'
title ='title'
body = 'this is body'
image = ''


def postCore(user, title, body, image):
    
    """ implemented required """
    
    return True
    
def post():
    
    checkList = [[user, "로그인해 주세요"], [title, "제목을 입력해 주세요"], [body, "내용을 입력해 주세요"], [image, "이미지를 첨부해 주세요"]]
    
    for i in checkList:
        if(i[0] == ''):
            print(i[1])
            return False
            
    return postCore(user, title, body, image)
    

post()
