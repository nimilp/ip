MyIP = function(whatismyip){
	var header = $('#whatismyipHeader');
	var clas = 'success';
	if(!whatismyip.ipAddress){
		clas='danger';
	}
	header.addClass(clas);
	var td = $('#whatismyip >td');
	td[0].append(whatismyip.ipAddress);
	td[1].append(whatismyip.lastUpdate);
	td[2].append(whatismyip.ipChanged);
}