  function loadNotificationFeed(){
  $.ajax({
  url:"/get/all/notifications",
  type:"GET",
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
  if(data.responseText == null){
        console.log("you have no notifications");
    }else{
   $('#notificationFeed').html(data.responseText).promise().done(function(){
     var notificationDates = document.getElementsByName("notificationDate");

      for(var i=0; i<notificationDates.length;i++){
      	notificationDates[i].innerHTML=timeSince(new Date(notificationDates[i].innerHTML));
         }
        });
        }
       }
    });
  }