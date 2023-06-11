package models.Notificador;

import models.Notificador.services.JavaMailService;
import models.Personas.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.*;

@Entity
@DiscriminatorValue("MS")
public class MailSender extends Notificador {
  @Transient
  JavaMailService javaMail;

  public MailSender(JavaMailService javaMail) {
    this.javaMail = javaMail;
  }

  public MailSender() {

  }

  public void notificar(List<Contacto> contactos, String asunto, String mensaje) {
    //"models.Asociacion.Asociacion.Mascota encontrada"
    contactos.forEach(contacto -> this.enviarMail(contacto.getEmail(), asunto, mensaje));
  }

  public void enviarMail(String destinatario, String asunto, String cuerpo) {
    //javaMail.sendEmail(destinatario, asunto, cuerpo);
  }

}
