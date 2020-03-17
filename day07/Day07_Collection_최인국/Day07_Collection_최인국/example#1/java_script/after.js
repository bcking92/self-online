function getRandomInt(min, max) {
		
	min = Math.ceil(min);
	max = Math.floor(max);
	return Math.floor(Math.random() * (max - min + 1)) + min;
}


function getFruit(){

	const fruits = ['Mango', 'Apple', 'Orange', 'Banana']

	const i = getRandomInt(0, fruits.length)

	return fruits[i]

}


console.log(getFruit())
