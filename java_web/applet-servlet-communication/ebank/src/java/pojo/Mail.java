/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import javax.mail.internet.InternetAddress;

/**
 *
 * @author pythonee
 */
public class Mail {
    private String mail_to;
    private String subject;
    private String msg;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMail_to() {
        return mail_to;
    }

    public void setMail_to(String mail_to) {
        this.mail_to = mail_to;
    }

}
