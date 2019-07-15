package com.yk.pyku.util;

import com.yk.pyku.enums.BaseEnums;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @ClassName Send163MailUtil
 * @Description 163发送邮件
 * @Author yangkang
 * @Date 2019/7/15 14:12
 * @Version 1.0
 **/
public class Send163MailUtil {
    /**
     * smtp服务器
     */
    static String HOST = "smtp.163.com";
    /**
     * 发件人地址
     */
    static String FROM = "18810673903@163.com";
    /**
     * 用户名
     */
    static String USER = "18810673903@163.com";
    /**
     * 163的授权码（不是邮箱密码，需要设置）
     */
    static String PWD = "Aking201167";
    /**
     * 邮件标题
     */
    static String SUBJECT = "派库验证码";

    /**
     * 发送邮件
     */
    public static void send(String context, String[] TOS) {
        Properties props = new Properties();
        /**
         * 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
         */
        props.put("mail.smtp.host", HOST);
        /**
         * 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
         */
        props.put("mail.smtp.auth", "true");
        /**
         * 用props对象构建一个session
         */
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        /**
         * 用session为参数定义消息对象
         */
        MimeMessage message = new MimeMessage(session);
        try {
            /**
             * 加载发件人地址
             */
            message.setFrom(new InternetAddress(FROM));
            /**
             * 加载收件人地址
             */
            InternetAddress[] sendTo = new InternetAddress[TOS.length];
            for (int i = 0; i < TOS.length; i++) {
                sendTo[i] = new InternetAddress(TOS[i]);
            }
            message.addRecipients(Message.RecipientType.TO, sendTo);
            /**
             * 设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
             */
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(FROM));
            /**
             * 加载标题
             */
            message.setSubject(SUBJECT);
            /**
             * 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
             */
            Multipart multipart = new MimeMultipart();
            /**
             * 设置邮件的文本内容
             */
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(context, "text/html;charset=UTF-8");
            contentPart.setText(context);
            multipart.addBodyPart(contentPart);
            /**
             * 将multipart对象放到message中
             */
            message.setContent(multipart);
            /**
             * 保存邮件
             */
            message.saveChanges();
            /**
             * 发送邮件
             */
            Transport transport = session.getTransport("smtp");
            /**
             * 连接服务器的邮箱
             */
            transport.connect(HOST, USER, PWD);
            /**
             * 把邮件发送出去
             */
            transport.sendMessage(message, message.getAllRecipients());
            /**
             * 关闭连接
             */
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String content = String.format(BaseEnums.VERIFY_CODE_MESSAGE,"897687");
        String[] TOS = new String[]{"15210785338@163.com"};
        send(content,TOS);
    }
}
