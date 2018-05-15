  function loadFeed(){
  $.ajax({
  url:"/get/all/user/status",
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
         var stateObj = { profile: "/profile" };
         history.replaceState(stateObj, "Sesh", "/profile/#/");
         loadOnlineUsers();
        });
        }
       }
    });
  }

    function loadOnlineUsers(){
    $.ajax({
    url:"/get/online/users",
    type:"GET",
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    error:function(){},
    complete:function(data){
    if(data.responseText == null){
          alert("you have no friends");
      }else{
     $('#onlineFriends').html(data.responseText).promise().done(function(){

          });
          }
         }
      });
    }

  $("#statusBtn").click(function(){
    document.getElementById("statusLoadIcon").className="fas fa-spinner fa-spin";
    var message = document.getElementById("statusMsg").value;

    var prep = {};
    prep['user_id'] = getSeshCookie("ul");
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

  $("#aboutMarker").click(function(e) {
    e.preventDefault();

          $('#postsContent').css("display","none");
          $('#aboutContent').css("display","");
          $('#reviewsContent').css("display","none");

          $("#aboutMarker").addClass("active");
          $("#reviewsMarker").removeClass("active");
          $("#postsMarker").removeClass("active");

    var content = $('#aboutContent').html();
    $('#profileContent').replaceWith(content);
  });

   $("#reviewsMarker").click(function(e) {
      e.preventDefault();

      $('#postsContent').css("display","none");
      $('#aboutContent').css("display","none");
      $('#reviewsContent').css("display","");

      $("#reviewsMarker").addClass("active");
      $("#aboutMarker").removeClass("active");
      $("#postsMarker").removeClass("active");

      var content = $('#reviewsContent').html();
      $('#profileContent').replaceWith(content).promise().done();
    });

    $("#postsMarker").click(function(e) {
          e.preventDefault();

          $('#postsContent').css("display","");
          $('#aboutContent').css("display","none");
          $('#reviewsContent').css("display","none");

          $("#postsMarker").addClass("active");
          $("#reviewsMarker").removeClass("active");
          $("#aboutMarker").removeClass("active");

          var content = $('#postsContent').html();
          $('#profileContent').replaceWith(content).promise().done();
        });

function getSeshCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}