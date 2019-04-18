app.directive("issueDirect", function(){
    return {
        restrict: "E",
        templateUrl: "templates/issue.jsp",
        replace: true,
        scope:{
        	issueType: '@',
        	issueId: '@',
        	issueKey: '@',
        	issueSummary: '@',
        	issueVersion: '@'
        }
    }
});