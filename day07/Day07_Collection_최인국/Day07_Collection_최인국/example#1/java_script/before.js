function getRandomInt(min, max) {
		
	min = Math.ceil(min);
	max = Math.floor(max);
	return Math.floor(Math.random() * (max - min + 1)) + min;
}


function getFruit(){
	
	const i = getRandomInt(0, 3)


	switch(i){

		case 0:
			return 'Mango'
		case 1:
			return 'Apple'
		case 2:
			return 'Orange'
		case 3:
			return 'Banana'
	}

}


console.log(getFruit())
