$(document).ready(function () {
    getHeaderUserName();
   showcominfo();
});


function  showcominfo() {
    $.ajax({
        url: '/enterprise/showinfo',
        type: 'get',
        dataType: 'json'
    }).done(function (data) {
        setData(data);
    }).fail(function (xhr,status) {

    });
}

function setData(data) {
    $("#name").text(data.name);
    $("#userName").text(data.userName);
    $("#mailbox").text(data.mailbox);
    $("#phoneNum").text(data.phoneNum);
    $("#enterpriseIntroduction").text(data.enterpriseIntroduction);
}


