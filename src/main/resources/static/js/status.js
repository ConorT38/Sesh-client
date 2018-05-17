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

$('.status').click(function(e) {
    e.preventDefault();
});

$(document).keyup(function (e) {
    if ($("input[name='comment']").focus() && (e.keyCode === 13)) {
    console.log("id: "+e.target.id);
    console.log("value: "+e.target.value);

       postComment(e.target.id,e.target.value);
       document.getElementById(e.target.id).value = "";
    }
 });


function postComment(id,message){
    var prep = {};
    prep['user_id'] = getSeshCookie("ul");
    prep['status_id'] = id.replace('commentMessage-','');
    prep['likes'] = 0;
    prep['message'] = message;

    var data = JSON.stringify(prep);
    $.ajax({
  url:"/create/comment",
  type:"POST",
  data:data,
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
  document.getElementById("statusLoadIcon").className="fas fa-paper-plane";
  document.getElementById("statusMsg").value="";
    getComment('comments-'+id.replace('commentMessage-',''))
  }
    });
}

function getComment(id){
    var prep = {};
    var new_id = id.replace('comments-','');

    $.ajax({
  url:"/get/comments/"+new_id,
  type:"GET",
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
   $('#comments-'+new_id).html(data.responseText).promise().done(function(){

         });
        }
    });
}


function getSeshCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}
