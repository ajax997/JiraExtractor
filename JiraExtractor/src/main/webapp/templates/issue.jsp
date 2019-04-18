<div class="card-body">
    <img class="mx-2" src={{issueType}} weight="30px" height="30px" />
    <p class="my-auto" width="100%"><a href={{"#!detail-issue/"+issueId}}>{{issueKey}} - {{issueSummary}}</a></p>
    <div class="issue-badge">
        <span class="badge badge-secondary" ng-if="issueVersion != ''">{{issueVersion}}</span>
    </div>
</div>