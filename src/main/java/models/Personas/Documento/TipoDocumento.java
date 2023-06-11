package models.Personas.Documento;

public enum TipoDocumento {
  DNI,
  LC,
  LR,
  PASAPORTE;

  String getTipoDocumento() {
    return this.toString();
  }
}
