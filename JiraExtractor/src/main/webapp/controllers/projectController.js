app.controller('detailCtr', function($scope, $http, $routeParams ){
    //$scope.data.id = $routeParams.idProject;
    
    
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
});