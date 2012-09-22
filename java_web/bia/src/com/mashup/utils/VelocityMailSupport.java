/*
 * 本代码所有权归作者所有 但在保持源代码不被破坏以及所有人署名的基础上 任何人可自由无限使用
 */
package com.mashup.utils;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 
 * 利用本类可方便发送由Velocity模板生成的邮件内容。
 * 
 * 客户程序可以组合或继承本类，使具有发送邮件功能。
 * 
 * 本类仅提供一些基本信息的配置和获取方法、veloticy模板文本生成方法、mailSender配置和获取方法
 * 相当于一个配置简化器，不是模板模式的实现。
 * 
 * 实际发送邮件时，由客户程序负责利用本类组成要发送的邮件，调用本类提供的mailSender发送。
 * 
 * @author zhiliang.wang@yahoo.com.cn
 *
 */
public class VelocityMailSupport implements MailSender, JavaMailSender{

	// -----------------------------------------------------------------------------
	
	private Properties mailHeaders = new Properties();

	/**
	 * 邮件发送者，包括发送者姓名和地址，用于设置在邮件的from栏目中
	 */
	private String from;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件内容模板地址/名称
	 */
	private String templateName;

	/**
	 * velocity引擎
	 */
	private VelocityEngine velocityEngine;

	/**
	 * mail发送器
	 */
	private MailSender mailSender;

	// -----------------------------------------------------------------------------
	
	/**
	 * 日志
	 */
	protected static final Log log = LogFactory
			.getLog(VelocityMailSupport.class);
	
	// -----------------------------------------------------------------------------

	/**
	 * 使用提供的数据，套入velocity模板，生成最后文本信息。
	 * 
	 * 大部分情况下，这个信息应该就是邮件的内容。
	 */
	public String renderText(Map model) throws VelocityException {
		return VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
				getTemplateName(), model);
	}
	

	// -----------------------------------------------------------------------------
	

	public Properties getMailHeaders() {
		return mailHeaders;
	}


	/**
	 * 设置非空mime hader，一般可以利用此设置contentType等
	 * @param mailHeaders
	 */
	public void setMailHeaders(Properties mailHeaders) {
		this.mailHeaders = mailHeaders;
	}
	
	
	public String getFrom() {
		return this.from;
	}
	
	public Address getFromAddress() throws AddressException {
		return new InternetAddress(this.from);
	}

	/**
	 * 设置邮件发送者，包括发送者姓名和地址，用于设置在邮件的from栏目中
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置邮件内容模板地址/名称
	 * 
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * 获取mail发送器，发送邮件。
	 * 
	 * @return
	 */
	public MailSender getMailSender() {
		return mailSender;
	}
	
	/**
	 * 获取下层配置的mail发送器，发送邮件, 该mail发送器必须是JavaMailSender，否则抛出CaseException。
	 * 
	 * @return
	 */
	public JavaMailSender getJavaMailSender() {
		return (JavaMailSender)mailSender;
	}

	/**
	 * 设置mail发送器
	 * 
	 * @param mailEngine
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	//----------------------------------------------------------------------------------
	
	/**
	 * 
	 * 发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送邮件，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param nameOfTo 邮件接收收人姓名 或 昵称
	 * @param emailOfTo 邮件接收人邮件地址
	 * @param model 邮件velocity模板中变量的值
	 * @throws MailException
	 */
	public void sendMime(String nameOfTo, String emailOfTo, Map<String, Object> model) throws MailException {
		sendMime(nameOfTo + "<" + emailOfTo + ">", model);
	}

	
	/**
	 *  发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送邮件，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param to 邮件接收人邮件地址以及可能的收件人姓名或昵称
	 * @param model 邮件velocity模板中变量的值
	 */
	public void sendMime(String to, Map<String, Object> model) throws MailException {
		sendMime(mergeSimpleMessage(to, model));
	}
	

	/**
	 * 发送Mime邮件简易方法。
	 * 
	 * 以Mime的方式发送SimpleMailMessage，这主要是为能够支持发送html或类似功能（非简单文本）的邮件
	 * 
	 * @param simpleMessage
	 * @throws MailException
	 */
	public void sendMime(SimpleMailMessage simpleMessage) throws MailException {
		send(toMimeMessage(simpleMessage));
	}
	

	//----------------------------------------------------------------------------------
	
	public SimpleMailMessage mergeSimpleMessage(String to, Map<String, Object> model) {
		//render text of mail from velocity template with the data
		String text = null;
		try {
			text = renderText(model);
		} catch (VelocityException e) {
			log.error(e);
			e.printStackTrace();
		}
		
		//mail message setting
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(getSubject());
		message.setFrom(getFrom());
		message.setTo(to);
		message.setText(text);
		return message;
	}
	
	/**
	 * 
	 * @param simpleMailMessage
	 * @return
	 */
	public MimeMessage toMimeMessage(SimpleMailMessage simpleMailMessage) {
		MimeMessage mimeMessage = createMimeMessage();	
		MimeMailMessage mmm = new MimeMailMessage(mimeMessage);
		simpleMailMessage.copyTo(mmm);
		return mmm.getMimeMessage();
			
	}

	//----------------------------------------------------------------------------------
	// MailSender接口实现 - 代理到配置的实际mailSender


	public void send(SimpleMailMessage simpleMessage) throws MailException {
		getMailSender().send(simpleMessage);
	}


	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		getMailSender().send(simpleMessages);
	}

	
	//----------------------------------------------------------------------------------
	// JavaMailSender接口实现 - 代理到配置的实际mailSender(实际配置必须是JavaMailSender的实现才可以)

	public MimeMessage createMimeMessage() {
		return getJavaMailSender().createMimeMessage();		
	}


	public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
		return getJavaMailSender().createMimeMessage(contentStream);		
	}


	public void send(MimeMessage mimeMessage) throws MailException {
		injectMailHeader(mimeMessage);
		getJavaMailSender().send(mimeMessage);
	}


	public void send(MimeMessage[] mimeMessages) throws MailException {
		for (MimeMessage message : mimeMessages) {
			injectMailHeader(message);
		}
		getJavaMailSender().send(mimeMessages);
		
	}


	public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
		getJavaMailSender().send(mimeMessagePreparator);		
	}


	public void send(MimeMessagePreparator[] mimeMessagePreparators) throws MailException {
		getJavaMailSender().send(mimeMessagePreparators);		
	}
	
	
	protected void injectMailHeader(MimeMessage mm){
		for (Object name : mailHeaders.keySet()) {
			try {
				mm.setHeader((String)name, mailHeaders.getProperty((String)name));
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
	}
}
