/*
 * 本代码所有权归作者所有 但在保持源代码不被破坏以及所有人署名的基础上 任何人可自由无限使用
 */
package com.mashup.utils;

import java.io.InputStream;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.Assert;

/**
 * 
 * 对Springframework MailSender和JavaMailSender的装饰, 实现装饰器模式。<p><p>
 * 
 * 客户程序按照MailSender或JavaMailSender接口的方式向PooledMailSender表示要发送邮件，
 * 然后立即返回(PooledMailSender没有保证此时邮件马上发送)。PooledMailSender将要发送
 * 邮件进行排队，按FIFO方式发送得到的邮件消息。<p><p>
 * 
 * 邮件如果发送失败，将被废弃，不重复发送。<p><p>
 * 
 * 使用PooledMailSender，需要注入一个真正发送邮件的MailSender或JavaMailSender，当PooledMailSender
 * 认为该发送邮件时，实际调用注入的MailSender或JavaMailSender处理。<p><p>
 * 
 * 当注入的是MailSender时，仅可以使用MailSender的两个邮件发送方法，当注入的是JavaMailSender时，可以
 * 使用JavaMailSender额外声明的邮件发送方法。<p><p>
 * 
 * 邮件发送详细规定，请参考SpringFramework的规定。<p><p>
 * 
 * 本类的职责在于排队邮件，将邮件代理给实际的MailSender发送。<p><p>
 * 
 * 在Spring Context中配置本类时，"最好"配置destroy-method="close"，不过这不是必须的。<p><p>
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @see org.springframework.mail.MailSender
 * @see org.springframework.mail.javamail.JavaMailSender
 *
 */
public class PooledMailSender implements MailSender, JavaMailSender,
		Runnable {

	// -------------------------------------------------------------------------

	/**
	 * 实际发送邮件的邮件发送器，可以是MailSender，或JavaMailSender
	 */
	private MailSender mailSender;
	
	// -------------------------------------------------------------------------
	
	/**
	 * 日志
	 */
	protected static final Log log = LogFactory
			.getLog(PooledMailSender.class);

	
	/**
	 * 邮件排队点号、发送线程，
	 */
	private Thread thread;

	/**
	 * 邮件排队队列
	 */
	private Queue<Object> queue = new LinkedBlockingQueue<Object>();
	
	/**
	 * 锁，仅此而已
	 *  
	 * @see #run()
	 * @see #add(Object)
	 */
	private Object mutex = new Object();

	// -------------------------------------------------------------------------

	/**
	 * 构造本类对象，同时启动侦听邮件的到达。
	 * 
	 * 如果要阻止侦听和发送，应该调用close方法，在Spring的Context中"最好"配置destroy-method="close"，不过这不是必须的。
	 * 
	 */	
	public PooledMailSender() {
		thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

    // -------------------------------------------------------------------------
    
	/**
	 * 邮件发送循环，它被作为thread runnable的run实现。
	 * 
	 * @see #add(Object)
	 * 
	 */
	public void run() {
		while (!isClose()) {
			if (!isEmpty()) {
				Object object = poll();
				try {
					//执行实际发送
					doSend(object);
				} catch (Exception ex) {
					log.error(ex);
					ex.printStackTrace();
				}
			}
			//等~直到add方法的通知!
			synchronized (mutex) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
				}
			}
		}
	}

	// -------------------------------------------------------------------------

	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * 设置实际的邮件发送器
	 * 
	 * @param mailSender
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


	// -------------------------------------------------------------------------

	/**
	 * 关闭PooledMailSender!
	 * 
	 * 关闭的PooledMailSender不可再接收和发送邮件
	 */
	public void close() {
		queue.clear();
		queue = null;
	}

	/**
	 * 已经关闭？
	 * 
	 * 关闭的PooledMailSender不可再接收和发送邮件
	 * 
	 * @return
	 */
	public boolean isClose() {
		return queue == null;
	}

	// -------------------------------------------------------------------------
	// 邮件排队机操作方法代理

	protected boolean isEmpty() {
		return queue != null && queue.isEmpty();
	}
	
	/**
	 * 邮件加入排队机
	 * 
	 * @param obj
	 * @see #run()
	 */
	protected void add(Object obj) {
		Assert.notNull(queue);
		queue.add(obj);
		//我来了!
		synchronized (mutex) {
			mutex.notify();
		}
	}

	protected Object poll() {
		Assert.notNull(queue);
		return queue.poll();
	}

	protected Object peek() {
		Assert.notNull(queue);
		return queue.peek();
	}



	// -------------------------------------------------------------------------
	// MailSender接口实现-将邮件放入排队机
	
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		add(simpleMessage);
	}

	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		for (SimpleMailMessage message : simpleMessages) {
			add(message);
		}
	}
	
	
	// -------------------------------------------------------------------------
	// JavaMailSender接口实现-将邮件放入排队机
	
	public void send(MimeMessage mimeMessage) throws MailException {
		add(mimeMessage);
	}

	public void send(MimeMessage[] mimeMessages) throws MailException {
		for (MimeMessage message : mimeMessages) {
			add(message);
		}
	}

	public void send(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		add(mimeMessagePreparator);
	}

	public void send(MimeMessagePreparator[] mimeMessagePreparators)
			throws MailException {
		for (MimeMessagePreparator preparator : mimeMessagePreparators) {
			add(preparator);
		}
	}

	public MimeMessage createMimeMessage() {
		return ((JavaMailSender) mailSender).createMimeMessage();
	}

	public MimeMessage createMimeMessage(InputStream contentStream)
			throws MailException {
		return ((JavaMailSender) mailSender).createMimeMessage(contentStream);
	}

	// -------------------------------------------------------------------------
    // 实际发送代理方法
	
	public void doSend(Object object) {
        if (object instanceof SimpleMailMessage) {
            doSend((SimpleMailMessage) object);
        } else if (object instanceof MimeMessage) {
            doSend((MimeMessage) object);
        } else if (object instanceof MimeMessagePreparator) {
            doSend((MimeMessagePreparator) object);
        }
    }

	public void doSend(SimpleMailMessage simpleMessage) throws MailException {
		mailSender.send(simpleMessage);
	}
	
	public void doSend(MimeMessage mimeMessage) throws MailException {
		((JavaMailSender) mailSender).send(mimeMessage);
	}

	public void doSend(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		((JavaMailSender) mailSender).send(mimeMessagePreparator);
	}


}
