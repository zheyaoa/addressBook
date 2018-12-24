window.onload = function(){
    document.querySelector('#navList').addEventListener('click',(e)=>{
        let src = e.target.getAttribute('src');
        console.log(src)
        if(src){
            setFrameSrc(src);
        }
    })

    //为iframe更换链接
    let setFrameSrc = (src) => {
        document.querySelector('.frame iframe').src=src;
    }
}