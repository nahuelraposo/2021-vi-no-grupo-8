import models.Refugios.BuscadorRefugios;
import models.Refugios.api.RefugiosService;
import models.Refugios.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.SharedExamples;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RefugiosAPITests {
  RefugiosService requester = mock(RefugiosService.class);

  @BeforeEach
  void init() {
    when(requester.getRefugios(anyInt())).thenReturn(SharedExamples.respuestaDeHogaresDisponibles());
  }

  @Test
  void puedoObtenerUnListadoDeTodosLosHogares() {
    assertEquals(new BuscadorRefugios(requester).getAllHogares().size(), 3);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresQueAceptanMascotasMedianasYGrandes() {
    assertTrue(new BuscadorRefugios(requester).getHogaresAceptanMascotasMedianasYGrandes().size() > 0);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresQueAdmitenPerros() {
    assertTrue(new BuscadorRefugios(requester).getHogaresParaPerros().size() > 0);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresQueAdmitenGatos() {
    assertTrue(new BuscadorRefugios(requester).getHogaresParaGatos().size() > 0);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresQueNecesitanCiertasCaracteristicas() {
    assertTrue(new BuscadorRefugios(requester).getHogaresAdmisionEspecifica(Collections.singletonList("Manso")).size() > 0);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresConLugar() {
    assertEquals(new BuscadorRefugios(requester).getHogaresConLugar().size(), 1);
  }

  @Test
  void puedoObtenerUnListadoDeHogaresDentroDeUnRadio() {
    Ubicacion unaUbicacion = new Ubicacion("Una direccion", -34.58802091762702, -58.39715054683186);
    assertTrue(new BuscadorRefugios(requester).getHogaresDentroDelRadio(5.0, unaUbicacion).size() > 0);
  }
}
