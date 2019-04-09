<div class="sidebar sidebar-blue bg-blue">
    <ul class="list-unstyled">
        <li class="titleProject">
            <hr/>
            <h3 >JIRA-EXTRACT </h3>
            <a class="sidebar-toggle mr-3" href="#"><i class="fa fa-bars"></i></a>
            <hr/>
        </li>
        <li><a href="#"><i class="fa fa-fw fa-tachometer-alt"></i> Dashboard</a></li>

        <li class="active"><a href="#/icons"><i class="fa fa-fw fa-flag"></i> Projects</a></li>
        <li><a href="#/docs"><i class="fa fa-fw fa-book"></i> Log out</a></li>
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