package models.Notificador.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioService {
  private static final String ACCOUNT_SID = "ACe1405a965d8110d511d2ccb228ab4716";
  private static final String AUTH_TOKEN = "8d6bc20978b40e2a9ef167c12b71440b";
  private static final String REMITENTE_AUTORIZADO = "+13122290641";

  public void sendSMS(String toNumber, String bodySMS) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(new PhoneNumber(toNumber), new PhoneNumber(REMITENTE_AUTORIZADO), bodySMS).create();
  }
}
