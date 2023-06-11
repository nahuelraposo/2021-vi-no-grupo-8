package models.Excepciones;

public class UbicacionInvalidaException extends RuntimeException {
  public UbicacionInvalidaException(String mensaje) {
    super(mensaje);
  }
}
