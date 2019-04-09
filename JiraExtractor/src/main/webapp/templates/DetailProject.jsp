<link rel="stylesheet" href="css/detailproject.css">

<div ng-controller="detailCtr">
<ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="Projects.html">Projects</a></li>
    <li class="breadcrumb-item active">Project_Name</li>
</ol>
<div class="discription-project mb-4" >

    <h2>
      <img class="avatar-project" src="https://jraproj.atlassian.net/secure/projectavatar?size=medium&avatarId=10324" width="60px" />
        Project_Name 
        <img class="avatar-user" src="https://avatar-cdn.atlassian.com/2494e026b8d7c07e4192f4096ba2cfb8?s=48&d=https%3A%2F%2Fsecure.gravatar.com%2Favatar%2F2494e026b8d7c07e4192f4096ba2cfb8%3Fd%3Dmm%26s%3D48%26noRedirect%3Dtrue" data-toggle="tooltip" data-placement="bottom" title="user-name"/>
    </h2>
    <h6 class="ml-4 discription">create project extract data from jira programs</h6>
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
                            <a class="dropdown-item" href="#">All</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Version02</a>
                            <a class="dropdown-item" href="#">Version03</a>
                        </div>
                    </div>
                    <!-- EPIC dropdown -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            EPIC
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">All</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Epic01</a>
                            <a class="dropdown-item" href="#">Epic02</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content Version and Epic -->
            <div class="row">
                <div class="card-deck mx-auto">
                    <div class="card bg-light col-sm-6">
                        <div class="card-body p-2">
                            <button type="button" class="close">&times;</button>
                            <h5 class="card-title">Version01</h5>
                            <p class="card-text ml-4 discription">release login function<br/>26/thg 3/19 2:50 CH • 27/thg 3/19 2:50 CH</p>
                        </div>
                    </div>
                    <div class="card bg-light col-sm-6">
                        <div class="card-body p-2">
                            <button type="button" class="close">&times;</button>
                            <h5 class="card-title">Epic01</h5>
                            <p class="card-text ml-4 discription">include tasks to complete login function</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <!-- SPRINTs -->
            <div class="row">
                <div id="accordion-sprint1" class="mx-4">
                    <div class="card card-parent">
                        <div class="card-header header-info p-0" id="sprint01">
                            <button class="btn btn-link collapsed mb-0 btnCollapse" data-toggle="collapse" data-target="#heading" aria-expanded="false" aria-controls="heading">
                                Sprint 01
                                <i class="fas fa-plus iconPlus"></i>
                                <i class="fas fa-minus iconMinus"></i>
                            </button>
                            <p class="discription">26/thg 3/19 2:50 CH • 27/thg 3/19 2:50 CH</p>
                        </div>
                        <!-- Issues -->
                        <div id="heading" class="collapse" aria-labelledby="sprint01" data-parent="#accordion-sprint1">
                            <div class="card-body card-issue">
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">xinchao</a></p>
                                        <div class="issue-badge">
                                            <span class="badge badge-secondary">Version01</span>
                                            <span class="badge badge-primary">Epic01</span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="accordion-sprint2" class="mx-4">
                    <div class="card card-parent">
                        <div class="card-header header-info p-0" id="sprint02">
                            <button class="btn btn-link collapsed mb-0 btnCollapse" data-toggle="collapse" data-target="#heading1" aria-expanded="false" aria-controls="heading1">
                                Sprint 02
                                <i class="fas fa-plus iconPlus"></i>
                                <i class="fas fa-minus iconMinus"></i>
                            </button>
                            <p class="discription">26/thg 3/19 2:50 CH • 27/thg 3/19 2:50 CH</p>
                        </div>
                        <!-- Issues -->
                        <div id="heading1" class="collapse" aria-labelledby="sprint02" data-parent="#accordion-sprint2">
                            <div class="card-body card-issue">
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">xinchao</a></p>
                                        <div class="issue-badge">
                                            <span class="badge badge-secondary">Version01</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">xinchao</a></p>
                                        <div class="issue-badge">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>  
            <!-- BACKLOG -->
            <div class="row">
                <div id="accordion1" class="mx-4">
                    <div class="card card-parent">
                        <div class="card-header  p-0" id="backlog">
                            <button class="btn collapsed mt-4 btnCollapse " aria-controls="heading2">
                                BACKLOG
                            </button>
                        </div>
                        <!-- Issues -->
                        <div id="heading2" class="collapse show" aria-labelledby="backlog" data-parent="#accordion1">
                            <div class="card-body card-issue">
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">hello</a></p>
                                        <div class="issue-badge">
                                            <span class="badge badge-primary">Epic01</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">hey</a></p>
                                        <div class="issue-badge">
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <img class="mx-2" src="https://jraproj.atlassian.net/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype" weight="30px" height="30px" />
                                        <p class="my-auto"><a href="#!detail-issue">xinchao</a></p>
                                        <div class="issue-badge">
                                            <span class="badge badge-secondary">Version01</span>
                                            <span class="badge badge-primary">Epic01</span>
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
</div>