<style>
    .card{
        box-shadow: 1px 1px 3px #dadada;
    }
</style>

<h2 class="mb-4">Dashboard <form action="/import" method="get" style=" float: right;"><button class="btn btn-primary" type="submit"><i class="fas fa-download"></i> Import Data</button></form></h2>

<div class="row mt-4" ng-controller="doashboardCtrl">
    <div class="col-sm-6 ">
        <div class="card">
            <div class="card-header">Introduction</div>
            <div class="card-body">
                <h5 class="card-title">WELCOME TO JIRA-EXTRACT</h5>
                <div class="card-text">
                    <img class="avatar-project" src={{data.user.avatarUrls}}
                        width="30px" /> {{data.user.displayName}}
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="card">
            <div class="card-header">Assigned to Me</div>
            <div class="card-body">
                <table class="table card-text">
                    <thead>
                        <tr>
                            <th scope="col">type</th>
                            <th scope="col">key</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="issue in data.issues" ng-if="issue.assignee.accountId===data.user.accountId">
                            <td><img class="avatar-project" src={{issue.issueType.iconUrl}}
                        width="30px" /></td>
                            <td><a href={{'#!detail-issue/'+issue.id}}>{{issue.key}} | {{issue.summary}}</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
</div>

			