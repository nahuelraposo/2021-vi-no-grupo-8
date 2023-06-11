package models.Excepciones;

public class DocumentoInvalidoException extends RuntimeException {
  public DocumentoInvalidoException(String causa) {
    super("El documento es invalido ya que: " + causa);
  }
}
