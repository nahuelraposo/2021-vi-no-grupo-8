import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import org.junit.jupiter.api.Test;
import support.SharedExamples;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicacionAdopcionTests {

  @Test
  public void sePuedeGenerarUnaPublicacionDeAdopcion() {
    PublicacionMascotaEnAdopcion publicacion = SharedExamples.crearPublicacionAdopcion(null);
    assertEquals(publicacion.getDuenio().getNombre(), "Jon");
    assertEquals(publicacion.getMascota().getNombre(), "Mas");
  }
}
