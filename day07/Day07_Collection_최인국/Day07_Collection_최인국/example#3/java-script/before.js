
function forward(){

	console.log('move forward')
}

function backward(){

	console.log('move backward')
}

function left(){

	console.log('move left')
}

function right(){

	console.log('move right')
}


function move(key){

	if(key == 'w')
		forward()
	else if(key == 's')
		backward()
	else if(key == 'a')
		left()
	else if(key == 'd')
		right()
}


const key = 'd'

move(key)
