  function loadRecommendedUsers(){
  $.ajax({
  url:"/get/recommended/users",
  type:"GET",
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
  if(data.responseText == null){
        alert("you have no friends");
    }else{
   $('#recommendedUsers').html(data.responseText).promise().done(function(){

        });
        }
       }
    });
  }