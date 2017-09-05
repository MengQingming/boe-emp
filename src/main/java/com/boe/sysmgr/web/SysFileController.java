/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.boe.sysmgr.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.entity.SysFile;
import com.boe.sysmgr.service.SysFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.utils.FileUtils;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.SysFileModel;
import com.boe.sysmgr.entity.SysParameter;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.service.SysParameterService;
import com.boe.sysmgr.utils.UserUtils;



/**
 * 文件上传Controller
 * @author zhou
 * @version 2016-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sysfile/sysfileupload")
public class SysFileController extends BaseController {

	@Autowired
	private SysFileService sysFileService;
	@Autowired
	private CacheComponent cacheComponent;
	@Autowired
	private SysParameterService sysParameterService;
	
	/**
	 * 查看
	 * @param conid
	 * @param sysFile
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("file:sysFile:edit")
	@RequestMapping(value = "list")
	public String list(String conid,SysFile sysFile, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		/*附件操作开始*/
		//获取当前登录人所在的  appno
		String appNo=getAppNo();
		//获取所以上传的附件   根据appNo  报账单的id
		List<SysFile> fileList=sysFileService.queryByAppNoAndDataSourceKey("", conid.toString(), appNo);
		//将报账单的id传值到页面，方便上传附件时候可以存储附件的dataSourceKey
		model.addAttribute("claimId",conid);
		/*附件操作结束*/
		model.addAttribute("fileList", fileList);
		return "modules/sys/fileupload/uploadFileList";
	}
	/**
	 * @description: 
	 * @param  vendorName
	 * @return json格式 数据
	 */
	@ResponseBody
	@RequestMapping(value=("findList"),method=RequestMethod.POST)
	public String findList(String conid){
		JSONObject json = new JSONObject();
		if(null==conid || conid.equals("")){
			json.put("status", false);
			return json.toString();
		}
		/*附件操作开始*/
		//获取当前登录人所在的  appno
		String appNo=getAppNo();
		//获取所以上传的附件   根据appNo  报账单的id
		List<SysFile> fileList=sysFileService.queryByAppNoAndDataSourceKey("", conid.toString(), appNo);
		json.put("row", fileList);
		json.put("status", true);
		return json.toString();
	}

	/**
	 *保存上传的附件
	 * @param myfile
	 * @param clientPath
	 * @param claimId
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "fileToUpload")
	public String fileToUpload(@RequestParam(value="myfile")MultipartFile myfile,String clientPath,
			@RequestParam(value="claimIdByUpload",defaultValue="",required=true)String claimId,
			@RequestParam(value="templateNoByUpload",defaultValue="",required=true)String templateNo,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<SysParameter> parameters = null;
		if(System.getProperty("os.name").toLowerCase().startsWith("win")){  //window
			parameters = sysParameterService.findByGroupStatusName("Windows_Path", "global", "1");
		}else{//Linux
			parameters = sysParameterService.findByGroupStatusName("Linux_Path", "global", "1");
		}
		//SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		//String category = "RMBS";
		/** 得到文件保存目录的真实路径* */
		//String logoRealPathDir= request.getSession().getServletContext().getRealPath(File.separator)+"upload"+File.separator+templateNo+File.separator+dateformat.format(new Date())+File.separator+claimId;
		String logoRealPathDir= parameters.get(0).getParamValue()+File.separator+templateNo+File.separator+claimId;

		/** 根据真实路径创建目录* */
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists()){
			logoSaveFile.mkdirs();
		}
		/** 获取文件的后缀* */
		String suffix = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
		/** 使用UUID生成文件名称* */
		//String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
		/** 拼成完整的文件保存路径加文件* */
		String filePath = logoRealPathDir + File.separator + myfile.getOriginalFilename();
		//查询系统该参数  设置的最大上传附件
		List<SysParameter> params = cacheComponent.getSysParam("uplaodfile_maxsize");
		long maxFileSize = 2;//默认是2M;
		for(SysParameter sysParameter : params){
			maxFileSize = Long.parseLong(sysParameter.getParamValue());
		}
		//设置最大
		long maxsize =maxFileSize*1024*1024;     //10485760 5M
		long size = myfile.getSize();//文件大小
		//判断大小
		if(maxsize<size){
			request.setAttribute("status", "overSize");
			request.setAttribute("size",maxsize/1024/1024+"M");
			return "modules/sys/fileupload/uploadCallback";
		}

		//判断是否重复
		//根据claimid和文件路径进行匹配
		List<SysFile> list=sysFileService.checkFileIsExist(filePath, claimId);
		if(list!=null && list.size()>0){
			request.setAttribute("status", "exist");
			return "modules/sys/fileupload/uploadCallback";
		}

		File file = new File(filePath);
		try {
			myfile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}


		String orgFilename =  myfile.getOriginalFilename();
		//String name =  myfile.getName();//input的name
		//String contentType = myfile.getContentType();
		//PrintWriter writer = response.getWriter();

		response.setCharacterEncoding("UTF-8");//这两句是必须的，一定要加

		// 文件上传成功后
		User user=UserUtils.getUser();
		SysFile sysFile=new SysFile();
		sysFile.setCompanyId(user.getCompany().getId());
		sysFile.setCompanyNo(user.getCompany().getCompanyNo());
		sysFile.setCompanyName(user.getCompany().getCompanyName());
		if(sysFile.getCreationDate()==null){
			sysFile.setCreationDate(new Date());
		}
		sysFile.setLastUpdateDate(new Date());
		sysFile.setFileName(orgFilename);
		sysFile.setFileType(suffix);
		sysFile.setServerPath(filePath);
		sysFile.setStatus("1");
		sysFile.setDataSource("1");
		sysFile.setDataSourceKey(claimId);
		sysFile.setAppNo(getAppNo());
		//		sysFile.setClientPath(clientPath);
		sysFile.setClientPath(myfile.getOriginalFilename());
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		sysFile.setClientIp(ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip);
		sysFileService.save(sysFile);

		//获取列表json
		request.setAttribute("status", "success");
		request.setAttribute("data", getListjson(claimId));
		return "modules/sys/fileupload/uploadCallback";
	}
	

	
	
	/**
	 * 下载
	 * @param fileId
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("file:sysFile:edit")
	@RequestMapping(value = "downloadfile")
	public void downloadfile(String fileId, HttpServletRequest request,
			HttpServletResponse response) {
		SysFile file= sysFileService.get(Integer.parseInt(fileId));
		//根據id查询出该记录   然后获取名称
		String fileName=file.getFileName();
		File downfile = new File(file.getServerPath());
	    FileUtils.downFile(downfile, request, response, fileName);
	}
	
	

	@ResponseBody
	@RequestMapping(value = "deletefile",method=RequestMethod.POST)
	public String deletefile(String id,String claimId) {
		SysFile sysFile=null;
		if(id!=null)
			sysFile=sysFileService.get(Integer.parseInt(id));
		if(sysFile!=null){
			//删除文件
			 File file = new File(sysFile.getServerPath());//D:\\tomcat-64\\webapps\\xxwfssc\\upload\\2016122914\\Tark_FSSC_设计文件_公共_开发指南_V0.1.doc
			   if(file.isFile() && file.exists()) {
				   file.delete();//"删除单个文件"+name+"成功！"
				   //删除文件列表的数据
				   sysFileService.delete(sysFile);
					//获取列表json
					 return getListjson(claimId);
			   }else{//"删除单个文件"+name+"失败！"
				   return "false";
			   }
			//
		}else{//"删除单个文件"+name+"失败！"
			   return "false";
		 }
		
	
	}
	
	@ResponseBody
	@RequestMapping(value = "getFileList")
	public String getFileList(@RequestParam(value="claimNo",required=true,defaultValue="")String claimId){
		return getListjson(claimId);
	}

	private String getListjson(String claimId){
		JSONObject json = new JSONObject();
		//获取返回json （列表）
		/*附件操作开始*/
		//获取当前登录人所在的  appno
		String appNo=getAppNo();
		//获取所以上传的附件   根据appNo  报账单的id
		List<SysFile> fileList=sysFileService.queryByAppNoAndDataSourceKey("", claimId.toString(), appNo);
		List<SysFileModel> fileModelList  = new ArrayList<>();
		if(fileList.size()>0){
			for(SysFile sv : fileList){
				SysFileModel model = new SysFileModel();
				model.setId(sv.getId());
				model.setCompanyId(sv.getCompanyId());
				model.setCompanyNo(sv.getCompanyNo());
				model.setCompanyName(sv.getCompanyName());
				model.setCreationDate(sv.getCreationDate());
				model.setLastUpdateDate(sv.getLastUpdateDate());
				model.setFileName(sv.getFileName());
				model.setFileType(sv.getFileType());
				model.setServerPath(sv.getServerPath());
				model.setStatus(sv.getStatus());
				model.setDataSource(sv.getDataSource());
				model.setDataSourceKey(sv.getDataSourceKey());
				model.setAppNo(sv.getAppNo());
				fileModelList.add(model);
			}
			json.put("row", fileModelList);
			json.put("status", true);
		}else{
			json.put("status", "noData");
		}
		return json.toString();
	}

}