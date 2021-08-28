var add = {

    init:function(){
             var _this = this;
    $('#submitButton').on('click', function(){
                _this.submit();
             });
    },

    submit:function(){
            var user = {
                 name : document.getElementById("name").value,
                 age : document.getElementById("age").value
            };

            $.ajax({
                  type: 'POST',
                  url: '/session/add',
                  dataType: 'text',
                  contentType:'application/json; charset=utf-8',
                  data: JSON.stringify(user),
                  success:function(data){
                       console.log("성공");
                       window.location.href="/session/auth";
                  },
                  error:function(e){
                    console.log("실패");
                    alert(e.status);
                  }

                });
            },
};

add.init();