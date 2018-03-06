/*
*       检查用户输入的规范
 */
;function checkRegisterFunc() {
    if ($('#userName').val() === null || $('#userName').val() === '') {
        $('#comregisterTip').text("请输入账户 ");
        return false;
    } else if ($('#password').val() === null || $('#password').val() === '') {
        $('#comregisterTip').text("请输入密码");
        return false;
    } else if(!($('#password').val().length>=6 && $('#password').val().length<=16)){
        $('#comregisterTip').text("密码不能少于6个字符或多于16个字符");
        return false;
    }
    return true;
}

function registerFunc(){
    if(checkRegisterFunc()){
        $.ajax({
            url: '/register/admin',
            type: 'post',
            dataType: 'json',
            data: {
                userName: $('#userName').val(),
                passWord: $('#password').val(),
            },
        }).done(function (data) {
            console.log('成功, 收到的数据: ' + JSON.stringify(data, null, '  '));
            var result = data;
            if(result.ok === "true"){
                window.location.href = "/adminPage/addEnterprise";
            }else{
                $('#comregisterTip').text(result.reason);
            }
        });
    }
}