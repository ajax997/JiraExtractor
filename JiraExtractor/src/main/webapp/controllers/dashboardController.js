app.controller('doashboardCtrl', ['$scope', '$http',function($scope, $http){
	$scope.data = {};
   $http.get('api/user/current').then(
       function(data){
           $scope.data.user = data.data;
           console.log($scope.data.user);
       },
       function(error){
           $scope.data.error = error;
       }
   );
   $http.get('/api/issues').then(
	       function(data){
	           $scope.data.issues = data.data;
	           console.log($scope.data.issues);
	       },
	       function(error){
	           $scope.data.error = error;
	       }
	   );
}]);