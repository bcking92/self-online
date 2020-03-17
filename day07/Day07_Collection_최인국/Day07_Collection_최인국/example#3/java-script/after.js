
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

	const dict = {'w':forward, 's':backward, 'a':left, 'd':right}

	const func = dict[key]

	if(func != null)
		func()
}


const key = 's'

move(key)
