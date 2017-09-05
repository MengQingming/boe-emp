package com.boe.sysmgr.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boe.common.persistence.Page;
import com.boe.sysmgr.dao.SysMessageDao;
import com.boe.sysmgr.dao.SysMessageHisDao;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boe.common.config.Global;
import com.boe.common.service.CrudService;
import com.boe.sysmgr.entity.SysMessage;
import com.boe.sysmgr.entity.SysMessageHis;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.EmailUtil;
import com.boe.sysmgr.utils.UserUtils;

@Service
public class SysMessageService extends CrudService<SysMessageDao,SysMessage>{
	
	@Autowired
	private SysMessageHisDao sysMessageHisDao;
	@Autowired
	private SysMessageDao sysMessageDao;
	
	/**
	 * 获取消息信息
	 * @param id 消息ID
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public SysMessage readMsg(Integer id) throws IllegalAccessException, InvocationTargetException{
		SysMessage sysMessage = sysMessageDao.get(id);
		if(sysMessage!=null){
			sysMessage.setReadStatus(Global.rend);
			SysMessageHis sysMessageHis = new SysMessageHis();
			sysMessageHis.setReadTime(new Date());
			BeanUtils.copyProperties(sysMessageHis,sysMessage);
		}
		return sysMessage;
	}
	
	/**
	 * 互获取消息分页列表数据
	 * @param page
	 * @param sysMessage
	 * @return
	 */
	public Page<SysMessage> findList(Page<SysMessage> page,SysMessage sysMessage){
		sysMessage.getSqlMap().put("dsf", dataScopeFilter(sysMessage.getCurrentUser(), "o", "a"));
		return super.findPage(page,sysMessage);
	}
	
