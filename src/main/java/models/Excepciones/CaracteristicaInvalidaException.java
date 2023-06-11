package models.Excepciones;

public class CaracteristicaInvalidaException extends RuntimeException {
  public CaracteristicaInvalidaException(String causa) {
    super("La caracteristica es invalida ya que: " + causa);
  }
}
