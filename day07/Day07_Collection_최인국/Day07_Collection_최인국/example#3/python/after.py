def forward():
    print('move forward')

def left():
    print('move left')
    
def right():
    print('move right')

def backward():
    print('move backward')


def move(key):
    
    if(key == 'w'):
        forward()
    elif(key == 'a'):
        left()
    elif(key == 'd'):
        right()
    elif(key == 's'):
        backward()
        

key = 's'

move(key)
