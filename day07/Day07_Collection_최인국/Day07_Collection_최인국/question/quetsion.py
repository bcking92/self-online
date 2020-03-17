import datetime


def getDays(year, month):

    if month == 1:
        return 31
    elif month == 3:
        return 31
    elif month == 5:
        return 31
    elif month == 7:
        return 31
    elif month == 8:
        return 31
    elif month == 10:
        return 31
    elif month == 12:
        return 31
    elif month == 4:
        return 30
    elif month == 6:
        return 30
    elif month == 9:
        return 30
    elif month == 11:
        return 30
    elif month == 2:
        if((year % 4) == 0) and ((year % 100) != 0) or ((year % 400 ) == 0):
            return 29
        else:
            return 28

dt = datetime.datetime.today()

print(getDays(dt.year, dt.month))
