document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.modal');
	var instances = M.Modal.init(elems, 5);
	var url = '';
	console.log(url)
	$('a[id*="btn_"]').click(function() {
		url = "http://localhost:8000/" + $(this).attr('id').split("_")[1];
	});
	console.log(url)
	$('#ok_confirm').click(function() {
		document.location.href = url;
	});
});
