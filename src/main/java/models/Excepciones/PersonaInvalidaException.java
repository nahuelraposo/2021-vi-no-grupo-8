package models.Excepciones;

public class PersonaInvalidaException extends RuntimeException {
  public PersonaInvalidaException(String causa) {
    super("La persona ingresada es invalida ya que: " + causa);
  }
}
