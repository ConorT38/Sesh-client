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

   function followUser(id){
   var id = id.replace('follow-','');

   var prep = {};
   prep['user_id'] = id;

   var data = JSON.stringify(prep);
   $.ajax({
    url:"/follow/user",
    type:"POST",
    data:data,
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    error:function(){},
    complete:function(data){
        loadRecommendedUsers();
    }
      });
    }