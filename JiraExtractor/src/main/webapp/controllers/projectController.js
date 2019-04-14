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
		    /* for(var i=0 ; i < $scope.data.issues.length ; i++){
			if($scope.data.issues[i].fixVersions === "0"){
				$scope.version[i]={"issue":$scope.data.issues[i],"ver":""};
			}
			else{
				$http.get('/api/versions/'+$scope.data.issues[i].fixVersions).then(function(data1){
		        	$scope.version[i]={"issue":$scope.data.issues[i],"ver":data1.data};
		        },function(error){
		        	console.log(error);
		        });
			}
		}
		console.log($scope.version);*/
    
});