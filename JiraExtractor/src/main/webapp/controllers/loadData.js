app.controller('mainCtrl', function($scope, $http){
    $scope.data = {};
     /*Get api json save to "$scope.data.project" */
    $http.get('http://localhost:8080/api/projects').then(
        function(data){
            $scope.data.projects = data.data;
        },
        function(error){
            $scope.data.error = error;
        }
    );
});
