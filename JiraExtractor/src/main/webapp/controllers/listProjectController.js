app.controller('mainCtrl', function($scope, $http, $location){
    $scope.data = {};
    
     /*Get api json save to "$scope.data.project" */
    $http.get('/api/projects').then(
        function(data){
            $scope.data.projects = data.data;
        },
        function(error){
            $scope.data.error = error;
        }
    );
});
