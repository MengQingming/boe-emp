
/**
 * 根据用户id获取用户信息，弹出框
 * @param id
 */
function getUserInfoById(id){
    top.$.jBox("get:"+ctx+"/rmbs/common/getUserById?id="+id,
        {
            title: "用户信息",
            top:100,
            width: 600,
            height: 200,
            buttons: { '关闭': true }
        }
    );
}

function evelBankCode(bankCode){
    if(bankCode != null &&bankCode != undefined && bankCode != ""){
        if(bankCode.length >8 ){
            var startString = bankCode.substring(0, 4);
            var endString = bankCode.substring(bankCode.length - 4);
            return startString+"****"+endString;
        }
    }
    return "";
}


/**
 * 打开选择人员
 * @param id
 * @param callback
 */
function selectUser(id,callback){
    var user = {};
    top.$.jBox.open("iframe:"+ctx+"/sys/user/selectUserParent?id="+id, "选择人员",810,$(top.document).height()-240,{
        buttons:{"确定":"ok", "清除":"clear", "关闭":true}, bottomText:"通过选择部门，然后选择列出的人员。",submit:function(v, h, f){
            var parentId = h.find("iframe")[0].contentWindow.parentId;
            var parentName = h.find("iframe")[0].contentWindow.parentName;
            if (v=="ok"){
                if(parentId==""){
                    top.$.jBox.tip("未选择人员信息！", 'info');
                    return false;
                };
                // 执行保存
                user =  {id:parentId,userName:parentName};
                callback(user);
            }else if (v=="clear"){
                h.find("iframe")[0].contentWindow.clearAssign();
                return false;
            }
        }, loaded:function(h){
            $(".jbox-content", top.document).css("overflow-y","hidden");
        }
    });
}

/**
 * 金额货币化
 * @param s
 * @returns {*}
 */
function currency(nub) {
	nub = nub.toString();
    if (nub != "") {
        var nubArr = nub.split(".");
    }
    var nubZArr = [];
    if (nubArr.length == 1) {
        var dou = Math.ceil(nubArr[0].length / 3) - 1;
        var first = nubArr[0].length % 3;
        if (first != 0) {
            nubZArr[0] = nubArr[0].substring(0, first);
            for (var i = 1; i < dou + 1; i++) {
                nubZArr[i] = nubArr[0].substring(first + (i - 1) * 3, first + i * 3);
            }
        } else {
            for (var i = 0; i < dou + 1; i++) {
                nubZArr[i] = nubArr[0].substring(i * 3, (i + 1) * 3);
            }
        }
        var nubZ = nubZArr.join(",");
        var nubReturn = nubZ + ".00";
        return nubReturn;
    } else if (nubArr.length == 2) {
        var dou = Math.ceil(nubArr[0].length / 3) - 1;
        var first = nubArr[0].length % 3;
        if (first != 0) {
            nubZArr[0] = nubArr[0].substring(0, first);
            for (var i = 1; i < dou + 1; i++) {
                nubZArr[i] = nubArr[0].substring(first + (i - 1) * 3, first + i * 3);
            }
        } else {
            for (var i = 0; i < dou + 1; i++) {
                nubZArr[i] = nubArr[0].substring(i * 3, (i + 1) * 3);
            }
        }
        var nubZ = nubZArr.join(",");
        var nubReturn = nubZ + "." + nubArr[1];
        return nubReturn;
    } else {
        return 0;
    }
}


