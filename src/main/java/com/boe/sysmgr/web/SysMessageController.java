package com.boe.sysmgr.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.service.SysMessageHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.boe.common.web.BaseController;
import com.boe.sysmgr.entity.SysMessage;
import com.boe.sysmgr.entity.SysMessageHis;
import com.boe.sysmgr.entity.SysParameter;
import com.boe.sysmgr.service.SysMessageService;

/**
* <p>Description:统一消息</p>
* <p>Company:T-ark </p>
* @created: 2016-12-21
* @version: 2.0
*/
@Controller
@RequestMapping(value = "${adminPath}/sys/message")
public class SysMessageController extends BaseController{
	
	@Autowired
	private SysMessageHisService sysMessageHisService;
	@Autowired
	private SysMessageService sysMessageService;
	@Autowired
	private CacheComponent cacheComponent;
	/**
	 * 发送消息
	 *@param title 消息标题
	 *@param content 消息内容
	 *@param messageType 在线 site 短信 sms 邮件 email
	 *@param requestNo 接收消息账号，多个账号直接可用英文逗号隔开","
	 *@param filePath 接收消息账号，多个账号直接可用英文逗号隔开","
	 *@return  String
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping("sendMessage")
	public String sendMessage(String title,String content,String messageType,String requestNoGroup,String filePath) throws IllegalAccessException, InvocationTargetException{
		JSONObject json = new JSONObject();
		if(title==null || title.equals("")){
			json.put("status", false);
			json.put("msg", "标题不能为空");
			return json.toJSONString();
		}
		if(content==null || content.equals("")){
			json.put("status", false);
			json.put("msg", "消息内容不能为空");
			return json.toJSONString();
		}
		if(messageType==null || messageType.equals("")){
			json.put("status", false);
			json.put("msg", "消息类型不能为空");
			return json.toJSONString();
		}
		if(requestNoGroup==null || requestNoGroup.equals("")){
			json.put("status", false);
			json.put("msg", "接收账号不能为空");
			return json.toJSONString();
		}
		List<SysParameter> params = cacheComponent.getSysParam("email_message_config");
		Integer resendCount = 0;
		for(SysParameter sysParameter : params){
			String paramn = sysParameter.getParamName();
			String paramv = sysParameter.getParamValue();
			if(paramn.equals("resend_count") && paramv!=null){
				resendCount =Integer.parseInt(paramv);
			}
		}
		json = sysMessageService.sendMsg(title, content, messageType, requestNoGroup, filePath,resendCount);
		json.put("status", true);
		json.put("msg", "消息发送处理完成");
		return json.toJSONString();
	}
	/**
	 * 短信或电子邮件消息重新发送
	 * @param msgIdGroup 短信ID，多个短信ID，用英文逗号隔开
	 * @param messageType 信息类型 重发接口之支持 手机短信(sms)和电子邮件重发(email)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("resendMessage")
	public String resendMessage(String msgIdGroup,String messageType){
		JSONObject json = new JSONObject();
		if(msgIdGroup==null || msgIdGroup.equals("")){
			json.put("status", false);
			json.put("msg", "消息ID不能为空");
			return json.toJSONString();
		}
		if(messageType==null || messageType.equals("")){
			json.put("status", false);
			json.put("msg", "消息类型不能为空");
			return json.toJSONString();
		}
		json = sysMessageService.resendMsg(msgIdGroup, messageType);
		json.put("status", true);
		json.put("msg", "消息重发完毕");
		return json.toJSONString();
	}
	
	/**
	 * 消息列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("messageList")
	public String messageList(String messageStatus,HttpServletRequest request, HttpServletResponse response){
		JSONObject json = new JSONObject();
		SysMessage sysMessage = new SysMessage();
		SysMessageHis sysMessageHis = new SysMessageHis();
		sysMessage.setDistType("site");
		sysMessageHis.setDistType("site");
		//支持根据公司，发送人，接收者查询
		
		/*if(messageStatus.equals("read")){
			Page<SysMessage> page = sysMessageService.findList(new Page<SysMessage>(request, response),sysMessage);
		}else if(messageStatus.equals("unread")){
			Page<SysMessageHis> page = sysMessageHisService.findList(new Page<SysMessageHis>(request, response),sysMessageHis);
		}*/
		if(messageStatus.equals("unread")){
			List<SysMessage> list = sysMessageService.findList(sysMessage);
			json.put("row", list);
		}else if(messageStatus.equals("read")){
			List<SysMessageHis> list = sysMessageHisService.findList(sysMessageHis);
			json.put("row", list);
		}
		return json.toJSONString();
	}
	
	/**
	 * 消息列表
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping("readMessage")
	public String readMessage(Integer msgId) throws IllegalAccessException, InvocationTargetException{
		JSONObject json = new JSONObject();
		if(msgId==null || msgId<=0){
			json.put("status", false);
			json.put("msg", "消息ID不能为空");
			return json.toJSONString();
		}
		SysMessage sysMessage =sysMessageService.readMsg(msgId);
		json.put("status", true);
		json.put("msg", "返回成功");
		json.put("result", sysMessage);
		return json.toJSONString();
	}
	
}
