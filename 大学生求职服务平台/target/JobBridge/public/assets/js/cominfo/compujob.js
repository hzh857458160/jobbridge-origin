/*
*       检查用户输入的规范
 */
function checkSaveFunc() {
    if ($('#jobName').val() === null ||$('#jobName').val() === '' ) {
        $('#saveTip').text("请输入职位名称");
        return false;
    }else if ($('#location').val() === null||$('#location').val() === '') {
        $('#saveTip').text("请输入工作地点");
        return false;
    }else if($('#lowSalary').val() === null ||$('#lowSalary').val() === ''){
        $('#saveTip').text("请输入最低薪金");
        return false;
    }else if ($('#highSalary').val() === null ||$('#highSalary').val() ==='') {
        $('#highSalary').text("请输入最高薪金");
        return false;
    }else if($('#deadline').val()===null ||$('#deadline').val()==='' ){
        $('#saveTip').text("请输入截止日期");
        return false;
    }else if ($('#jobDescribe').val() === null || $('#jobDescribe').val() ==='' ) {
        $('#saveTip').text("请输入职位描述");
        return false;
    }else if ($('#jobRequire').val()===null ||$('#jobRequire').val()==='' ){
        $('#saveTip').text("请输入职位要求");
        return false;
    }
    return true;
}

function save(){
    if(checkSaveFunc()){
        $.ajax({
            type: 'post',
            url: '/enterprise/recruitinfo/new',
            dataType: 'json',
            data: {
                jobName: $('#jobName').val(),
                location: $('#location').val(),
                lowSalary: $('#lowSalary').val(),
                highSalary: $('#highSalary').val(),
                deadline: $('#deadline').val(),
                jobDescribe:$('#jobDescribe').val(),
                jobRequire:$('#jobRequire').val()
            },
        }).done(function (data) {
            console.log('成功, 收到的数据: ' + JSON.stringify(data, null, '  '));
            var result = data;
            if(result.ok === "true"){
                window.location.href = "";
            }else{
                $('#saveTip').text(result.reason);
            }
        });
    }
}
