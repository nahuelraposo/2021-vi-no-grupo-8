import models.Excepciones.PersonaInvalidaException;
import models.Mascota.Chapita;
import models.Asociacion.Asociacion;
import models.Asociacion.Publicacion.PublicacionConChapita;
import models.Asociacion.Publicacion.PublicacionSinChapita;
import models.Mascota.MascotaPerdida;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Notificador.services.JavaMailService;
import models.Notificador.MailSender;
import models.Notificador.Notificador;
import models.Personas.Duenio;
import models.Refugios.BuscadorRefugios;
import models.Refugios.api.RefugiosService;
import models.Refugios.dto.Hogar;
import models.Refugios.Ubicacion;
import models.Repositorios.RepositorioPreguntas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.SharedExamples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublicacionRescateTests {
  RefugiosService requesterDeHogares = mock(RefugiosService.class);
  RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas();

  @BeforeEach
  void init() {
    when(requesterDeHogares.getRefugios(anyInt())).thenReturn(SharedExamples.respuestaDeHogaresDisponibles());
  }

  @Test
  public void rescatistaNotificaMascotaPerdida() {
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Collections.singletonList(notificadorPrueba);
    Asociacion asociacion = new Asociacion(repositorioPreguntas);
    PublicacionSinChapita publicacionDePrueba = publicacionDePruebaSinChapita(LocalDate.of(1999, 3, 12), notificadores, asociacion);
    asociacion.agregarPublicacion(publicacionDePrueba);

    assertTrue(asociacion.getPublicacionesRescate().contains(publicacionDePrueba));
  }

  @Test
  public void sePuedenVerLasMascotasEncontradasEnLosUltimos10Dias() {
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Collections.singletonList(notificadorPrueba);
    Asociacion asociacion = new Asociacion(repositorioPreguntas);

    PublicacionSinChapita publicacion1 = publicacionDePruebaSinChapita(LocalDate.now().minusDays(3), notificadores, asociacion);
    PublicacionSinChapita publicacion2 = publicacionDePruebaSinChapita(LocalDate.of(2021, 2, 12), notificadores, asociacion);
    PublicacionSinChapita publicacion3 = publicacionDePruebaSinChapita(LocalDate.now(), notificadores, asociacion);

    asociacion.agregarPublicacion(publicacion1);
    asociacion.agregarPublicacion(publicacion2);
    asociacion.agregarPublicacion(publicacion3);

    assertTrue(asociacion.listarMascotasEncontradasLosUltimos10Dias().contains(publicacion1));
    assertFalse(asociacion.listarMascotasEncontradasLosUltimos10Dias().contains(publicacion2));
    assertTrue(asociacion.listarMascotasEncontradasLosUltimos10Dias().contains(publicacion3));
  }

  @Test
  void cuandoSeCreaUnaPublicacionIncompletaSeLanzaUnaExcepcion() {
    assertThrows(
        PersonaInvalidaException.class,
        () -> publicacionDePruebaIncompletaSinChapita(LocalDate.now())
    );
  }

  @Test
  void cuandoSeApruebaUnaPublicacionDeUnaMascotaSePasaAlEstadoDeAprobada() {
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Collections.singletonList(notificadorPrueba);
    Asociacion asociacion = new Asociacion(repositorioPreguntas);
    PublicacionSinChapita publicacionSinChapita = publicacionDePruebaSinChapita(
        LocalDate.now(),
        notificadores,
        asociacion
    );

    publicacionSinChapita.aceptar();

    assertTrue(publicacionSinChapita.estaAprobada());
  }

  @Test
  void cuandoSeApruebaUnaPublicacionDeUnaMascotaConChapitaSeNotificaAlDuenio() {
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Collections.singletonList(notificadorPrueba);
    Duenio duenioPrueba = SharedExamples.crearDuenio(notificadores);
    Chapita chapitaPrueba = new Chapita(1, duenioPrueba);

    publicacionDePruebaConChapita(LocalDate.now(), notificadores, chapitaPrueba);

    verify(notificadorPrueba).notificar(any(), any(), anyString());
  }

  @Test
  void cuandoAlguienEncuentraASuMascotaEntreLasPublicacionesSeContactaConElRescatista() {
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Collections.singletonList(notificadorPrueba);
    Asociacion asociacion = new Asociacion(repositorioPreguntas);
    PublicacionSinChapita publicacionSinChapita = publicacionDePruebaSinChapita(LocalDate.now(), notificadores, asociacion);

    publicacionSinChapita.contactarRescatista(SharedExamples.persona(), "hola 7722");

    verify(notificadorPrueba).notificar(any(), any(), anyString());
  }

  // Se pueden recomendar diferentes hogares para el rescatista que no posea lugar adecuado para la mascota
  @Test
  void sePuedenRecomendarDistintosHogaresDeTransitoParaElRescatista() {
    Asociacion asociacion = new Asociacion(repositorioPreguntas);
    PublicacionSinChapita publicacionSinChapita = publicacionDePruebaSinChapita(
        LocalDate.now().minusDays(3),
        null,
        asociacion
    );
    List<Hogar> hogaresRecomendados = publicacionSinChapita.obtenerHogaresAdecuados(50.0);
    assertEquals(2, hogaresRecomendados.size());
  }

  PublicacionSinChapita publicacionDePruebaSinChapita(LocalDate fecha, List<Notificador> notificadores, Asociacion asociacion) {
    return new PublicacionSinChapita(
        SharedExamples.persona(),
        "Calle Falsa 123",
        mascotaPerdidaEjemplo(),
        fecha,
        notificadores,
        new BuscadorRefugios(requesterDeHogares)
    );
  }

  PublicacionSinChapita publicacionDePruebaIncompletaSinChapita(LocalDate fecha) {
    JavaMailService javaMail = mock(JavaMailService.class);
    MailSender mailSender = new MailSender(javaMail);
    return new PublicacionSinChapita(
        SharedExamples.persona(),
        "",
        mascotaPerdidaEjemplo(),
        fecha,
        Collections.singletonList(mailSender),
        new BuscadorRefugios(requesterDeHogares)
    );
  }

  PublicacionConChapita publicacionDePruebaConChapita(LocalDate fecha, List<Notificador> notificadores, Chapita chapita) {
    return new PublicacionConChapita(
        SharedExamples.persona(),
        "Calle Falsa 123",
        chapita,
        mascotaPerdidaEjemplo(),
        fecha,
        notificadores
    );
  }

  MascotaPerdida mascotaPerdidaEjemplo() {
    Ubicacion ubicacion = new Ubicacion("Westeros 123", -34.42061525423029, -58.572775488348505);
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    List<String> caract = new ArrayList<>(Collections.singletonList("Tranquilo"));
    return new MascotaPerdida(fotos, "Buen estado", ubicacion, TipoMascota.PERRO, TamanioMascota.CHICA, caract);
  }
}
