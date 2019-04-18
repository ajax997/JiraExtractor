app.controller('detailCtr', function($scope, $http, $routeParams, $q){  
	$scope.data={};
	$scope.displayVersion={};
	$scope.version={};
    console.log($routeParams.idProject);
	/* Get api json of project by id project" */
    $http.get('/api/project/'+ $routeParams.idProject).then(
        function(data){
            $scope.data.project = data.data;
            
        },
        function(error){
        	console.log(error);
        }
    );
    
    /* Get api json of version by id project" */
    $http.get('/api/'+ $routeParams.idProject+'/versions').then(
        function(data){
            $scope.data.version = data.data;
            console.log($scope.data);
        },
        function(error){
        	console.log(error);
        }
    );
    
    /* Get api json of project by id project" */
    /*$http.get('/api/'+ $routeParams.idProject+'/issue').then(
        function(data){
           $scope.data.issues = data.data;
           console.log($scope.data.issues);
        },
        function(error){
        	console.log(error);
        }
    );*/

    let promise = new Promise((resolve, reject) => {
        $http({
            method: 'GET',
            url: '/api/'+ $routeParams.idProject+'/issue'
        }).then(
            value => {
                resolve(value);
            },
            reason => {
                reject(reason);
            }
        );
    });
    
    promise.then(
        value => {
            $scope.data.issues = value.data;
            $scope.$apply();
        },
        reason => {
            $scope.data.error = reason;
            $scope.$apply();
        }
    );
    
    /*Get data sprint*/ 
    $http.get('/api/'+$routeParams.idProject+'/sprints').then(
            function(data){
                $scope.data.sprints = data.data;
                console.log($scope);
            },
            function(error){
                $scope.data.error = error;
            }
        );
    
	/*Handle click filter version */
    $scope.modelVersion= false;
    $scope.versionFunction= function(value){
    	if(value == -1){
    		$scope.modelVersion= false;
    		$scope.displayVersion={};
    	}else{
    		$scope.modelVersion=true;
    		$scope.displayVersion = value;
    		console.log($scope.displayVersion);
    	}
    }
    
});