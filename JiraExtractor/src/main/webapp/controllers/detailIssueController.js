app.controller('issueCtr', function($scope, $http, $routeParams, APIService ){  
	$scope.data={};
    console.log($routeParams.idIssue);
    if(!angular.isUndefined($routeParams.idIssue)){
	    APIService.getIssueById($routeParams.idIssue).then(function(data){
			$scope.data.issue =  data;
			/* Get version of this issue */
			APIService.getVersionById($scope.data.issue[0].fixVersions).then(function(data){
				$scope.data.version =  data;
			});
	        /*Get data sprint of this issue*/ 
			APIService.getSprintById($scope.data.issue[0].sprintID).then(function(data){
				$scope.data.sprint =  data;
			});
	        
	        /* Get all issue to find sub-task of this issue*/
			APIService.getIssueByIdProject($scope.data.issue[0].project.id).then(function(data){
				$scope.data.issues =  data;
			});
	        console.log($scope.data);
		});
    }
    
});