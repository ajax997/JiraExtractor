<link rel="stylesheet" href="css/detailproject.css">

<div ng-controller="detailCtr">
<ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="Projects.html">Projects</a></li>
    <li class="breadcrumb-item active">Project_Name</li>
</ol>
<div class="discription-project mb-4" >

    <h2>
      <img class="avatar-project" src={{data.project[0].avatarUrl}} width="60px" />
        {{data.project[0].name}}
        <!--<img class="avatar-user" src={{data.project[0].projectUser.avatarUrls}} data-toggle="tooltip" data-placement="bottom" title={{data.project[0].projectUser.displayName}}/>-->
    </h2>
    <h6 class="ml-4 discription">{{data.project[0].description}}</h6>
    <div class="card">
        <div class="card-header bg-white">
            <div class="row">
                <div class="col-lg-4 col-lg-offset-4">
                    <!-- Actual SEARCH box -->
                    <div class="form-group has-search">
                        <span class=" form-control-feedback"><i class="fas fa-search"></i></span>
                        <input type="text" class="form-control" placeholder="Search" ng-model="searchText.name">
                    </div>

                </div>
                <div class="col-lg-4 col-lg-offset-4">
                    <!-- VERSION dropdown -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            VERSION
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" ng-click="versionFunction(-1)">All</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" ng-repeat="ver in data.version" ng-click="versionFunction(ver)">{{ver.name}}</a>
                        </div>
                    </div>
                   
                </div>
            </div>
            <!-- Content Version and Epic -->
            <div class="row">
                <div class="card-deck mx-auto">
                    <div class="card bg-light col-sm-6" ng-show="modelVersion">
                        <div class="card-body p-2">
                            <button type="button" class="close" ng-click="versionFunction(-1)">&times;</button>
                            <h5 class="card-title">{{displayVersion.name}}</h5>
                            <p class="card-text ml-4 discription">{{displayVersion.description}}<br/>{{displayVersion.startDate +' - '+ displayVersion.releaseDate}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <!-- =======================SPRINTs============================= -->
            <div class="row" ng-repeat="sprint in data.sprints">
                <div id={{"accordion-"+sprint.id}} class="sprint mx-4">
                    <div class="card card-parent">
                        <div class="card-header header-info p-0" id="sprint01">
                            <button class="btn btn-link collapsed mb-0 btnCollapse" data-toggle="collapse" data-target={{"#heading"+sprint.id}} aria-expanded="false" aria-controls={{"heading"+sprint.id}}>
                                {{sprint.name}}
                                <i class="fas fa-plus iconPlus"></i>
                                <i class="fas fa-minus iconMinus"></i>
                            </button>
                            <p class="discription">{{sprint.startDate+ " - "+ sprint.endDate}} </p>
                        </div>
                        <!-- Issues Sprint-->
                        <div id={{"heading"+sprint.id}} class="collapse show" aria-labelledby="sprint01" data-parent={{"#accordion-"+sprint.id}}>
                            <div class="card-body card-issue">
                                <!--row issue-->
                                <div class="card" ng-repeat="issue in data.issues" ng-if="!issue.issueType.subtask && issue.sprintID == sprint.id" ng-init="versionName = ''">
                                     <div ng-repeat="ver in data.version" ng-if="ver.id === issue.fixVersions" ng-init="versionName = ver.name">
                                       <div style="visibility: hidden;">{{versionName = ver.name}}</div>
                                    </div>
                                    <issue-direct issue-type={{issue.issueType.iconUrl}} issue-id={{issue.id}} issue-key={{issue.key}} issue-version={{versionName}}></issue-direct>
                                    
                                    
                                    <!--<div ng-click="getVersion(issue.fixVersions)"></div>
                                    <issue-direct issue-type={{issue.issueType.iconUrl}} issue-id={{issue.id}} issue-key={{issue.key}} issue-version={{versionName}}></issue-direct>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  <!--end Sprints-->
            <!-- =======================BACKLOG========================== -->
            <div class="row">
                <div id="accordion1" class="mx-4">
                    <div class="card card-parent">
                        <div class="card-header  p-0" id="backlog">
                            <button class="btn collapsed mt-4 btnCollapse " aria-controls="heading2">
                                BACKLOG
                            </button>
                        </div>
                        <!-- Issues Backlog-->
                        <div id="heading2" class="collapse show" aria-labelledby="backlog" data-parent="#accordion1">
                            <div class="card-body card-issue">
                                <!--row issue-->
                                <div class="card" ng-repeat="issue in data.issues" ng-if="!issue.issueType.subtask && issue.sprintID ==  '0'" ng-init="versionName=''">
                                    <div ng-repeat="ver in data.version" ng-if="ver.id === issue.fixVersions" ng-init="versionName=ver.name"></div>
                                    <issue-direct issue-type={{issue.issueType.iconUrl}} issue-id={{issue.id}} issue-key={{issue.key}} issue-version={{versionName}}></issue-direct>
                                </div>
                                <!--=.=-->
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!--end Backlog-->
        </div>
    </div>
</div>
</div>