	/**
	 * 发送消息
	 * @param title
	 * @param content
	 * @param messageType
	 * @param requestNoGroup
	 * @param filePath
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public JSONObject sendMsg(String title,String content,String messageType,String requestNoGroup,String filePath,Integer resendCount) throws IllegalAccessException, InvocationTargetException{
		User user  = UserUtils.getUser();
		SysMessage sysMessage = new SysMessage();
		String requestNos[] = requestNoGroup.split(",");
		List<SysMessage> sysmsglist = new ArrayList<>();
		sysMessage.setAppNo(String.valueOf(UserUtils.getSession().getAttribute("appNo")));
		if(user.getCompany()!=null && user.getCompany().getId()!=null && user.getCompany().getId()>0){
			sysMessage.setCompanyId(user.getCompany().getId());
			sysMessage.setCompanyName(user.getCompany().getCompanyName());
			sysMessage.setCompanyNo(user.getCompany().getCompanyNo());
		}else{
			sysMessage.setCompanyId(0);
			sysMessage.setCompanyName("超级管理员");
			sysMessage.setCompanyNo("-1");
		}
		//sysMessage.setRequestId(requestId);
		sysMessage.setSenderId(user.getId());
		sysMessage.setSenderName(user.getFullName());
		sysMessage.setSenderNum(user.getUserNum());
		//sysMessage.setSenderTool(senderTool);
		sysMessage.setDistType(messageType);
		sysMessage.setDistList(requestNoGroup);
		sysMessage.setMsgTitle(title);
		sysMessage.setMsgBody(content);
		sysMessage.setMaxTryTimes(resendCount);
		sysMessage.setCurrTryTimes(1);
		sysMessage.setReadStatus(Global.unread);
		//sysMessage.setReadTime(null);
		if(filePath!=null && !filePath.equals("")){
			sysMessage.setHasAttachement(Global.YES);
			sysMessage.setAttachfilePath(filePath);
		}else{
			sysMessage.setHasAttachement(Global.NO);
		}
		//如果消息类型为站内短信，则直接将信息存入信息表中
		if(messageType.equals("site")){
			sysMessage.setSendStatus(Global.SEND_SUCCESS);
		}else{
			sysMessage.setSendStatus(Global.SENDING);
		}
		for(String requestNo : requestNos){
			SysMessage addmsg = new SysMessage();
			BeanUtils.copyProperties(addmsg,sysMessage);
			//设置发送时间
			addmsg.setSendTime(new Date());
			//设置接受者账号
			addmsg.setDistList(requestNo);
			sysMessageDao.insert(addmsg);
			sysmsglist.add(addmsg);
		}
		JSONObject json = new JSONObject();
		json.put("messageType", "site");
		if(messageType.equals("site")){
			return json;
		}
		//如果不是站内短信，则逐条调起服务，发送短信
		JSONArray s_jsona = new JSONArray();//存储成功发送的记录
		JSONArray f_jsona = new JSONArray();//存储失败记录
		for(int i=0;i<sysmsglist.size();i++){
			if(messageType.equals("sms")){
				//在此预留短信发送业务代码区
			}else if(messageType.equals("email")){
				SysMessage wsmsg = sysmsglist.get(i);
				EmailUtil sendmail = new EmailUtil(wsmsg.getDistList(),title,content);
				//添加附件
				if(filePath!=null && !filePath.equals("")){
					String[] files = filePath.split(",");
					for(String fileP : files){
						sendmail.attachfile(fileP.trim());
					}
				}
				Boolean b = sendmail.sendMail();
				if(b){
					JSONObject re_j = sendSuccess(wsmsg);
					s_jsona.add(re_j);
				}else{
					JSONObject re_j = sendFail(wsmsg);
					f_jsona.add(re_j);
					
				}
			}
		}
		json.put("success", s_jsona);
		json.put("fail", f_jsona);
		return json;
	}
	/**
	 * 重新发送接口
	 * @param msgIdGroup 多个ID直接可以用因为逗号隔开","
	 * @return json
	 */
	public JSONObject resendMsg(String msgIdGroup,String messageType){
		JSONObject json = new JSONObject();
		String[] msgIds = msgIdGroup.split(",");
		if(msgIds.length>0){
			JSONArray s_jsona = new JSONArray();//存储成功发送的记录
			JSONArray f_jsona = new JSONArray();//存储失败记录
			JSONArray n_jsona = new JSONArray();//存储再发送记录
			for(String msgId : msgIds){
				SysMessage sysMessage = sysMessageDao.get(Integer.valueOf(msgId));
				if(sysMessage!=null && sysMessage.getMaxTryTimes()>sysMessage.getCurrTryTimes()){
					if(messageType.equals("sms")){
						//在此预留短信发送业务代码区
					}else if(messageType.equals("email")){
						EmailUtil sendmail = new EmailUtil(sysMessage.getDistList(),sysMessage.getMsgTitle(),sysMessage.getMsgBody());
						String filePath = sysMessage.getAttachfilePath();
						//添加附件
						if(filePath!=null && !filePath.equals("")){
							String[] files = filePath.split(",");
							for(String fileP : files){
								sendmail.attachfile(fileP);
							}
						}
						Boolean b = sendmail.sendMail();
						if(b){
							JSONObject re_j = sendSuccess(sysMessage);
							s_jsona.add(re_j);
						}else{
							JSONObject re_j = sendFail(sysMessage);
							f_jsona.add(re_j);
						}
					}
				}else{
					JSONObject n_json = new JSONObject();
					n_json.put("msgId", sysMessage.getId());
					n_json.put("receiver", sysMessage.getDistList());
					n_jsona.add(n_json);
				}
			}
			json.put("messageType", messageType);
			json.put("success", s_jsona);
			json.put("fail", f_jsona);
			json.put("cannotSend", n_jsona);
		}
		return json;
	}
	
	/**
	 * 信息发送第三方成功
	 * @param wsmsg
	 * @return
	 */
	private JSONObject sendSuccess(SysMessage wsmsg){
		JSONObject json = new JSONObject();
		try {
			wsmsg.setSendStatus(Global.SEND_SUCCESS);
			wsmsg.setCurrTryTimes(wsmsg.getCurrTryTimes()+1);
			//更新信息发送状态
			sysMessageDao.update(wsmsg);
			//添加信息到信息HIS表中
			SysMessageHis sysMessageHis = new SysMessageHis();
			BeanUtils.copyProperties(sysMessageHis,wsmsg);
			sysMessageHis.setSendTime(new Date());
			sysMessageHis.setId(null);
			sysMessageHisDao.insert(sysMessageHis);
			
			json.put("msgId", wsmsg.getId());
			json.put("receiver", wsmsg.getDistList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 信息发送第三方失败
	 * @param wsmsg
	 * @return
	 */
	private JSONObject sendFail(SysMessage wsmsg){
		JSONObject json = new JSONObject();
		try {
			wsmsg.setSendStatus(Global.SEND_SUCCESS);
			wsmsg.setCurrTryTimes(wsmsg.getCurrTryTimes()+1);
			//更新信息发送状态
			sysMessageDao.update(wsmsg);
			json.put("msgId", wsmsg.getId());
			json.put("receiver", wsmsg.getDistList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
