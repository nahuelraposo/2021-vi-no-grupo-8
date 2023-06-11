import models.Mascota.Mascota;
import models.Mascota.TipoMascota;
import models.Mascota.TamanioMascota;
import models.Personas.Duenio;
import org.junit.jupiter.api.*;

import support.SharedExamples;

import static org.junit.jupiter.api.Assertions.*;

public class DuenioTests {

  @Test
  public void unDuenioPuedeRegistrarSusDatosYSuMascota(){
    Duenio duenio = SharedExamples.generarDuenioRegistrado();
    Mascota mascotaPrueba = SharedExamples.crearMascotaDePrueba(TipoMascota.GATO, TamanioMascota.MEDIANA);

    duenio.agregarMascota(mascotaPrueba);

    assertTrue(duenio.getMascotas().contains(mascotaPrueba));
  }

  @Test
  public void unDuenioPuedeRegistrarMasDeUnaMascota(){
    Duenio duenio = SharedExamples.generarDuenioRegistrado();
    Mascota mascotaPrueba1 = SharedExamples.crearMascotaDePrueba(TipoMascota.GATO, TamanioMascota.CHICA);
    Mascota mascotaPrueba2 = SharedExamples.crearMascotaDePrueba(TipoMascota.PERRO, TamanioMascota.GRANDE);

    duenio.agregarMascota(mascotaPrueba1);
    duenio.agregarMascota(mascotaPrueba2);

    assertTrue(duenio.poseeLaMascota(mascotaPrueba1));
    assertTrue(duenio.poseeLaMascota(mascotaPrueba2));
  }
}


