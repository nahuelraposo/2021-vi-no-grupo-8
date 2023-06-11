import models.Refugios.Ubicacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UbicacionesTest {
  Ubicacion refugioUbicacion = new Ubicacion("Una direccion", -34.58802091762702, -58.39715054683186);
  Ubicacion otraUbicacion = new Ubicacion("Una direccion", -34.596764663188885, -58.37680862959623);

  @Test
  void CalcularDistanciaAOtraUbicacion() {
    assertEquals(refugioUbicacion.distanciaAOtraUbicacion(otraUbicacion), 2.0, 0.3); //Funciona, tiene un delta 0.3 km
  }

  @Test
  void UbicacionEstaEnElRadio() {
    assertTrue(refugioUbicacion.estaDentroDelRadio(2.5, otraUbicacion));
  }
}

