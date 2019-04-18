<link rel="stylesheet" href="css/detailproject.css">

<div ng-controller="issueCtr">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="#!/projects">Projects</a></li>
        <li class="breadcrumb-item"><a href={{"#!detail-project/"+data.issue[0].project.id}}>{{data.issue[0].project.name}}</a></li>
        <li class="breadcrumb-item active">{{data.issue[0].key}}</li>
    </ol>


    <div class="card">
        <div class="card-header bg-white">
            <div class="discription-project" >
              <h2>{{data.issue[0].key}} 
            </h2>
            <h4 class="ml-4 mt-2 discription">{{data.issue[0].summary}} </h4>
            </div>
        </div>
        <div class="card-body">  
            <div class="row">
                <div class="col-sm-3">
                     <div class="mb-2">
                        <p class="title-info m-0">Type: </p>
                        <img class="ml-4" src={{data.issue[0].issueType.iconUrl}} width="20px" /> {{data.issue[0].issueType.name}}
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Fix version:</p>
                        <span class="ml-4" >{{data.version.name}}</span>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">sprint:</p>
                        <span class="ml-4" >{{data.sprint.name}}</span>
                    </div>
                    <div class="mb-2">
                        <p class="title-info m-0">Time estimate:</p>
                        <div ng-if="data.issue[0].time == 0"></div>
                        <div ng-if="data.issue[0].time != 0">
                            <span class="ml-4" >{{data.issue[0].time/3600}}h</span>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <!-- INFORMATION USER -->
                    <div class="mb-2">
                        <p class="title-info m-0">Creator: </p>
                        <div ng-if="data.issue[0].creator.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue.creator.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue[0].creator.avatarUrls}} width="20px" /> {{data.issue[0].creator.displayName}}
                        </div>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Assignee:</p>
                        <div ng-if="data.issue[0].assignee.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue[0].assignee.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue[0].assignee.avatarUrls}} width="20px" /> {{data.issue[0].assignee.displayName}}
                        </div>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Reporter:</p>
                        <div ng-if="data.issue[0].reporter.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue[0].reporter.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue[0].reporter.avatarUrls}} width="20px" /> {{data.issue[0].reporter.displayName}}
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <!--=====Project-->
                    <div class="mb-2">
                        <p class="title-info m-0">Project:</p>
                        <img class="ml-4" src={{data.issue[0].project.avatarUrl}} width="20px" /> {{data.issue[0].project.name}}
                    </div>
                    <!--=====Subtask-->
                    <div id="accordion1" class="my-0" ng-if="!data.issue[0].issueType.subtask">
                        <div class="card card-parent">
                            <div class="card-header  p-0" id="backlog">
                                <button class="btn mt-2 collapsed btnCollapse " aria-controls="heading2">
                                    Subtasks
                                </button>
                            </div>
                            <!-- Issues -->
                            <div id="heading2" class="collapse show" aria-labelledby="backlog" data-parent="#accordion1">
                                <div class="card-body card-issue">
                                    <!--row issue-->
                                    <div class="card" ng-repeat="issue in data.issues" ng-if="issue.parentID == data.issue[0].id">
                                        <issue-direct issue-type={{issue.issueType.iconUrl}} issue-id={{issue.id}} issue-key={{issue.key}} issue-summary={{issue.summary}} issue-version=""></issue-direct>
                                    </div>
                                    <!--=.=-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>