var clickAdd = function() {
    $(location).attr('href', 'subscriber/add')
}

var clickDel = function() {
    alert("del");
}

var onLoad = function() {
 console.log("hello");
 $("#add").click(clickAdd);
 $("#del").click(clickDel);
};

$(window).on('load', onLoad);
