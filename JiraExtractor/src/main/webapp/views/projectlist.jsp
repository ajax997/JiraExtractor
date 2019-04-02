<div ng-controller="ListController">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-8">
            <h3>PROJECTS</h3>
        </div>
        <div class="col-lg-4 col-lg-offset-4">
            <!-- Actual search box -->
            <div class="form-group has-search">
                <span class=" form-control-feedback"><i class="fas fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search" ng-model="searchText.name">
            </div>
        </div>
    </div>
    <div class="panel row" ng-hide="data.error">
        <!--Get data from object data.project -->
        <table class="table .table-success table-hover">
            <thead>
                <tr>
                    <th scope="col" class="checkboxFive"> <input type="checkbox" value="1" id="checkboxFiveInput" ng-model='checkall' /></th>
                    <th scope="col">id</th>
                    <th scope="col">Avatar</th>
                    <th scope="col">Name</th>
                    <!--<th scope="col">Key</th>-->
                    <th scope="col">Project Type</th>
                    <!--<th scope="col">Style</th>-->
                    <th scope="col">IsPrivate</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="item in data.projects | filter:searchText:strict">
                    <th scope="row" class="checkboxFive"> <input type="checkbox" value="1" ng-checked="checkall" /></th>
                    <th>{{item.id}}</th>
                    <td><img src={{item.avatarUrl}} weight="50px" height="50px" /> </td>
                    <td><a href={{item.url}}>{{item.name}}</a></td>
                    <!--<th>{{item.key}}</th>-->
                    <td>{{item.projectType}}</td>
                    <!--<td>{{item.style}}</td>-->
                    <td>{{item.isPrivate}}</td>
                </tr>
            </tbody>
            
        </table>
        <div class="row importData">
            <!--<span>{{countItem}} project(s) </span>-->
            <button type="button" class="btn btn-outline-primary"> <i class="fas fa-file-export"></i> Export</button>
        </div>
    </div>
</div>
