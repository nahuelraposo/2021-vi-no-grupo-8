package models.Refugios.api;

import models.Excepciones.ResponseInvalidaException;
import models.Refugios.dto.Respuesta;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

public class RefugiosService {
  private RefugioMapper mapper = new RefugioMapper();
  private Client client;
  private static final String API_REFUGIOS = "https://api.refugiosdds.com.ar/api/";
  private static final String RESOURCE = "hogares";
  private static final String TOKEN =
      "3d8rVEZ2zP7VPEo4ClVFI8Lnxv0p6U0Ht2U5V5W9zAoDNux0ucgw36tCwtsl";

  public RefugiosService() {
    this.client = Client.create();
  }

  public Respuesta getRefugios(Integer offset) {
    ClientResponse response = makeRequest(offset);
    return parsearResponse(response);
  }

  private ClientResponse makeRequest(Integer offset) {
    ClientResponse response = this.client.resource(API_REFUGIOS).path(RESOURCE)
        .queryParam("offset", offset.toString())
        .header("Authorization", "Bearer " + TOKEN)
        .accept(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);
    validResponse(response);
    return response;
  }

  private Respuesta parsearResponse(ClientResponse response) {
    String json = response.getEntity(String.class);
    return mapper.fromJson(json, Respuesta.class);
  }

  private void validResponse(ClientResponse response) {
    if (response.getStatus() != 200) {
      throw new ResponseInvalidaException(response.getStatus());
    }
  }
}
