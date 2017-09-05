package com.boe.sysmgr.utils;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.boe.common.utils.SpringContextHolder;
import com.boe.sysmgr.cache.CacheComponent;
import com.boe.sysmgr.entity.SysParameter;

/**
 * <p>
 * Title: 使用javamail发送邮件
 * </p>
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class EmailUtil {
	private static CacheComponent cacheComponent = SpringContextHolder.getBean(CacheComponent.class);
	private String host="";
	private String to = "";// 收件人
	private String from = "";// 发件人
	private String username = "";
	private String password = "";
	private String filename = "";// 附件文件名
	private String subject = "";// 邮件主题
	private String content = "";// 邮件正文
	private Vector file = new Vector();// 附件文件集合
	private Properties props = new Properties();
	/**
	 * <br>
	 * 方法说明：默认构造器 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public EmailUtil() {
		
	}

	/**
	 * <br>
	 * 方法说明：构造器，提供直接的参数传入 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public EmailUtil(String to,String subject,String content) {
		//从缓存中获取配置信息
		List<SysParameter> params = cacheComponent.getSysParam("email_message_config");
		String thost="smtp.qq.com";
		String tport="465";
		String tfrom="839160103@qq.com";
		String tusername="839160103@qq.com";
		String tpassword="upqpmjmgslezbgac";
		for(SysParameter sysParameter : params){
			String paramn = sysParameter.getParamName();
			String paramv = sysParameter.getParamValue();
			if(paramn.equals("host")){
				thost = paramv;
			}else if(paramn.equals("port")){
				tport = paramv;
			}else if(paramn.equals("from")){
				tfrom = paramv;
			}else if(paramn.equals("username")){
				tusername = paramv;
			}else if(paramn.equals("password")){
				tpassword = paramv;
			}
		}
		props.put("mail.smtp.host", thost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", tport);
		props.put("mail.smtp.socketFactory.port", tport);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		this.host = thost;
		this.from = tfrom;
		this.username = tusername;
		this.password = tpassword;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件服务器地址 <br>
	 * 输入参数：String host 邮件服务器地址名称 <br>
	 * 返回类型：
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * <br>
	 * 方法说明：设置登录服务器校验密码 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setPassWord(String pwd) {
		this.password = pwd;
	}

	/**
	 * <br>
	 * 方法说明：设置登录服务器校验用户 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setUserName(String usn) {
		this.username = usn;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件发送目的邮箱 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件发送源邮箱 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件主题 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * <br>
	 * 方法说明：设置邮件内容 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * <br>
	 * 方法说明：把主题转换为中文 <br>
	 * 输入参数：String strText <br>
	 * 返回类型：
	 */
	public String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"GB2312"), "GB2312", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	/**
	 * <br>
	 * 方法说明：往附件组合中添加附件 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void attachfile(String fname) {
		file.addElement(fname);
	}

	/**
	 * <br>
	 * 方法说明：发送邮件 <br>
	 * 输入参数： <br>
	 * 返回类型：boolean 成功为true，反之为false
	 */
	public boolean sendMail() {

		// 构造mail session
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// 构造MimeMessage 并设定基本的值
			MimeMessage msg = new MimeMessage(session);
			//MimeMessage msg = new MimeMessage();
			msg.setFrom(new InternetAddress(from));
		 
			
			//msg.addRecipients(Message.RecipientType.TO, address); //这个只能是给一个人发送email
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to)) ;
			subject = transferChinese(subject);
			msg.setSubject(subject);

			// 构造Multipart
			Multipart mp = new MimeMultipart();

			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(content, "text/html;charset=gb2312");
			
			// 向MimeMessage添加（Multipart代表正文）
			mp.addBodyPart(mbpContent);

			// 向Multipart添加附件
			Enumeration efile = file.elements();
			while (efile.hasMoreElements()) {

				MimeBodyPart mbpFile = new MimeBodyPart();
				filename = efile.nextElement().toString();
				FileDataSource fds = new FileDataSource(filename);
				mbpFile.setDataHandler(new DataHandler(fds));
				/*//从服务器获取文件资源
				java.net.URL url = new URL("");
				DataHandler dh = new DataHandler(url);*/
				//<span style="color: #ff0000;">//这个方法可以解决附件乱码问题。</span>	
				String filename= new String(fds.getName().getBytes(),"ISO-8859-1");

				mbpFile.setFileName(filename);
				// 向MimeMessage添加（Multipart代表附件）
				mp.addBodyPart(mbpFile);

			}

			file.removeAllElements();
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			msg.setSentDate(new Date());
			msg.saveChanges() ;
			// 发送邮件
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}


	
	/**
	 * <br>
	 * 方法说明：主方法，用于测试 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
 	public static void main(String[] args) {
 		EmailUtil sendmail = new EmailUtil();
 		
 		sendmail.setUserName("839160103@qq.com");
 		sendmail.setPassWord("upqpmjmgslezbgac");
 		sendmail.setTo("15201117897@163.com");
 		sendmail.setFrom("839160103@qq.com");
 		sendmail.setSubject("你好，这是测试！");
 		sendmail.setContent("你好这是一个带多附件的测试！");
		// Mail sendmail = new Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","你好","胃，你好吗？");
 		sendmail.attachfile("f:\\cs.png");
 		
 		System.out.println(sendmail.sendMail());

 	}
}

