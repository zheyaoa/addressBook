var getUserDetail = function(userName){
    if (!userName){
        console.log(1)
        userName = localStorage.getItem("userName");
    }
    console.log(userName);
    var data = {
        userName
    }
    data = JSON.stringify(data);
    $.ajax({
        type: "POST",
        url: "/api/getUserByUserName",
        contentType: 'application/json;charset=utf-8',
        data:data,
        dataType: "json",
        success:function (res) {
            console.log(res);
            document.querySelector('#name').value = res.name;
            document.querySelector('#birth').value = res.birth;
            document.querySelector('#profession').value = res.profession;
            document.querySelector('#class').value = res.className;
            document.querySelector('#entranceTime').value = res.entranceTime;
            document.querySelector('#leaveTime').value = res.leaveTime;
            document.querySelector('#phoneNumber').value = res.phoneNumber;
            document.querySelector('#location').value = res.location;
            document.querySelector('#employmentUnit').value = res.employmentUnit;
            document.querySelector('#email').value = res.email;
            document.querySelector('#description').value = res.description;


        }
    })
}
var setUser = function(){
    var data = {
        name:document.querySelector('#name').value ,
        birth:document.querySelector('#birth').value,
        profession:document.querySelector('#profession').value,
        className:document.querySelector('#class').value,
        entranceTime:document.querySelector('#entranceTime').value,
        leaveTime:document.querySelector('#leaveTime').value,
        phoneNumber:document.querySelector('#phoneNumber').value,
        location:document.querySelector('#location').value,
        employmentUnit:document.querySelector('#employmentUnit').value,
        email:document.querySelector('#email').value,
        description:document.querySelector('#description').value,
    }
    data = JSON.stringify(data);
    $.ajax({
        type: "POST",
        url: "/api/setUser",
        contentType: 'application/json;charset=utf-8',
        data:data,
        dataType: "json",
        success:function (res) {
            if(res.status == 0){
                alert("网络错误")
            }else if(res.status == 1){
                alert("修改成功");
            }else{
                alert("未登入")
            }
        }
    })
}