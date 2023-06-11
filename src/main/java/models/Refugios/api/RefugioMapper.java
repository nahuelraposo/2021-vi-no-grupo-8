package models.Refugios.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RefugioMapper {
  private ObjectMapper objectMapper;

  public RefugioMapper() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public <T> T fromJson(String json, Class<T> typeReference) {
    try {
      return this.objectMapper.readValue(json, typeReference);
    } catch (IOException e) {
      throw new RuntimeException("Error reading a json", e);
    }
  }

}
