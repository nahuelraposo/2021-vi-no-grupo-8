package models.Refugios.dto;

import models.Refugios.Ubicacion;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UbicacionDto {
  @JsonProperty ("direccion")
  String direccion;
  @JsonProperty("lat")
  Double latitud;
  @JsonProperty("long")
  Double longitud;
  private static final Integer RADIO_TIERRA = 6371;

  public String getDireccion() {
    return direccion;
  }

  public Double getLatitud() {
    return latitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public Double radiansLat() {
    return Math.toRadians(getLatitud());
  }

  public Double radiansLong() {
    return Math.toRadians(getLongitud());
  }

  public Double distanciaAOtraUbicacion(Ubicacion otraUbicacion) {
    return Math.acos(Math.sin(radiansLat()) * Math.sin(otraUbicacion.radiansLatitud())
            + Math.cos(radiansLat()) * Math.cos(otraUbicacion.radiansLatitud())
            * Math.cos(otraUbicacion.radiansLongitud() - radiansLong())) * RADIO_TIERRA;
  }

  public boolean estaDentroDelRadio(Double radio, Ubicacion otraUbicacion) {
    return distanciaAOtraUbicacion(otraUbicacion) <= radio;
  }

  public UbicacionDto(String direccion, Double latitud, Double longitud) {
    this.direccion = direccion;
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
