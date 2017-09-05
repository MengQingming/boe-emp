/**
 * 公共方法
 */
$(function() {
	var selectArray = $('select');
    var inputArray = $('input');
    var textareaArray = $('textarea');
    for (var i = 0; i < selectArray.length; i++) {
        selectArray[i].disabled = true;
    }
    for (var i = 0; i < inputArray.length; i++) {
        if (inputArray[i].type != "button" && inputArray[i].type != "submit" &&
            inputArray[i].type != "button" && inputArray[i].type != "reset") {
            inputArray[i].readOnly = true;
        }
    }
    for (var i = 0; i < textareaArray.length; i++) {
        textareaArray[i].readOnly = true;
    }
    var str = $("[name='formControlName']").val();
    var json = eval("(" + str + ")");
    for (var key in json) {
        //$("#"+key).attr(json[key],"false");
        if (json[key] == "display") {
            $("#" + key).attr("style", "display:none");
        } else {
            $("#" + key).removeAttr(json[key]);
        }
    }
});

var removeSelectDisabledfnc = function(){
	var selectArray = $('select');
	for (var i = 0; i < selectArray.length; i++) {
        selectArray[i].disabled = false;
    }
}