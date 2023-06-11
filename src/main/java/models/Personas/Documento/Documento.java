package models.Personas.Documento;

import models.Excepciones.DocumentoInvalidoException;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Documento{
  @Enumerated
  TipoDocumento tipo;
  int numero;

  public Documento(TipoDocumento tipo, int numero) {
    this.chequearDocumentoValido(tipo, numero);
    this.tipo = tipo;
    this.numero = numero;
  }

  public Documento() {}

  private void chequearDocumentoValido(TipoDocumento tipo, int numero) {
    this.validarNumeroDeDocumento(numero);
    this.validarTipoDocumento(tipo);
  }

  private void validarTipoDocumento(TipoDocumento tipo) {
    if (tipo.getTipoDocumento() == null || tipo.getTipoDocumento().isEmpty()) {
      throw new DocumentoInvalidoException("no es tipo valido");
    }
  }

  private void validarNumeroDeDocumento(int numero) {
    if (Integer.toString(numero).length() < 4 || Integer.toString(numero).length() > 8) {
      throw new DocumentoInvalidoException("no se ingresó un número válido");
    }
  }

  public TipoDocumento getTipo() {
    return tipo;
  }

  public int getNumero() {
    return numero;
  }
}
