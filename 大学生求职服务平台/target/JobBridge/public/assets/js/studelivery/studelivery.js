$(document).ready(function () {
    getHeaderUserName();
    getData();
});

//获取用户投递职位的信息
function getData() {
    $.ajax({
        url: '/studentcenter/showdelivery',
        type: 'get',
        dataType: 'json'
    }).done(function (data) {
        setDeliveryData(data);
        console.log("成功收到数据：" + JSON.stringify(data));
    }).fail(function (xhr, status) {
        console.log("失败:" + xhr + " , ", status);
    });
}

//操作DOM,插入数据
function setDeliveryData(data) {
    var deliveryDataDom = "";
    var eachDeliveryDataDom = "";
    var contentDom = "";
    var data = data.deliverydata;
    var position = "";
    var havedel = "";
    var bg = ["bg-violet", "bg-green", "bg-pink", "bg-blue"]; //设置背景色
    var icon = ["fa-group", "fa-paper-plane", "fa-coffee"]; //设置图标
    var bgThemes = ["bg-theme","bg-warning","bg-success","bg-info","bg-important"];

    if(data.length === 0){
        deliveryDataDom = "<h4> Opps! 你似乎还没投递任何简历哦</h4> ";
    }else{
        for (let i = 0; i < data.length; i++) {
            //职位是否已被删除
            if(data[i].havedel === "true"){
                havedel = "职位已删除";
            }else{
                havedel = "";
            }
            //设置时间轴卡片的位置
            if (i % 2 === 0) {
                position = "timeline-entry";
            } else {
                position = "timeline-entry left-aligned";
            }
            contentDom = "<div class='timeline-entry-inner'>" +
                "<time class='timeline-time'><span>" + data[i].time + "</span>" + "</time>" +
                "<div class='timeline-icon " + bg[i % bg.length] + "'>" + "<i class='fa " + icon[i % icon.length] + "'></i></div>" +
                "<div class='timeline-label " + bg[i % bg.length] + "'>" + "<h4 class='timeline-title'>" +"<a href='"+data[i].jobhref+"'>"+ "<span style='color: #ffffff'>"+data[i].jobtitle + "  " + data[i].comname + "</span></a>"+"<span style='float: right' class='badge " + bgThemes[i%bgThemes.length] + "'>" + havedel + "</span>"  +"</h4><hr>" +
                "<p>" + data[i].jobdesc + ".</p></div>" +
                "</div>";

            //如果是最后一个，加上  加号
            if (i === data.length - 1) {
                eachDeliveryDataDom = "<article class='" + position + "'>" +
                    contentDom +
                    "<div class='timeline-entry-inner'>" +
                    "<div style='-webkit-transform: rotate(-90deg); -moz-transform: rotate(-90deg);' class='timeline-icon'><a href='../public/jobs.html'><i class='fa fa-plus'></i></a></div>" +
                    "</div>" +
                    "</article>";
            } else {
                eachDeliveryDataDom = "<article class='" + position + "'>" +
                    contentDom +
                    "</article>";
            }

            deliveryDataDom += eachDeliveryDataDom;
            position = "";
            eachDeliveryDataDom = "";
        }
    }

    $('#timeline').append(deliveryDataDom);
}

