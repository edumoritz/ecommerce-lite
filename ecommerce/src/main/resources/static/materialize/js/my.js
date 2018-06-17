window.onhashchange = function(e) {
	const oldURL = e.oldURL.split('#')[1]
	const newURL = e.newURL.split('#')[1]
	console.log(oldURL, newURL)
	const oldMenu = document.querySelector(`.menu a[id='#${oldURL}']`)
	const newMenu = document.querySelector(`.menu a[id='#${newURL}']`)
	oldMenu && oldMenu.classList.remove('selected')
	newMenu && newMenu.classList.add('selected')
	
}


