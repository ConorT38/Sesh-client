  $.ajax({
  url:"/get/all/status",
  type:"POST",
  contentType:"application/json; charset=utf-8",
  dataType:"json",
  error:function(){},
  complete:function(data){
    ('#liveFeed').load(document.URL +  ' #liveFeed');
  }
    });

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
  //alert(JSON.stringify(data));
  document.getElementById("statusLoadIcon").className="fas fa-paper-plane";
  document.getElementById("statusMsg").value="";
  }
    });

});
