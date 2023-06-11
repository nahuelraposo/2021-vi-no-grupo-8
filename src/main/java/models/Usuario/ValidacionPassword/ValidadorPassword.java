package models.Usuario.ValidacionPassword;

import models.Excepciones.PasswordInseguraException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {
  private List<ValidacionPassword> validaciones = new ArrayList<ValidacionPassword>();

  public void agregarValidaciones(ValidacionPassword validacion) {
    validaciones.add(validacion);
  }

  private boolean seCumplenTodasLasValidaciones(String password) {
    return validaciones.stream().allMatch(validacion -> validacion.cumpleCondicion(password));
  }

  public void validarPassword(String password) {
    if (!seCumplenTodasLasValidaciones(password)) {
      throw new PasswordInseguraException();
    }
  }

}
