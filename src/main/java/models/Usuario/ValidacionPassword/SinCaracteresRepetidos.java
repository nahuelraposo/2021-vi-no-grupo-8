package models.Usuario.ValidacionPassword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinCaracteresRepetidos implements ValidacionPassword {
  private static final String REGEX_PARA_CARACTERES_REPETIDOS = ".*((\\w)\\2\\2).*";

  public boolean cumpleCondicion(String password) {
    Pattern pat = Pattern.compile(REGEX_PARA_CARACTERES_REPETIDOS);
    Matcher mat = pat.matcher(password);
    return !mat.matches();
  }

}

