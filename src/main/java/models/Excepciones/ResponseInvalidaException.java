package models.Excepciones;

public class ResponseInvalidaException extends RuntimeException {
  public ResponseInvalidaException(int codeError) {
    super("Error en la API. Codigo: " + codeError);
  }
}
