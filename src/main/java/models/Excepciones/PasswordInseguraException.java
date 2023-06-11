package models.Excepciones;

public class PasswordInseguraException extends RuntimeException {
  public PasswordInseguraException() {
    super("La password ingresada es insegura.");
  }
}
