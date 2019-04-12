<div class="sidebar sidebar-blue bg-blue">
    <ul class="list-unstyled">
        <li class="titleProject">
            <hr/>
            <h3 >JIRA-EXTRACT </h3>
            <a class="sidebar-toggle mr-3"><i class="fa fa-bars"></i></a>
            <hr/>
        </li>
        <li ng-class="{'active': index == 0}"><a href="#!/" ng-click="index=0"><i class="fa fa-fw fa-tachometer-alt"></i> Dashboard</a></li>

        <li ng-class="{'active': index == 1}"><a href="#!/projects" ng-click="index=1"><i class="fa fa-fw fa-flag"></i> Projects</a></li>
        <li><a href="/logout"><i class="fa fa-fw fa-book"></i> Log out</a></li>
    </ul>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var body = $('body');

        body.tooltip({
            selector: '[data-toggle="tooltip"]'
        });
        body.popover({
            selector: '[data-toggle="popover"]'
        });

        $('.sidebar-toggle').on('click', function (e) {
            e.preventDefault();
            $('.sidebar').toggleClass('toggled');
        });
    });
</script>