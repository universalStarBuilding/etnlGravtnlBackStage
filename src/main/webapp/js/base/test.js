var Cookie = {
    TOKEN: 'cookie.token',
    LOGINNAME: 'cookie.username',
    THEME: 'cookie.theme',
    USERID: 'cookie.userId'
};

var RequestHeader = {
    TOKEN: 'Authorization',
};

$(document).ready(function() {
    // 当遇到 401 状态码时，清空 cookie 中的 token，并跳转到登录页面
    $.ajaxSetup({
        statusCode: {
            401: function () {
                $.removeCookie(Cookie.TOKEN);
                location.href = '../../WEB-INF/views/index.jsp';
            }
        }
    });


    // 当发送 ajax 请求开始时，将 cookie 中的 token 与 username 放入 request header 中
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader(RequestHeader.TOKEN, $.cookie(Cookie.TOKEN));
    });

    $("#testButton").click(testToken);


});
function testToken()
{
    $.post("/test",
        {
            test:"admin@qq.com",
        },function(data){
            location.href ="/page/index.html";
        },"json");
}