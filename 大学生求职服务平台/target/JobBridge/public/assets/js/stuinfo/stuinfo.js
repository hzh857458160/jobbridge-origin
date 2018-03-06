/**
 * Created by huangzhenyang on 2017/7/22.
 */

$(document).ready(function () {
    getHeaderUserName();
    getInfo();
});

/*
 *  @author HuangZhenyang
 *  获取历史信息函数
 * */
function getInfo() {
    $.ajax({
        url: '/studentcenter/showinfo',
        type: 'get',
        dataType: 'json'
    }).done(function (data) {
        setInfo(data);
    }).fail(function (xhr,status) {
        
    });
}

/*
 *  @author HuangZhenyang
 *  设置用户已经设置过的内容
 * */
function setInfo(infoData) {
    if(getJSONLength(infoData)===1){
        var email = infoData.email;
        $('#userName').text(infoData.email);
        $('#email').text(infoData.email);
    }else{
        var info = infoData.info;
        var study = infoData.study;
        var jobintention = infoData.jobintention;
        var city = jobintention.city;
        var industry = jobintention.industry;
        var func = jobintention.func;
        var str = "";

        $('#userName').text(info.email);
        $('#email').text(info.email);
        $('#phone').attr('value',info.phone);

        $('#school').attr('value',study.school);
        $('#major').attr('value',study.major);
        $('#grade').attr('value',study.grade);

        for(let i=0;i<city.length;i++){
            str = "input:checkbox[value="+city[i]+"]";
            $(str).attr('checked','true');
        }
        for(let i=0;i<industry.length;i++){
            str = "input:checkbox[value='"+industry[i]+"']";
            $(str).attr('checked','true');
        }
        for(let i=0;i<func.length;i++){
            str = "input:checkbox[value='"+func[i]+"']";
            $(str).attr('checked','true');
        }
    }
}

/*
*
* */
function getJSONLength(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    console.log(size);
    return size;
};

/*
 *  @author HuangZhenyang
 *  保存按钮函数
 * */
function save() {
    let phone = $('#phone').val();
    let school = $('#school').val();
    let major = $('#major').val();
    let grade = $('#grade').val();

    let city = new Array();
    let industry = new Array();
    let func = new Array();

    $('input[name="city"]:checked').each(function(){
        city.push($(this).siblings().text());
    });
    $('input[name="industry"]:checked').each(function(){
        industry.push($(this).siblings().text());
    });
    $('input[name="func"]:checked').each(function(){
        func.push($(this).siblings().text());
    });

    for(let i=0;i<city.length;i++){
        console.log(city[i]);
    }

    if(check(phone,school,major,grade,city,industry,func)){
        var data = JSON.stringify({
            "info":{
                "phone":phone
            },
            "study":{
                "school": school,
                "major": major,
                "grade": grade
            },
            "jobintention":{
                "city":city,
                "industry":industry,
                "func":func
            }
        });
        $.ajax({
            url: '/studentcenter/saveinfo',
            type: 'post',
            dataType: 'json',
            // contentType: "application/json;charset=utf-8",
            data:{
                "content":data
            }
        }).done(function (data) {
            $('#saveTip').text("保存成功");
            console.log("成功收到数据："+JSON.stringify(data));
        }).fail(function (xhr, status) {
            console.log("失败:" + xhr + " , ",status);
        });
    }
}






