package models.Usuario.ValidacionPassword;

public class LongitudPassword implements  ValidacionPassword {

  public boolean cumpleCondicion(String password) {
    return password.length() >= 8;
  }


}
