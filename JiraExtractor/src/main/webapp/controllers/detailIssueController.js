app.controller('issueCtr', function($scope, $http, $routeParams ){  
	$scope.data={};
    console.log($routeParams.idIssue);
    
    $http.get('/api/issue/'+$routeParams.idIssue).then(
            function(data){
                $scope.data.issue = data.data;
                /* Get version of this issue */
                $http.get('/api/versions/'+$scope.data.issue[0].fixVersions).then(
                    function(data){
                        $scope.data.version = data.data;
                    },
                    function(error){
                    	console.log(error);
                    }
                );
                /*Get data sprint of this issue*/ 
                $http.get('/api/sprint/'+$scope.data.issue[0].sprintID).then(
                        function(data){
                            $scope.data.sprint = data.data;
                        },
                        function(error){
                            $scope.data.error = error;
                        }
                    );
                /* Get all issue to find sub-task of this issue*/
                $http.get('/api/'+$scope.data.issue[0].project.id+'/issue').then(
                    function(data){
                        $scope.data.issues = data.data;
                        
                    },
                    function(error){
                    	console.log(error);
                    }
                );
                console.log($scope.data);
            },
            function(error){
                $scope.data.error = error;
            }
        );
});