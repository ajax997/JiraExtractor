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
            $routeProvider.when('/', {
               templateUrl: "templates/Dashboard.jsp"
            }).when('/projects', {
               templateUrl: "templates/ProjectList.jsp",
               controller: 'issueCtr' 
            }).when('/detail-project/:idProject', {
               templateUrl: "templates/DetailProject.jsp",
               controller: 'detailCtr' 
            }).when('/detail-issue/:idIssue', {
               templateUrl: "templates/DetailIssue.jsp",
               controller: 'issueCtr' 
            }).otherwise({
               templateUrl: "templates/ProjectList.jsp",
                controller: 'mainCtrl'
            });
        });
    </script>
     <script src="directives/menuDirective.js"></script>
     <script src="directives/issueDirective.js"></script>
    
    <script src="controllers/listProjectController.js"></script>
    <script src="controllers/detailProjectController.js"></script>
    <script src="controllers/detailIssueController.js"></script>
    <script src="controllers/dashboardController.js"></script>
    
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fontawesome-all.min.css">
    <link rel="stylesheet" href="css/jiraextract.css">
</head>
<body>

    <div class="d-flex" ng-init="index=0">
        <!--Menu-->
        <menu-direct></menu-direct>
        <div class="content p-4">
            <div ng-view style="width:100%;"></div>
        </div>
    </div>

</body>
</html>