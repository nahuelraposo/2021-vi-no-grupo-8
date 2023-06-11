package models.Usuario.ValidacionPassword;

import models.Usuario.ValidacionPassword.Buscador.BuscadorEnArchivo;

public class SinCaracteresConsecutivos implements ValidacionPassword {

  public boolean cumpleCondicion(String password) {
    return !(BuscadorEnArchivo.tieneCaracteresConsecutivos(password, "caracteresConsecutivos"));
  }
}