//数字转换成大写中文
function intToChinese ( str ) {
    str = str+'';
    var len = str.length-1;
    var idxs = ['','十','百','千','万','十','百','千','亿','十','百','千','万','十','百','千','亿'];
    var num = ['零','一','二','三','四','五','六','七','八','九'];
    return str.replace(/([1-9]|0+)/g,function( $, $1, idx, full) {
        var pos = 0;
        if( $1[0] != '0' ){
            pos = len-idx;
            if( idx == 0 && $1[0] == 1 && idxs[len-idx] == '十'){
                return idxs[len-idx];
            }
            return num[$1[0]] + idxs[len-idx];
        } else {
            var left = len - idx;
            var right = len - idx + $1.length;
            if( Math.floor(right/4) - Math.floor(left/4) > 0 ){
                pos = left - left%4;
            }
            if( pos ){
                return idxs[pos] + num[$1[0]];
            } else if( idx + $1.length >= len ){
                return '';
            }else {
                return num[$1[0]]
            }
        }
    });
}
/**
 * 时间格式化
 * @param obj
 * @param Ishms trun:2017-3-2 11:54:26 , false:2017-3-2
 * @returns {string}
 */
function formatDateTime (obj, Ishms) {
    var myDate = new Date(obj);
    var year = myDate.getFullYear();
    var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
    var day = ("0" + myDate.getDate()).slice(-2);
    var h = ("0" + myDate.getHours()).slice(-2);
    var m = ("0" + myDate.getMinutes()).slice(-2);
    var s = ("0" + myDate.getSeconds()).slice(-2);
    if (Ishms == true) {
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
    }
    else {
        return  year + "-" + month + "-" + day;
    }
}
/**
 * 是否走我的保存
 * @param formId 报账单id
 * @param isSave 是否保存 1-是保存，2-不走我的保存
 */
function sendClaim(formId,isSave){
	loading('请稍等...');
	$.ajax({ 
		url: ctx+"/rmbs/common/sendRmbsClaim", 
		data:{
			formId : formId
		},
		type:"post",
		cache:false,

		success: function(data){

			if(data.success){
			    alertx(data.msg);
                closeLoading();
                var url = ctx+'/myWorkbench/myfinish';
                window.location.href = url;
            }else{
                alertx(data.msg);
                closeLoading();
            }

			//openSelectAduitPersonnel(data,formId,isSave);
  		}
	});
}

function openSelectAduitPersonnel(data,formId,isSave){
	top.$.jBox.open(data, "选择审批人",610,$(top.document).height()-240,{
		buttons:{"提交":"ok","关闭":true}, bottomText:"",submit:function(v, h, f){
			var selectUserId = f.selectUserId;
			var selectGroupId = f.selectGroupId;
			var processInstId = f.processInstId;
			var taskInstId = f.taskInstId;
			var nextActivityDefId = f.nextActivityDefId;
			var targetActivityDefName = f.targetActivityDefName;
			if (v=="ok"){
				if(selectUserId=="" && nextActivityDefId != "financePreReviewActivity"){
					top.$.jBox.tip("未选择审批人！", 'info');
					return false;
				};
				if("1" == isSave){
					save("D",'2');
				}
				startCompleteTask(formId,processInstId,taskInstId,nextActivityDefId,selectUserId,selectGroupId,targetActivityDefName);
			} 
		}, loaded:function(h){
			$(".jbox-content", top.document).css("overflow-y","hidden");
		}
	});
}

function startCompleteTask(formId,processInstId,taskInstId,nextActivityDefId,assigneeUserId,assigneeGroupId,targetActivityDefName){
	loading('请稍等...');
	$.ajax({ 
	url: ctx+"/rmbs/common/startCompleteTask", 
	data:{
		processInstId:processInstId,
		taskInstId:taskInstId,
		nextActivityDefId:nextActivityDefId,
		assigneeUserId:assigneeUserId,
		assigneeGroupId:assigneeGroupId,
		targetActivityDefName :targetActivityDefName,
		formId:formId
	},
	type:"post",
	cache:false,
	context: document.body, 
	success: function(html){
			closeLoading();
			var data = html.split(",");
			if("SUCCESS" == data[0]){
				window.location.href = ctx+"/rmbs/common/open/view?id="+data[1]+"&templateNo="+data[2];
			}else{
				alertx("系统异常，请联系管理员");
			}
			}
		});
}

function rmoney(s)
{
    return parseFloat(s.replace(/[^\d\.-]/g, ""));
}

function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(x*100)/100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}
//加法
function accAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
}

//乘法
function accMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}