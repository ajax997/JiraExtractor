app.controller('detailCtr', function($scope, $http, $routeParams, APIService){  
	$scope.data={};
	$scope.displayVersion={};
	$scope.idVerFil = -1;
	$scope.version={};
    console.log($routeParams.idProject);
	/* Get api json of project by id project" */
    APIService.getProjectById($routeParams.idProject).then(function(data){

    	console.log(data);
		$scope.data.project =  data;
	});
    
    /* Get api json of version by id project" */
    APIService.getVersionByIdProject($routeParams.idProject).then(function(data){
		$scope.data.version =  data;
	});
    
    /* Get api json of issue by id project" */
    APIService.getIssueByIdProject($routeParams.idProject).then(function(data){
		$scope.data.issues =  data;
	});

    
    /*Get data sprint*/
    APIService.getSprintByIdProject($routeParams.idProject).then(function(data){
		$scope.data.sprints =  data;
	});
    
	/*Handle click filter version */
    $scope.modelVersion= false;
    $scope.versionFunction= function(value){
    	if(value == -1){
    		$scope.modelVersion= false;
    		$scope.idVerFil = -1;
    		$scope.displayVersion={};
    	}else{
    		$scope.modelVersion=true;
    		$scope.displayVersion = value;
    		$scope.idVerFil = $scope.displayVersion.id;
    		console.log($scope.displayVersion);
    	}
    }
});