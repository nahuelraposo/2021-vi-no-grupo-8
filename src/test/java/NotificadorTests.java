import models.Notificador.services.JavaMailService;
import models.Notificador.MailSender;
import models.Notificador.SMSSender;
import models.Notificador.services.TwilioService;
import models.Personas.Duenio;
import org.junit.jupiter.api.Test;
import support.SharedExamples;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class NotificadorTests {
  JavaMailService javaMail = mock(JavaMailService.class);
  TwilioService twilioService = mock(TwilioService.class);
  MailSender mailSender = new MailSender(javaMail);
  SMSSender smsSender = new SMSSender(twilioService);

  @Test
  void sePuedeNotificarPorSMSAUnDuenio() {
    Duenio duenio = SharedExamples.generarDuenioRegistrado();
    String cuerpo = "Hola, su mascota ha sido encontrada";
    smsSender.notificar(duenio.getContactos(), "", cuerpo);

    verify(twilioService, times(1)).sendSMS(
        eq("+541135811485"), eq(cuerpo));

    verify(twilioService, times(1)).sendSMS(
        eq("+541136150987"), eq(cuerpo));
  }
}
