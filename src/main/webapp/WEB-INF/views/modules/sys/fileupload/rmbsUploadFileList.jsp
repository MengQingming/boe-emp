<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%String contextPath = request.getContextPath();%>

<html>
<head>
<script src="${ctxStatic}/ajaxfileupload/ajaxfileupload.js"></script>

<script type="text/javascript">
function downloadFileBtn(id){
		location.href='${ctx}/rmbs/sysfile/sysfileupload/downloadfile?fileId='+id;
	}
function deleteFileBtn(id){
	confirmx("确定要删除该附件吗？",function(){
		  $.ajax({
        type: "post",
        data:{"id":id, "claimId":document.getElementById('claimIdByUpload').value},
        url: "${ctx}/rmbs/sysfile/sysfileupload/deletefile",
        dataType: "json",
        success: function(data){
        	if(data!="false"){
        		var html="";
	        	var json =data;
	        	var statusvar="";
	        	console.log(json);
				//if(data.status!="noData"){
					top.$.jBox.tip("附件删除成功！", 'info');
					spliceHtml(data);
				//}
				
        	}else{
        		 top.$.jBox.alert('附件删除失败！');
        	}
        	
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	 top.$.jBox.alert('附件删除失败！');
        }
    });
	});
  
}

//上传文件的合法数据验证
function validateFileExt() {
	//判断是否选择文件
	var fileName = document.getElementById("myfile").value;
	if (fileName == "") {
		top.$.jBox.alert("请选择上传附件!");
		return false;
	}else{
		return true;
	}
}
//得到文件客户端的路径
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
		$("#clientForm")[0].submit();
		return false;
	}
}
//拼接页面
function spliceHtml(json){
	var html = "";
	var viewHtml = ${opt eq 'view'?("\"style='display:none'\""):"\"\""};
	if(json!=null && json.row!=null && json.row.length>0){
	for (var _i = 0; _i < json.row.length; _i++) {
		var statusvar="";
		if(json.row[_i].status=="1"){
			statusvar="已上传";
		}else{
			statusvar="未上传";
		}
			html += "<tr><td style='text-align:center;padding:0px 0px;'>" + json.row[_i].fileName + "</td><td style='text-align:center;padding:0px 0px;'>" + json.row[_i].fileType + "</td><td style='text-align:center;padding:0px 0px;'>" + statusvar + "</td> <td style='text-align:center;padding:0px 0px;'>  "
      		+" <a href='#' onclick='downloadFileBtn("+json.row[_i].id+")'><img src='${ctxStatic}/images/download.png' onMouseOver='this.src='${ctxStatic}/images/download2.png''onMouseOut='this.src='${ctxStatic}/images/download.png'' title='下载' style='height:70%;'></a>"
      		+" <a "+viewHtml+" href='#' onclick='deleteFileBtn("+json.row[_i].id+")'><img src='${ctxStatic}/images/03.png' onMouseOver='this.src='${ctxStatic}/images/3.png''onMouseOut='this.src='${ctxStatic}/images/03.png'' title='删除' style='height:80%;'></a>"
      		+"</td></tr>";			             
  		}
	}
  	 //document.getElementById("content").innerHTML=html; 
  	 $("#filecontent").html(html);
  	 $("#filesize").html("");
}


function loadfile(file) {
    var filePath = file.value;
    if (file.files && file.files[0]) {
        var filesize="文件大小："+(file.files[0].size / 1024).toFixed(0) + "kb";
        document.getElementById("filesize").innerHTML=filesize;
    } 
} 
function cencelFileBtn(){
	 $("#filesize").html("");
	document.getElementById("myfile").value="";
}


$(document).ready(function() {
	if("${claimId}" != null && $.trim("${claimId}").length > 0){
		 $.ajax({
	        type: "post",
	        data:"claimNo=${claimId}",
	        url: "${ctx}/rmbs/sysfile/sysfileupload/getFileList",
	        dataType: "json",
	        success: function(data){
				spliceHtml(data);
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        }
		});
	}
});
</script>
</head>
	<iframe name="hidden_frame" style="display: none;"></iframe>
	<form action="${ctx}/rmbs/sysfile/sysfileupload/fileToUpload" target="hidden_frame" method="post" id="clientForm" enctype="multipart/form-data">
		
		<input type="hidden" name="templateNoByUpload" id="templateNoByUpload" value="${templateNo}"/>
		<input type="hidden" name="claimIdByUpload" id="claimIdByUpload" value="${claimId}"/>


		<table id="contentTable" class="table table-striped table-bordered table-condensed">
	    <thead>
	    <tr>
		    <th style="text-align: center;">文件名称</th>
		    <th style="text-align: center;">文件类型</th>
		    <th style="text-align: center;">上传状态</th>
		    <th style="text-align: center;width: 80px;">操作</th>
	    </tr> 
	   </thead>
	    <tbody id="filecontent">
		 <c:forEach items="${fileList}" var="sysFile" varStatus="status">
				<tr>
					<td>
						${sysFile.fileName}
					</td>
					<td>
						${sysFile.fileType}
					</td>
					<td>
						${sysFile.status eq '1'?'已上传1':'未上传2'}
					</td>
					<td>
						<c:if test="${claimId  ne null}">
							<a href="#" onclick="downloadFileBtn(${sysFile.id})"><img src="${ctxStatic}/images/download.png" onMouseOver="this.src='${ctxStatic}/images/download2.png'"onMouseOut="this.src='${ctxStatic}/images/download.png'" title="下载"></a>
							<%-- <a href="${ctx}/rmbs/sysfile/sysfileupload/deletefile?fileId=${sysFile.id}" style="color: #009900;">下载</a> --%>
					  		<a href="#" onclick="deleteFileBtn(${sysFile.id})"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" ></a>
					  	</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		
</table>
<c:choose>
	<c:when test="${opt ne 'view'}">
		<table>
			<tr>
				<td colspan="4" width="5%">&nbsp;
						<div>
							<img id="tempimg" dynsrc="" src="" style="display:none" />  
							<input type="file" name="myfile" id="myfile"  onchange="loadfile(this)"/>&nbsp;
							<span id="filesize"></span>
							<button id="btnSubmit" class="btn btn-primary" onclick="uploadFile()">确认上传</button>&nbsp;
							<button id="btnCancel" class="btn btn-primary" onclick="cencelFileBtn()">取消上传</button>
						</div>
				</td>
			</tr> 
		</table>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
</form>


