<link rel="stylesheet" href="css/detailproject.css">

<div ng-controller="issueCtr">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="Projects.html">Projects</a></li>
        <li class="breadcrumb-item"><a href="DetailProject.html">Project_Name</a></li>
        <li class="breadcrumb-item active">Issue_Name</li>
    </ol>


    <div class="card">
        <div class="card-header bg-white">
            <div class="discription-project" >
              <h2>{{data.issue.key}} 
            </h2>
            <h6 class="ml-4 mt-2 discription">{{data.issue.discription}} </h6>
            </div>
        </div>
        <div class="card-body">  
            <div class="row">
                <div class="col-sm-3">
                     <div class="mb-2">
                        <p class="title-info m-0">Type: </p>
                        <img class="ml-4" src={{data.issue.issueType.iconUrl}} width="20px" /> {{data.issue.issueType.name}}
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Fix version:</p>
                        <span class="badge badge-secondary ml-4">Version01</span>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">sprint:</p>
                        <span class="ml-4">Print 01</span>
                    </div>
                </div>
                <div class="col-sm-3">
                    <!-- INFORMATION USER -->
                    <div class="mb-2">
                        <p class="title-info m-0">Creator: </p>
                        <div ng-if="data.issue.creator.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue.creator.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue.creator.avatarUrls}} width="20px" /> {{data.issue.creator.displayName}}
                        </div>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Assignee:</p>
                        <div ng-if="data.issue.assignee.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue.assignee.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue.assignee.avatarUrls}} width="20px" /> {{data.issue.assignee.displayName}}
                        </div>
                    </div>
                     <div class="mb-2">
                        <p class="title-info m-0">Reporter:</p>
                        <div ng-if="data.issue.reporter.accountId === '-1'">
                            <img class="ml-4" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" width="20px" /> Unassigned
                        </div>
                        <div ng-if="data.issue.reporter.accountId !== '-1'">
                            <img class="ml-4" src={{data.issue.reporter.avatarUrls}} width="20px" /> {{data.issue.reporter.displayName}}
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="mb-2">
                        <p class="title-info m-0">Project:</p>
                        <img class="ml-4" src={{data.issue.project.avatarUrl}} width="20px" /> {{data.issue.project.name}}
                    </div>
                    <div id="accordion1" class="my-0">
                        <div class="card card-parent">
                            <div class="card-header  p-0" id="backlog">
                                <button class="btn mt-2 collapsed btnCollapse " aria-controls="heading2">
                                    Subtasks
                                </button>
                            </div>
                            <!-- Issues -->
                            <div id="heading2" class="collapse show" aria-labelledby="backlog" data-parent="#accordion1">
                                <div class="card-body card-issue">
                                    <div class="card">
                                        <div class="card-body">
                                            <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype" weight="30px" height="30px" />
                                            <p class="my-auto">hello</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>