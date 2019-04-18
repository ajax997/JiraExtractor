app.controller('doashboardCtrl', ['$scope', '$http','APIService', function($scope, $http, APIService){
	$scope.data = {};
	$scope.num = 0;
	
	APIService.getUserCurrent().then(function(data){
		$scope.data.user =  data;
	});
	
	APIService.getAllIsssue().then(function(data){
		$scope.data.issues =  data;
	});
}]);