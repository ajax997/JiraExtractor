app.controller('mainCtrl', function($scope, $http, APIService){
    $scope.data = {};
    
     /*Get api json save to "$scope.data.project" */
    APIService.getAllProject().then(function(data){
		$scope.data.projects =  data;
	});
});
