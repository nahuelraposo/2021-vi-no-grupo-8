package models.Excepciones;

public class MascotaInvalidaException extends RuntimeException {
  public MascotaInvalidaException(String causa) {
    super("La mascota es invalida ya que: " + causa);
  }
}
