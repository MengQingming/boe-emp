<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%String contextPath = request.getContextPath();%>

<html>
<head>
<script src="${ctxStatic}/ajaxfileupload/ajaxfileupload.js"></script>
<style type="text/css">
.file {
    position: relative;
    display: inline-block;
    background: #40ABE9;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #FFFFFF;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin-left: 14px;
    
} 	
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #40ACEA;
    border-color: #78C3F3;
    color: #FFFFFF;
    text-decoration: none;
}
</style>


<script type="text/javascript">
function downloadFileBtn(id){
		location.href='${ctx}/sysfile/sysfileupload/downloadfile?fileId='+id;
	}
function deleteFileBtn(id){
	confirmx("确定要删除该附件吗？",function(){
		  $.ajax({
        type: "post",
        data:{"id":id, "claimId":document.getElementById('claimId').value},
        url: "${ctx}/sysfile/sysfileupload/deletefile",
        dataType: "json",
        success: function(data){
        	if(data!="false"){
	        	var json =data;
	        	console.log(json);
				if(data.status!="noData"){
					top.$.jBox.tip("附件删除成功！", 'info');
					splicHtmlNolist(data);
				}
				
        	}else{
        		 top.$.jBox.tip('附件删除失败！');
        	}
        	
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	 top.$.jBox.tip('附件删除失败！');
        }
    });
	  });
  
}

//上传附件的合法数据验证
function validateFileExt() {
	//判断是否选择附件
	var fileName = document.getElementById("myfile").value;
	if (fileName == "") {
		top.$.jBox.alert("请选择上传附件!");
		return false;
	}else{
		return true;
	}
}
//得到附件客户端的路径
function getPath(obj)
{
  if(obj)
    {    

    if (window.navigator.userAgent.indexOf("MSIE")>=1)
      {
        obj.select();    
        var text="";
        if (window.getSelection) { 
        	text = window.getSelection(); 
        } else if (window.document.getSelection) { 
        	text = window.document.getSelection(); 
        } else if (window.document.selection) { 
        	text = window.document.selection.createRange().text; 
        } 
      	return text;
      }    

    else if(window.navigator.userAgent.indexOf("Firefox")>=1)
      {
      if(obj.files)
        {    

        return obj.files.item(0).getAsDataURL();
        }
      return obj.value;
      }
    return obj.value;
    }
}
function uploadFile(){
	if(validateFileExt()){
		var mydata;
		var filepath=getPath(document.getElementById('myfile'));
		$.ajaxFileUpload
		(
			{
				url:'${ctx}/sysfile/sysfileupload/fileToUpload',
				secureuri:false,
				fileElementId:'myfile',
				dataType: 'text/html',
				data:{"claimId":document.getElementById('claimId').value,"clientPath":filepath},
				success: function (data, status)
				{
					var a=data.replace(/<\/*?pre[^<>]*>/,'');
					var mydata1=a.split('</pre>');
					var json=JSON.parse(mydata1[0]);
					if(json.status=='exist'){
						top.$.jBox.tip('该附件已经存在！');
					}else if(json.status=='overSize'){
						top.$.jBox.alert('该附件超过'+json.size);
					}else{
						 top.$.jBox.tip("附件上传成功！", 'info');
						splicHtmlNolist(json);
					}
					
				},
				error: function (data, status, e)
				{
					top.$.jBox.alert(e);
				}
			}
		)
	}
	
}


function splicHtmlNolist(json){
  
	  var html2="";
	  for (var _i = 0; _i < json.row.length; _i++) {
	      html2 += " &nbsp;&nbsp; <a href='#' style='color: #333;text-decoration:underline;line-height: 30px;' onclick='downloadFileBtn("+json.row[_i].id+")'> " + json.row[_i].fileName + "</a>  &nbsp;&nbsp;"
	        +"<a href='#' onclick='deleteFileBtn("+json.row[_i].id+")'> "
	        +"<img src='${ctxStatic}/images/X2.png' onMouseOver='this.src='${ctxStatic}/images/X2.png''onMouseOut='this.src='${ctxStatic}/images/X2.png'' title='删除'></a>"
	        +"<br>";                   
	      }
   	 $("#filecontent").html(html2);
}
/* //拼接页面
function spliceHtml(json){
	var html="";
	for (var _i = 0; _i < json.row.length; _i++) {
		var statusvar="";
		if(json.row[_i].status=="1"){
			statusvar="已上传";
		}else{
			statusvar="未上传";
		}
			html += "<tr><td>" + json.row[_i].fileName + "</td><td>" + json.row[_i].fileType + "</td><td>" + statusvar + "</td> <td>  "
      		+" <a href='#' onclick='downloadFileBtn("+json.row[_i].id+")'><img src='${ctxStatic}/images/download.png' onMouseOver='this.src='${ctxStatic}/images/download2.png''onMouseOut='this.src='${ctxStatic}/images/download.png'' title='下载' style='height:70%;'></a>"
      		+" <a href='#' onclick='deleteFileBtn("+json.row[_i].id+")'><img src='${ctxStatic}/images/03.png' onMouseOver='this.src='${ctxStatic}/images/3.png''onMouseOut='this.src='${ctxStatic}/images/03.png'' title='删除' style='height:80%;'></a>"
      		+"</td></tr>";			             
  		}
  	 //document.getElementById("content").innerHTML=html; 
  	 $("#filecontent").html(html);
  	 $("#filesize").html("");
} */


function loadfile(file) {
    var filePath = file.value;
    if (file.files && file.files[0]) {
        var filesize="附件大小："+(file.files[0].size / 1024).toFixed(0) + "kb";
        document.getElementById("filesize").innerHTML=filesize;
    } 
} 
function cencelFileBtn(){
	 $("#filesize").html("");
	document.getElementById("myfile").value="";
}
</script>
</head>
	<input type="hidden" name="claimId" id="claimId" value="${claimId}"/>
		<table id="contentTable" class="table-bordered"  style="width: 100%;">
			<tr>
				<td style="width: 100px;" rowspan="${fn:length(fileList)}">
					<a href="javascript:;" class="file">添加附件
    					<input type="file" name="myfile" id="myfile" onchange="uploadFile()">
					</a>
				</td>
				<td id="filecontent">
		           <c:forEach items="${fileList}" var="sysFile" varStatus="status">
		              &nbsp;&nbsp;
		               <a href="#" style="color: #333;text-decoration:underline;line-height: 30px;" onclick="downloadFileBtn(${sysFile.id})">${sysFile.fileName} </a>
		               &nbsp;&nbsp;
		             <a href="#" onclick="deleteFileBtn(${sysFile.id})">
		             <img src="${ctxStatic}/images/X2.png" onMouseOver="this.src='${ctxStatic}/images/X2.png'"onMouseOut="this.src='${ctxStatic}/images/X2.png'" title="删除"></a>
		             <br>
		           </c:forEach>
       			 </td>
			</tr>
</table>
