$(document).ready(function() {
    //初始化配件
    App.init();
    EternalGravitational.init();
    $("#page-container").hide();
    $('body').jpreLoader({
        splashID: "#jSplash",
        loaderVPos: '70%',
        //autoClose: false,
        //closeBtnText: "Let's Begin!",
        splashFunction: function() {
            //passing Splash Screen script to jPreLoader
            $('#jSplash').children('section').not('.selected').hide();
            $('#jSplash').hide().fadeIn(800);

            timer = setInterval(function() {
                splashRotator();
            }, 4000);
        }
    }, function() {	//callback function
        clearInterval(timer);
        $("#page-container").fadeIn(1000);
        /* $('#footer')
         .animate({"bottom":0}, 500);
         $('#header')
         .animate({"top":0}, 800, function() {
         $('#wrapper').fadeIn(1000);
         });*/
    });

    //create splash screen animation
    function splashRotator(){
        var cur = $('#jSplash').children('.selected');
        var next = $(cur).next();

        if($(next).length != 0) {
            $(next).addClass('selected');
        } else {
            $('#jSplash').children('section:first-child').addClass('selected');
            next = $('#jSplash').children('section:first-child');
        }

        $(cur).removeClass('selected').fadeOut(800, function() {
            $(next).fadeIn(800);
        });
    }
});