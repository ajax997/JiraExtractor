app.directive("menuDirect", function(){
    return {
        restrict: "E",
        templateUrl: "templates/menu.jsp",
        controller: function($scope){
            console.log($scope);
        }
    }
});