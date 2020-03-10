import requests
import json

def ssafy_req(BASE_URL='http://13.125.222.176/quiz/', name='서울 3반 김병철'):
    BASE_URL = BASE_URL
    headers = {
        'Accept' : 'application/json',
        'Content-Type' : 'application/json'
    }
    NEXT_URL = 'alpha'
    result = []
    print('Q1: 2기 여러분들이 싸피와 함께한지도 벌써 9개월이 되었는데요. 싸피 2기의 입학식은 7월 O일이었습니다. 여기서 O에 들어갈 숫자는?')
    while True:
        i = input('정답: ')

        data = json.dumps({
            'nickname' : name,
            'yourAnswer' : i,
        })

        res = requests.post(BASE_URL + NEXT_URL, headers=headers, data=data)
        resJson = res.json()
        if resJson.get('code') == 600:
            print('Wrong asnwer.')
        elif resJson.get('code') == 400:
            print('JSON parsing error')
        elif resJson.get('code') == 403:
            print('No nickname')
        elif resJson.get('code') == 200:
            result.append(resJson)
            print(resJson.get('question'))
            NEXT_URL = resJson.get('nextUrl')
        else:
            print('알수없는 error')
            break

        if resJson.get('nextUrl') == '수고하셨습니다.':
            print('끝')
            break
    with open('response.json', 'w', encoding='utf-8') as f:
        json.dump(result, f, ensure_ascii=False)


if __name__ == '__main__':
    ssafy_req()