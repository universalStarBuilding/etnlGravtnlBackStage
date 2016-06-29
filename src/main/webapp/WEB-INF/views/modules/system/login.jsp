<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <title>Eternal gravitational Back Stage Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Styles -->
    <link href="/css/preview/login.css" rel="stylesheet">
    <script src="/plugins/bootstrap-progressbar/js/bootstrap-progressbar.js"></script>
</head>
<body>

<div class="logo">
    <h4><a href="#"><img src="/images/base/logoed.png" alt="" style="width:65%"></a></h4>
</div>
<div class="lock-holder">
    <div class="form-group pull-left input-username">
        <div class="input-group">
            <input type="text" class="form-control" name="loginName"  placeholder="bootstrapguru">
            <span class="input-group-addon"><i class="fa fa-user"></i></span>
        </div>
    </div>
    <i class="fa fa-ellipsis-h dot-left"></i>
    <i class="fa fa-ellipsis-h dot-right"></i>
    <div class="form-group pull-right input-password">
        <div class="input-group">
            <input type="password" class="form-control " name="password" placeholder="************">
            <span class="input-group-addon"><i class="fa fa-key"></i></span>
        </div>
    </div>
</div>
<div class="avatar">
    <img src="/images/base/IMG_1118.JPG" alt="">
</div>
<div class="submit">
    <button type="button" class="btn btn-success btn-submit"><i class="fa fa-unlock"></i> ENTER</button>
</div>


<script type="text/javascript">
    $(document).ready(function(){

        $('.btn-submit').click(function(e){
            $('.input-username,dot-left').addClass('animated fadeOutRight')
            $('.input-password,dot-right').addClass('animated fadeOutLeft')
            $('.btn-submit').addClass('animated fadeOutUp')
            setTimeout(function () {
                        $('.avatar').addClass('avatar-top');
                        //$('.submit').html('<i class="fa fa-spinner fa-spin text-white"></i>');
                        $('.submit').html('<div class="progress"><div class="progress-bar progress-bar-success" aria-valuetransitiongoal="100"></div></div>');
                        $('.progress .progress-bar').progressbar();
                    },
                    500);

            setTimeout(function () {
                        window.location.href = '/page/index.html';
                    },
                    1500);

        });


    });
</script>

</body>
</html>

