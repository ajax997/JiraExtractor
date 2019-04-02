app.controller('ListController', function ($scope) {

    $scope.curPage = 1,
        $scope.itemsPerPage = 3,
        $scope.maxSize = 5;

    $scope.numOfPages = function () {
        return Math.ceil($scope.data.projects.length / $scope.itemsPerPage);

    };

    $scope.$watch('curPage + numPerPage', function () {
        var begin = (($scope.curPage - 1) * $scope.itemsPerPage),
            end = begin + $scope.itemsPerPage;

        $scope.filteredItems = $scope.data.projects.slice(begin, end);
    });
});
