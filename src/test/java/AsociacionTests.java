import models.Asociacion.Asociacion;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import models.Repositorios.RepositorioPreguntas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Asociacion.Pregunta;
import support.SharedExamples;

import static org.junit.jupiter.api.Assertions.*;

/*public class AsociacionTests {
  RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas();
  Asociacion asociacion;

  @BeforeEach
  void init() {
    Pregunta preguntaRespuestaBaseA =
        new Pregunta<>(
            "¿Tu mascota suele jugar con chicos?",
            "¿Tenes menores de edad en tu casa?",
            Arrays.asList(true, false));
    Pregunta preguntaRespuestaBaseB =
        new Pregunta<>(
            "¿Tenes con quien dejar a tu mascota es caso de un imprevisto?",
            "¿Exiten mas personas que convivan en la misma casa que vos?",
            Arrays.asList(true, false));

    repositorioPreguntas.agregarPreguntaBase(preguntaRespuestaBaseA);
    repositorioPreguntas.agregarPreguntaBase(preguntaRespuestaBaseB);
    asociacion = SharedExamples.crearAsociacionConPreguntasBase(repositorioPreguntas);
  }

  @Test
  void unaAsociacionTienePorDefaultLasPreguntasQueSiempreSonContestadas() {
    assertEquals(asociacion.getPreguntasBase(), repositorioPreguntas.getPreguntasBase());
  }

  @Test
  void sePuedenCargarPreguntasPropiasParaUnaAsociacion() {
    Pregunta preguntaA = SharedExamples.preguntaCantidadAmbientes();
    Pregunta preguntaB = SharedExamples.preguntaNecesidadPatio();
    Pregunta preguntaC = SharedExamples.preguntaCantidadMascotas();

    asociacion.agregarPregunta(preguntaA);
    asociacion.agregarPregunta(preguntaB);
    asociacion.agregarPregunta(preguntaC);

    Assertions.assertTrue(
        asociacion.getPreguntasConRespuestasPosibles()
            .containsAll(Arrays.asList(preguntaA, preguntaB, preguntaC))
    );
  }

  @Test
  void unaAsociacionTieneLasPreguntasBaseYSusPreguntasPropias() {
    Pregunta preguntaA = SharedExamples.preguntaCantidadAmbientes();
    Pregunta preguntaB = SharedExamples.preguntaNecesidadPatio();
    Pregunta preguntaC = SharedExamples.preguntaCantidadMascotas();

    asociacion.agregarPregunta(preguntaA);
    asociacion.agregarPregunta(preguntaB);
    asociacion.agregarPregunta(preguntaC);


    List<Pregunta> todasLasPreguntas = Stream
        .concat(repositorioPreguntas.getPreguntasBase().stream(), Stream.of(preguntaA, preguntaB, preguntaC))
        .collect(Collectors.toList());

    Assertions.assertTrue(asociacion.getPreguntasConRespuestasPosibles().containsAll(todasLasPreguntas));
  }

  @Test
  void sePuedenCargarPublicacionesDeAdopcionEnLaAsociacion() {
    PublicacionMascotaEnAdopcion publiA = SharedExamples.crearPublicacionAdopcion(null);

    asociacion.cargarNuevaAdopcion(publiA);

    assertEquals(
        asociacion.getPublicacionesMascotasEnAdopcion(),
        Collections.singletonList(publiA)
    );
  }
}
*/