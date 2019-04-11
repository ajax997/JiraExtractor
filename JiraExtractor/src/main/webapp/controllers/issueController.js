app.controller('issueCtr', function($scope, $http, $routeParams ){  
	$scope.data={};
    console.log($routeParams.idIssue);
    
   /* $http.get('/api/issue/'+$routeParams.idIssue).then(
            function(data){
                $scope.data.issue = data.data;
                console.log($scope);
            },
            function(error){
                $scope.data.error = error;
            }
        );*/
});