app.controller('detailCtr', function($scope, $http, $routeParams, $q){  
	$scope.data={};
	$scope.displayVersion={};
	$scope.versionName = "";
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
    $http.get('/api/'+ $routeParams.idProject+'/issue').then(
        function(data){
            $scope.data.issues = data.data;
            console.log($scope.data.issues);
            for(var i=0 ; i < data.data.length ; i++){
            	console.log($scope.data.issues[i].id);
            	if($scope.data.issues[i].fixVersions !== "0"){
            		$scope.data.issues[i].ver ={};
            		$http.get('/api/versions/'+$scope.data.issues[i].fixVersions).then(
                        function(data){
                        	$scope.data.issues[i].ver = data.data ;
                        	
                        },
                        function(error){
                        	console.log(error);
                        }
                    );
            	}
            }
            console.log($scope.data.issues);
        },
        function(error){
        	console.log(error);
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
    
    /*$scope.getVersion= function(idVer){
    	$scope.versionName = {};
    	  Get version of this issue 
        $http.get('/api/versions/'+idVer).then(
            function(data){
            	$scope.versionName = data.data;
            	console.log($scope.versionName);
            },
            function(error){
            	console.log(error);
            }
        );
    }*/
    
});