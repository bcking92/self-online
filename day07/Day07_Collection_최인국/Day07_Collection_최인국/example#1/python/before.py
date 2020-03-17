import random


def getFruit():
    
    fruits = ["Mango", "Orange", "Apple", "Banana"]
    
    i = random.randrange(0, 4)
    
    if i == 0:
        return "Mango"
    elif i == 1:
        return "Orange"
    elif i == 2:
        return "Apple"
    elif i == 3:
        return "Banana"

print(getFruit())

