import models.Excepciones.PasswordInseguraException;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidacionesTests {
  ValidadorPassword validadorPassword;

  @BeforeEach
  public void setup() {
    validadorPassword = new ValidadorPassword();
    validadorPassword.agregarValidaciones(new LongitudPassword());
    validadorPassword.agregarValidaciones(new NoEstaEntrePasswordsMasInseguras());
    validadorPassword.agregarValidaciones(new SinCaracteresConsecutivos());
    validadorPassword.agregarValidaciones(new SinCaracteresRepetidos());
  }

  @Test
  public void usuario1FallaPorTenerCaracteresConsecutivos() {
    assertThrows(PasswordInseguraException.class, () -> new Usuario("Manolo", "12357879", validadorPassword, false));
  }

  @Test
  public void usuario2FallaPorTenerCaracteresRepetidos() {
    assertThrows(PasswordInseguraException.class, () -> new Usuario("Manolo", "rrr985465", validadorPassword, false));
  }

  @Test
  public void usuario3FallaPorTenerContraseñaQueEstaEntreLas10000MasInseguras() {
    assertThrows(PasswordInseguraException.class, () -> new Usuario("Manolo", "redskins", validadorPassword, false));
  }

  @Test
  public void usuario4FallaPorTenerPasswordDeLongitudMenorA8() {
    assertThrows(PasswordInseguraException.class, () -> new Usuario("Manolo", "kjgd", validadorPassword, false));
  }

  @Test
  public void usuario5ApruebaTodasLasValidaciones() {
    Usuario usuario = usuario("kilimanyaro985", validadorPassword);
    assertEquals(usuario.getPassword(), "kilimanyaro985");
  }

  Usuario usuario(String password, ValidadorPassword validadorPassword) {
    return new Usuario("Manolo", password, validadorPassword, false);
  }

}
