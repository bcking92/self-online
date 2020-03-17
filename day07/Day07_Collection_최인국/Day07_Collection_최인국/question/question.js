const date = new Date()
const year = date.getFullYear()
const month = date.getMonth()

var days = null

switch(month){

	case 0:
	case 2:
	case 4:
	case 6:
	case 7:
	case 9:
	case 11:
		days = 31;
		break;
	
	case 3:
	case 5:
	case 8:
	case 10:
		days = 30;
		break;
	case 1:
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400) == 0)
			days = 29;
		else
			days = 28;
		break;
}

console.log(days + ' days for ' + year + '-' + (month + 1))
