<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/login.css"/>
  <script src="js/angular.min.js"></script> 
</head>
<body ng-app="">
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">SIGN IN</h5>
            <form name="form" class="form-signin" method="POST" action="/login" >
                
              <div class="form-label-group">
                <label for="inputLink">Link Jira</label>
                <input type="url" id="inputLink" name="link" ng-model="link" class="form-control" placeholder="Enter link" required autofocus >
                <div style="color:red">
                    <span class="error" ng-show="form.link.$error.url">
                      Not valid url!</span>
                  </div>
              </div>
                
              <div class="form-label-group">
                <label for="inputEmail">Email</label>
                <input type="email" id="inputEmail" name="email" ng-model="email" class="form-control" placeholder="Enter Email" required>
                <div style="color:red">
                    <span class="error" ng-show="form.email.$error.email">
                      Not valid email!</span>
                  </div>
              </div>

              <div class="form-label-group">
                <label for="inputPassword">API token</label>
                <input type="password" id="inputPassword" name="token" ng-model="pass" class="form-control" placeholder="Enter api token" required>
              </div>

              <hr class="my-4">
              <button class="btn btn-lg btn-primary btn-block" type="submit">SIGN IN</button>
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>