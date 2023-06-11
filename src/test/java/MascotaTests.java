import models.Excepciones.MascotaInvalidaException;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Mascota;
import models.Mascota.TipoMascota;
import models.Mascota.TamanioMascota;
import org.junit.jupiter.api.Test;
import support.SharedExamples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MascotaTests {

  @Test
  public void crearUnaMascotaConTodosSusDatosCompletosYSeRegistra() {
    Mascota mascota = SharedExamples.crearMascotaDePrueba(TipoMascota.GATO, TamanioMascota.MEDIANA);
    assertEquals(5, mascota.getEdad(), 0);
  }

  @Test
  public void noPuedoCrearUnaMascotaConDatosIncompletos() {
    assertThrows(MascotaInvalidaException.class, this::crearMascotaDePruebaIncompleta);
  }

  @Test
  public void noPuedoCrearUnaMascotaConDatosIncorrectos() {
    assertThrows(MascotaInvalidaException.class, this::crearMascotaIncorrecta);
  }

  public Mascota crearMascotaDePruebaIncompleta() {
    List<CaracteristicaSensible> caracteristicas = SharedExamples.listadoDeCaracteristicasDePrueba();
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    return new Mascota(TipoMascota.PERRO, "", "", 9, 'M', TamanioMascota.CHICA, "Un perro de prueba", fotos, caracteristicas);
  }

  public Mascota crearMascotaIncorrecta() {
    List<CaracteristicaSensible> caracteristicas = SharedExamples.listadoDeCaracteristicasDePrueba();
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    return new Mascota(TipoMascota.GATO, "Paula", "Pepa", -2, 'X', TamanioMascota.CHICA, "Una mascota de prueba", fotos, caracteristicas);
  }
}
