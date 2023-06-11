package models.Notificador;

import models.Notificador.services.TwilioService;
import models.Personas.Contacto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@DiscriminatorValue("SS")
public class SMSSender extends Notificador {
  @Transient
  TwilioService twilioService;

  public SMSSender(TwilioService twilioService) {
    this.twilioService = twilioService;
  }

  public SMSSender() {

  }

  public void notificar(List<Contacto> contactos, String asunto, String mensaje) {
    contactos.forEach(contacto -> this.enviarSMS(parsearTelefono(contacto.getTelefono()), mensaje));
  }

  private String parsearTelefono(Integer telefono) {
    return "+54" + telefono;
  }

  private void enviarSMS(String telefono, String mensaje) {
    twilioService.sendSMS(telefono, mensaje);
  }

}
