window.onload = function(){
    $.ajax({
        type: "POST",
        url: "/api/getUserList",
        contentType: 'application/json;charset=utf-8',
        dataType: "json",
        success:function (res) {
            var list = document.querySelector('#friendList');
            res.forEach(user => {
                var html = `
                    <li><a class="friend" data-id=${user.userName}>${user.name}:${user.userName}</a></li>
                `
                list.innerHTML += html;
            })
        }
    })
    document.querySelector('#navList').addEventListener('click',(e)=>{
        let src = e.target.getAttribute('src');
        if(src){
            setFrameSrc(src);
        }
        console.log(e.target.classList);
        if(e.target.classList[0] == 'friend'){
            var userName = e.target.getAttribute('data-id');
            setFrameSrc('userDetail.html?userName='+ userName);
        }
    })
    document.querySelector('#friend').onclick = function(){
        
    }

    //为iframe更换链接
    let setFrameSrc = (src) => {
        document.querySelector('.frame iframe').src=src;
    }


}