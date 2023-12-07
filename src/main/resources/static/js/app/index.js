const main =  {

    //function 키워드를 사용하는 것이 아니라 화살표 함수를 사용하게 되면 this가 무엇인자 알지못한다.
    init : function (){
            //_ (언더스코어)는 자바스크립트에서 private를 나타냄
            const _this = this;
            console.log('this',this);

            $("#btn-save").on('click',function(){
                _this.save();
            });

            $("#btn-update").on('click',function(){
                _this.update();
            });

    },
    save : function () {
        const data = {
            title : document.querySelector("#title").value,
            content : document.querySelector("#content").value,
            author : document.querySelector("#author").value
        }

        // console.log('data',data);

        $.ajax({
            type: 'post',
            url: '/api/v1/posts',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글 등록완료');
            location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        alert('aaa');
        const data = {
            title : document.querySelector("#title").value,
            content : document.querySelector("#content").value,
        }

        const id = document.querySelector("#id").value;

        $.ajax({
            type: 'put',
            url: '/api/v1/posts/' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글 수정 완료');
            location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    }


}

main.init();





