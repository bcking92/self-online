def forward():
    print('move forward')

def left():
    print('move left')
    
def right():
    print('move right')

def backward():
    print('move backward')


def move(key):
    
    dict = {'w':forward, 'a':left, 'd':right, 's':backward}
    
    func = dict.get(key)
    
    if func is not None:
        func()
    

key = 'w'

move(key)
