/**
 * Created by huangzhenyang on 2017/7/24.
 */

$(document).ready(function () {
    getHeaderUserName();
   getData();
});

/*
*  @author:HuangZhenyang
*  获取数据
* */
function getData() {
    $.ajax({
        url:'/enterprise/showdeliveryinfo',
        type:'get',
        dataType:'json'
    }).done(function (data) {
        setData(data);
    }).fail(function (xhr, status) {

    });
}


/*
 *  @author:HuangZhenyang
 *  操作Dom插入数据
 * */
function setData(data) {
    var eachDataDom = "";
    var dataDom = "";
    var deliveryId = "";
    var deliveryTime = "";
    var jobTitle = "";
    var userName = "";
    var status = "";
    var liClass = ["list-primary","list-danger","list-success","list-warning","list-info"];
    var bgThemes = ["bg-theme","bg-warning","bg-success","bg-info","bg-important"];
    var data = data.data;

    for(let i=0;i<data.length;i++){
        deliveryId = data[i].deliveryid;
        deliveryTime = data[i].deliverytime;
        jobTitle = data[i].jobtitle;
        userName = data[i].username;
        status = data[i].status;

        eachDataDom = "<li class='"+liClass[i%liClass.length] + "' id='"+ deliveryId+"'>" +
            "<i class='fa fa-ellipsis-v'></i>"+
            "<div class='job-title'>"+ "<a href='/public/comreview.html?id="+deliveryId+"'>" +

            "<span class='task-title-sp'>" + jobTitle + "</span>" + "<span>&nbsp from: " + userName +"&nbsp;&nbsp;</span>" +
            "<span class='badge " + bgThemes[i%bgThemes.length] + "'>" + deliveryTime + "</span>" +
            "</a>"+
            "<div class='pull-right hidden-phone'>" +
            "<span class='badge'>"+status+"</span>"+"<span>&nbsp;&nbsp;</span>"+
            "<button class='btn btn-danger btn-xs fa fa-trash-o' onclick='del(this)'></button>" +
            "</div>"+
            "</div>"+
            "</li>";

        dataDom += eachDataDom;
    }

    console.log(dataDom);
    $('#sortable').append(dataDom);
}

/*
 *  @author:HuangZhenyang
 *  点击按钮的请求
 * */
function del(evt) {
    //console.log($(evt).parent().parent().parent().attr('id'));
    $.ajax({
        url: '/enterprise/delivery/delete?id='+ $(evt).parent().parent().parent().attr('id'),
        type:'get',
        dataType:'json'
    }).done(function (data) {
        if(data.ok==='true'){
            delMessage(evt);
        }else{
            alert(data.reason);
        }
    }).fail(function (xhr,status) {

    })
}


/*
 *  @author:HuangZhenyang
 *  点击按钮的请求
 * */
function delMessage(evt) {
    $(evt).parent().parent().parent().remove();
}



