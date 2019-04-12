app.controller('detailCtr', function($scope, $http, $routeParams ){  
	$scope.data={};
    console.log($routeParams.idProject);
    
    /* Get api json save to "$scope.data.project" */
    $http.get('/api/project/'+ $routeParams.idProject).then(
        function(data){
            $scope.data.project = data.data;
        },
        function(error){
        	console.log(error);
        }
    );

    $http.get('/api/'+$routeParams.idProject+'/sprints').then(
            function(data){
                $scope.data.sprints = data.data;
                console.log($scope);
            },
            function(error){
                $scope.data.error = error;
            }
        );
});