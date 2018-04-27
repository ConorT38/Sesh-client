  $("#statusBtn").click(function(){
    var message = document.getElementById("statusMsg").value;

    var prep = {};
    prep['message'] = message;

    var data = JSON.stringify(prep);
    $.ajax({
  url:"/create/status",
  type:"POST",
  data:data,
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  success: function(data,status){
  },
  error:function(){

           }
    });

});