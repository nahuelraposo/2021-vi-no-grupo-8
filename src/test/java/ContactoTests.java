import models.Excepciones.PersonaInvalidaException;
import models.Personas.Contacto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactoTests {
  @Test
  public void sePuedeCrearUnContactoCompleto() {
    Contacto contactoPrueba = new Contacto("Maria", "Gomez", 1135811485, "vengaElMail@gmail.com");
    assertFalse(contactoPrueba.getApellido().isEmpty());
    assertFalse(contactoPrueba.getNombre().isEmpty());
    assertEquals(10, getDigitsOfNumber(contactoPrueba.getTelefono()));
    assertFalse(contactoPrueba.getEmail().isEmpty());
  }

  @Test
  public void siUnContactoEstaInCompletoSeLanzaUnaExcepcion() {
    assertThrows(
        PersonaInvalidaException.class,
        () -> new Contacto("Maria", null, 1135811485, "")
    );
  }

  private int getDigitsOfNumber(int number) {
    return (int) (Math.log10(number) + 1);
  }
}
