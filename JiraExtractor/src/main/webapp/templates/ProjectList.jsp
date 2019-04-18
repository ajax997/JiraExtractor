<div ng-controller="mainCtrl">
<h2 class="mb-4">Projects</h2>
<div class="card">
    <!-- <div class="card-header bg-white font-weight-bold">
        Recent Orders
    </div> -->
    <div class="card-body" >
        <div class="col-lg-4 col-lg-offset-4">
            <!-- Actual search box -->
            <div class="form-group has-search">
                <span class=" form-control-feedback"><i class="fas fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search" ng-model="searchText.name">
            </div>
        </div>
        <table class="table table-hover" ng-hide="data.error">
            <thead>
                <tr>
                    <th scope="col">Avatar</th>
                    <th scope="col">Name</th>
                    <th scope="col">Key</th>
                    <th scope="col">Project Typekey</th>
                    <th scope="col">IsPrivate</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="item in data.projects | filter:searchText">
                    <td><img src={{item.avatarUrl}} weight="50px" height="50px" /> </td>
                    <td id={{item.id}} ><a href={{"#!detail-project/"+item.id}}>{{item.name}}</a></td>
                    <th>{{item.key}}</th>
                    <td>{{item.projectType}}</td>
                    <td><span class="badge badge-success" ng-if="item.isPrivate">true</span>
                        <span class="badge badge-secondary" ng-if="!item.isPrivate">false</span>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</div>