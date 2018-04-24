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
         * ����������Ϣ���˴�Ϊ163����
         */
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");


        Session session = Session.getInstance(prop); // ��������ָ���ʼ��������Ự��session
        session.setDebug(true);
        Message message = createMessage(session); // �����Ự��Ϣ


        Transport ts = session.getTransport();
        ts.connect("m15639081168@163.com", "tigerone987"); // �������ʼ������������ڲ����Զ��������base64����
        ts.sendMessage(message, message.getAllRecipients()); // �����ʼ�Ŀ�ķ�
        ts.close(); // �Ͽ��������������
    }

    static String identifycode() { // ������λ������֤��
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
        message.setFrom(new InternetAddress("m15639081168@163.com")); // �����ʼ���ַ
        System.out.println("��������Ҫ���͵����䣺");
        Scanner sc = new Scanner(System.in);
        String target = new String();
        target=sc.next(); // ����Ŀ�������ַ
        
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
        message.setSubject("������֤��"); // �ʼ�����
        message.setContent(identifycode(), "text/html;charset=UTF-8");//�ʼ�����
        message.saveChanges();
        System.out.println(identifycode());
        return message;
    }
} 