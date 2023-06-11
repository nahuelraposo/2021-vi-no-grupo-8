package models.Notificador.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailService {
  private final Properties properties = new Properties();
  private static final Integer PUERTO_SEGURO_GOOGLE = 587;
  private static final String HOST_GOOGLE = "smtp.gmail.com";
  private final String remitente;
  private final String password;
  private Session session;

  public JavaMailService(String remitente, String password) {
    this.remitente = remitente;
    this.password = password;
  }

  private void init() {
    properties.put("mail.smtp.host", HOST_GOOGLE);
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", PUERTO_SEGURO_GOOGLE);
    properties.put("mail.smtp.user", remitente);
    properties.put("mail.smtp.auth", "true");

    session = Session.getDefaultInstance(properties);
  }

  public void sendEmail(String destinatario, String asunto, String cuerpo) {
    init();
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(remitente));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
      message.setSubject(asunto);
      message.setText(cuerpo);
      Transport t = session.getTransport("smtp");
      t.connect((String) properties.get("mail.smtp.user"), password);
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}
