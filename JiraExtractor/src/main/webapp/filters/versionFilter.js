app.filter('versionFilter', function () {
	return function (items, idVer) {
		var filtered = [];
		if(idVer != -1){
			angular.forEach(items, function(item, key) {
				if (item.version.id === idVer) {
					filtered.push(item);
				}
			});
		}else{
			filtered = items;
		}
		return filtered;
	};
});