function selectRow(obj) {
	var row = obj.parentNode;
	var parent = obj.parentNode.parentNode;
	for ( var x = 0; parent.childNodes.length; x++) {
		if (row == parent.childNodes[x]) {
			parent.childNodes[x].style.backgroundColor = '#F3E8C9';
		} else {
			parent.childNodes[x].style.backgroundColor = '#FFFFFF';
		}
	}
}