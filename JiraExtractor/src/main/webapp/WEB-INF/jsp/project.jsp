<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html ng-app="myapp">

<head>
    <title>Extract</title>
    <script src="lib/angular.js"></script>
    <link rel="stylesheet" href="lib/bootstrap.min.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    
    <!--Khai báo modul app rồi! sd thêm 1 modul khác-->
    <script>
        var app=angular.module('myapp',['ngRoute']);
        app.config(function($routeProvider){
            $routeProvider.when('/checkout', {
               templateUrl: "/login" 
            });
            $routeProvider.when('/projects', {
               templateUrl: "views/projectlist.jsp" 
            });
            $routeProvider.otherwise({
               templateUrl: "views/projectlist.jsp" 
            });
        });
    </script>
    <script src="controllers/loadData.js"></script>
    <script src="controllers/pagination.js"></script>
    <script src="directives/menuDirective.js"></script>
    <script src="lib/angular-route.js"></script>
    
    <script src="lib/jquery-3.3.1.min.js" ></script>
    <script src="lib/bootstrap.min.js" ></script>
    
</head>

<body ng-controller="mainCtrl">
    
    <menu-direct></menu-direct>
    
    <div class="container" style="margin-top: 20px;">
        <div class="row">
            <div class="alert alert-danger" ng-show="data.error">
                Error ({{data.error.status}}). The project data was not loaded.
                <a href="#" class="alert-link">Click here to try again</a>
            </div>

            <div ng-view style="width:100%;"></div>
        </div>
    </div>
</body>

</html>