package models.Refugios;

import models.Excepciones.UbicacionInvalidaException;

import javax.persistence.Embeddable;

@Embeddable
public class Ubicacion {
  String direccion;
  Double latitud;
  Double longitud;

  public Ubicacion(String direccion, Double lat, Double longitud) {
    this.validarUbicacion(direccion, lat, longitud);
    this.direccion = direccion;
    this.latitud = lat;
    this.longitud = longitud;
  }

  public Ubicacion() {

  }

  public String getDireccion() {
    return direccion;
  }

  public Double getLatitud() {
    return latitud;
  }

  public Double getLong() {
    return longitud;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public void setLatitud(Double latitud) {
    this.latitud = latitud;
  }

  public void setLong(Double longitud) {
    this.longitud = longitud;
  }

  public Double distanciaAOtraUbicacion(Ubicacion otraUbicacion) {
    int RADIO_TIERRA = 6371;
    return Math.acos(Math.sin(radiansLatitud())
        * Math.sin(otraUbicacion.radiansLatitud())
        + Math.cos(radiansLatitud()) * Math.cos(otraUbicacion.radiansLatitud())
        * Math.cos(otraUbicacion.radiansLongitud() - radiansLongitud()))
        * RADIO_TIERRA;
  }

  public boolean estaDentroDelRadio(Double radio, Ubicacion otraUbicacion) {
    return distanciaAOtraUbicacion(otraUbicacion) <= radio;
  }

  public Double radiansLatitud() {
    return Math.toRadians(getLatitud());
  }

  public Double radiansLongitud() {
    return Math.toRadians(getLong());
  }


  private void validarUbicacion(String direccion, Double longitud, Double latitud) {
    validarDireccion(direccion);
    validarLatitudYLongitud(longitud, latitud);
  }

  private void validarDireccion(String direccion) {
    if (direccion == null) {
      throw new UbicacionInvalidaException("La direccion no puede estar sin completar");
    }
  }

  private void validarLatitudYLongitud(Double longitud, Double latitud) {
    if (longitud == null || latitud == null) {
      throw new UbicacionInvalidaException("La ubicacion debe tener un par Longitud,Latitud");
    }
  }
}
