<!doctype html>
<html ng-app="myapp">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Projects</title>

    <script src="js/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/angular-route.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
   
    <!--Module app-->
    <script>
        var app=angular.module('myapp',['ngRoute']);
        app.config(function($routeProvider){
            $routeProvider.when('/detail-project/:idProject', {
               templateUrl: "templates/DetailProject.jsp" 
            });
            $routeProvider.when('/detail-issue', {
               templateUrl: "templates/DetailIssue.jsp",
               controller: 'detailCtr'
            });
            $routeProvider.otherwise({
               templateUrl: "templates/ProjectList.jsp" 
            });
        });
    </script>
     <script src="directives/menuDirective.js"></script>
    <script src="controllers/loadProjects.js"></script>
    <script src="controllers/projectController.js"></script>
    
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fontawesome-all.min.css">
    <link rel="stylesheet" href="css/jiraextract.css">
</head>
<body>

    <div class="d-flex">
        <!--Menu-->
        <menu-direct></menu-direct>
        <div class="content p-4">
            <div class="alert alert-danger" ng-show="data.error">
                Error ({{data.error.status}}). The project data was not loaded.
                <a href="/Projects.html" class="alert-link">Click here to try again</a>
            </div>
            <div ng-view style="width:100%;"></div>
        </div>
    </div>

</body>
</html>