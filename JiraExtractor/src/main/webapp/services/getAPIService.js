app.service('APIService', function($http) {
	
    this.getUserCurrent = function() {
    	var data = {};
    	data = $http.get('api/user/current').then(
	       function(data){
	           return data.data;
	       },
	       function(error){
	           return error;
	       }
	   );
    	return data;
    }
    this.getAllIsssue = function() {
    	var data = {};
    	data = $http.get('/api/issues').then(
	       function(data){
	           return data.data;
	       },
	       function(error){
	           return error;
	       }
	   );
    	return data;
    }
    
    this.getAllProject = function() {
    	var data = {};
    	data = $http.get('/api/projects').then(
	        function(data){
	            return data.data;
	        },
	        function(error){
	            return error;
	        }
	    );
    	return data;
    }
    
    this.getProjectById = function(idProject) {
    	var data = {};
    	data = $http.get('/api/project/'+ idProject).then(
	        function(data){
	        	
	            return data.data;
	        },
	        function(error){
	        	return error;
	        }
	    );
    	return data;
    }
    
    this.getIssueById = function(idIssue) {
    	var data = {};
    	data = $http.get('/api/issue/'+idIssue).then(
	        function(data){
	            return data.data;
	        },
	        function(error){
	            return error;
	        }
	    );
    	return data;
    }
    
    this.getVersionById = function(idVer) {
    	var data = {};
    	data = $http.get('/api/versions/'+idVer).then(
            function(data){
                return data.data;
            },
            function(error){
            	return error;
            }
        );
    	return data;
    }
    
    this.getVersionByIdProject = function(idProject) {
    	var data = {};
    	data = $http.get('/api/'+ idProject+'/versions').then(
	        function(data){
	            return data.data;
	        },
	        function(error){
	        	return error;
	        }
	    );
    	return data;
    }
    
    this.getSprintByIdProject = function(idProject) {
    	var data = {};
    	data = $http.get('/api/'+idProject+'/sprints').then(
            function(data){
                return data.data;
            },
            function(error){
                return error;
            }
        );
    	return data;
    }
    
    this.getSprintById = function(idSprint) {
    	var data = {};
    	data = $http.get('/api/sprint/'+idSprint).then(
            function(data){
                return data.data;
            },
            function(error){
                return error;
            }
        );
    	return data;
    }
    
    this.getIssueByIdProject = function(idProject) {
    	var data = {};
    	data = $http.get('/api/'+idProject+'/issue').then(
	            function(data){
	                return data.data;
	                
	            },
	            function(error){
	            	return error;
	            }
	        );
    	return data;
    }
 });