  function loadFeed(){
  $.ajax({
  url:"/get/all/status",
  type:"GET",
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
  if(data.responseText == null){
        alert("you have no friends");
    }else{
   $('#liveFeed').html(data.responseText).promise().done(function(){
     var statusDates = document.getElementsByName("statusDate");

      for(var i=0; i<statusDates.length;i++){
      	statusDates[i].innerHTML=timeSince(new Date(statusDates[i].innerHTML));
         }
        });
        }
       }
    });
  }

  $("#statusBtn").click(function(){
    document.getElementById("statusLoadIcon").className="fas fa-spinner fa-spin";
    var message = document.getElementById("statusMsg").value;

    var prep = {};
    prep['message'] = message;
    prep['location'] = 0;
    prep['likes'] = 0;

    prep['going'] = "";
    prep['not_going'] = "";
    prep['maybe'] = "";

    var data = JSON.stringify(prep);
    $.ajax({
  url:"/create/status",
  type:"POST",
  data:data,
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
  document.getElementById("statusLoadIcon").className="fas fa-paper-plane";
  document.getElementById("statusMsg").value="";
$("#app-growl").append('<div class="alert alert-success alert-dismissible fade show" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>Success! Status uploaded!</div>');
  loadFeed();
  }
    });

});
