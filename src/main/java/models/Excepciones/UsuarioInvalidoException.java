package models.Excepciones;

public class UsuarioInvalidoException extends RuntimeException {
  public UsuarioInvalidoException(String mensaje) {
    super(mensaje);
  }
}
