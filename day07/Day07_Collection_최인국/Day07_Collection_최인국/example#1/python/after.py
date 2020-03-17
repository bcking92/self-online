import random


def getFruit():
    
    fruits = ["Mango", "Orange", "Apple", "Banana"]
    
    i = random.randrange(0, len(fruits))
    
    return fruits[i]

print(getFruit())

