import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {

    public static void main(String[] args) throws Exception {
        /*
         * 设置邮箱信息，此处为163邮箱
         */
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");


        Session session = Session.getInstance(prop); // 创建出与指定邮件服务器会话的session
        session.setDebug(true);
        Message message = createMessage(session); // 创建会话信息


        Transport ts = session.getTransport();
        ts.connect("m15639081168@163.com", "tigerone987"); // 连接上邮件服务器，其内部会自动帮你进行base64编码
        ts.sendMessage(message, message.getAllRecipients()); // 发送邮件目的方
        ts.close(); // 断开与服务器的连接
    }

    static String identifycode() { // 产生四位数字验证码
    	String str="0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++) {
        char ch=str.charAt(new Random().nextInt(str.length()));
        sb.append(ch);
        }
        String context = sb.toString();
        return context;
    }

    private static Message createMessage(Session session) throws AddressException, MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("m15639081168@163.com")); // 发送邮件地址
        System.out.println("请输入您要发送的邮箱：");
        Scanner sc = new Scanner(System.in);
        String target = new String();
        target=sc.next(); // 输入目的邮箱地址
        
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
        message.setSubject("轻聊验证码"); // 邮件标题
        message.setContent(identifycode(), "text/html;charset=UTF-8");//邮件内容
        message.saveChanges();
        System.out.println(identifycode());
        return message;
    }
} 