var clickAdd = function() {
    $(location).attr('href', 'subscriber/add')
}

var clickDel = function() {
    var idList = [];
    jQuery("input:checked[name='selCheckBox']").each(function() {
        idList.push(this.id);
    });

    console.log(idList);

    $("#ids").val(idList);
    $("#del-form").submit();

//    $.post("/subscriber/del", {id: idList},
//        function(returnedData){
//             window.location.reload();
//    }).fail(function(){
//          console.log("error deletion");
//    });
}

var onLoad = function() {
 console.log("hello");
 $("#add").click(clickAdd);
 $("#del").click(clickDel);
};

$(window).on('load', onLoad);
