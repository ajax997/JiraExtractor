app.controller('detailCtr', function($scope, $http, $routeParams ){  
	/*Handle click filter version */
    $scope.modelVersion= false;
    $scope.versionFunction= function(value){
    	if(value == -1){
    		$scope.modelVersion= false;
    	}else{
    		$scope.modelVersion=true;
    	}
    }
    /*Handle click filter epic */
    $scope.modelEpic= false;
    $scope.epicFunction= function(value){
    	if(value == -1){
    		$scope.modelEpic= false;
    	}else{
    		$scope.modelEpic=true;
    	}
    }
    
    /*Get data project by id from routeParams*/ 
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