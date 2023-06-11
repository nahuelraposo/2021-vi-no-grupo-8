package models.Usuario.ValidacionPassword;

import models.Usuario.ValidacionPassword.Buscador.BuscadorEnArchivo;

public class NoEstaEntrePasswordsMasInseguras implements  ValidacionPassword {

  public boolean cumpleCondicion(String password) {
    return !(BuscadorEnArchivo.estaIncluida(password, "10k-most-common-passwords"));
  }
}
