import models.Asociacion.Asociacion;
import models.Asociacion.Pregunta;
import models.Asociacion.Publicacion.Preferencia;
import models.Asociacion.Publicacion.PublicacionInteresado;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import models.Mascota.Mascota;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Notificador.MailSender;
import models.Personas.Contacto;
import models.Repositorios.RepositorioPreguntas;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import models.Asociacion.PreguntaRespondida;
import support.SharedExamples;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*
public class PublicacionInteresadoTests {
  MailSender mailSender = mock(MailSender.class);
  Asociacion asociacion = SharedExamples.crearAsociacionConPreguntasBase(repositorioPreguntas());

  @Test
  void sePuedeGenerarUnaPublicacionDelInteresadoEnBaseAPreferenciasYComodidades() {
    Preferencia preferencia = crearPreferenciaBase();
    PublicacionInteresado publiA = crearPublicacionDelInteresado(preferencia, new ArrayList<>());

    assertEquals(publiA.getPreferencias().getTipoMascota().toString(), "GATO");
    assertEquals(publiA.getPreferencias().getSexo(), 'F');
    assertEquals(publiA.getPreferencias().getTamanio().toString(), "CHICA");
    assertEquals(publiA.getComodidades().size(), 0);
  }

  @Test
  void seGeneraUnLinkHaciaElInteresadoParaPermitirLaBajaDeUnaPublicacionHecha() {
    Preferencia preferencia = crearPreferenciaBase();
    crearPublicacionDelInteresado(preferencia, null);

    verify(mailSender, times(1)).enviarMail(
        "poneleUnMail@gmail.com",
        "Link para desuscripción de la publicacion",
        "https://github.com/dds-utn/2021-vi-no-grupo-8"
    );
  }

  @Test
  void sePuedeDarDeBajaUnPublicacion() {
    PublicacionInteresado publicacion = new PublicacionInteresado(
        null,
        "NECESITO_CAFE@gmail.com",
        new Contacto("Julio", "Omar", 123, "1234@gmail.com"),
        null,
        mailSender
    );

    asociacion.darDeBajaPublicacion(publicacion);
    assertEquals(0, asociacion.getPublicacionesDeInteresados().size());
  }

  @Test
  void elInteresadoPuedeRecibirPublicacionesDeMascotasEnAdopcion() {
    List<PreguntaRespondida> preguntasRespondidas = this.crearPreguntasRespondidas();
    PublicacionMascotaEnAdopcion publicacionDeAdopcion = crearPublicacionAdopcion(preguntasRespondidas, SharedExamples.crearMascotaDePrueba(TipoMascota.PERRO, TamanioMascota.GRANDE));
    List<PublicacionMascotaEnAdopcion> publicacionesAdopcion = Arrays.asList(publicacionDeAdopcion);
    Preferencia preferencias = new Preferencia(TipoMascota.PERRO, TamanioMascota.GRANDE, 'M', null);

    PublicacionInteresado publicacionInteresado = crearPublicacionDelInteresado(preferencias, preguntasRespondidas);

    this.seLeRecomiendaAlInteresadoLasPublicacionesDeAdopcion(publicacionInteresado, publicacionesAdopcion);
  }

  @Test
  void apareceUnInteresadoEnAdoptarYSeLeNotificaAlDuenioDeLaMascota() {
    List<PreguntaRespondida> preguntasRespondidas = crearPreguntasRespondidas();
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = crearPublicacionAdopcion(preguntasRespondidas,
        SharedExamples.crearMascotaDePrueba(TipoMascota.PERRO, TamanioMascota.CHICA));
    PublicacionInteresado publicacionInteresado = crearPublicacionDelInteresado(crearPreferenciaBase(), preguntasRespondidas);

    this.seLeNotificaAlDuenioDeLaMascota(publicacionMascotaEnAdopcion, publicacionInteresado);
  }

  @Test
  void sePuedeMatchearLasCondicionesEntreAdoptanteYDador() {
    String preguntaDador = "¿Tu mascota necesita un patio?";
    String preguntaAdoptante = "¿Tenes patio?";
    Pregunta pregunta = new Pregunta(preguntaDador, preguntaAdoptante, Arrays.asList(true, false));
    PreguntaRespondida preguntaTenesPatio = pregunta.responder(true);

    assertEquals(preguntaTenesPatio.getPreguntaDador(), preguntaDador);
    assertEquals(preguntaTenesPatio.getPreguntaAdoptante(), preguntaAdoptante);
  }

  PublicacionInteresado crearPublicacionDelInteresado(Preferencia preferencias, List<PreguntaRespondida> preguntasRespondidas) {
    return new PublicacionInteresado(
        preferencias,
        "poneleUnMail@gmail.com",
        new Contacto("Julio", "Omar", 123, "1234@gmail.com"),
        preguntasRespondidas,
        mailSender
    );
  }

  private Preferencia crearPreferenciaBase() {
    return new Preferencia(TipoMascota.GATO, TamanioMascota.CHICA, 'F', null);
  }

  RepositorioPreguntas repositorioPreguntas() {
    RepositorioPreguntas repo = new RepositorioPreguntas();
    repo.agregarPreguntaBase(SharedExamples.preguntaNecesidadPatio());
    repo.agregarPreguntaBase(SharedExamples.preguntaCantidadMascotas());
    repo.agregarPreguntaBase(SharedExamples.preguntaCantidadAmbientes());
    return repo;
  }

  PublicacionMascotaEnAdopcion crearPublicacionAdopcion(List<PreguntaRespondida> necesidades, Mascota mascota) {
    return new PublicacionMascotaEnAdopcion(
        SharedExamples.crearDuenio(Arrays.asList(mailSender)),
        mascota,
        necesidades
    );
  }

  List<PreguntaRespondida> crearPreguntasRespondidas() {
    Pregunta pregunta = new Pregunta("¿Tu mascota necesita un patio?", "¿Tenes patio?", Arrays.asList(true, false));
    PreguntaRespondida preguntaTenesPatio = pregunta.responder(true);
    List<PreguntaRespondida> preguntasRespondidas = Collections.singletonList(preguntaTenesPatio);
    return preguntasRespondidas;
  }

  void seLeNotificaAlDuenioDeLaMascota(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion,
                                       PublicacionInteresado publicacionInteresado) {
    publicacionMascotaEnAdopcion.contactarseConDuenio(publicacionInteresado.getContacto());
    verify(mailSender, times(1)).enviarMail(anyString(), anyString(), anyString());
    verify(mailSender, times(1)).notificar(
        publicacionMascotaEnAdopcion.getDuenio().getContactos(),
        "Interesado en adopcion de mascota",
        "¡Una persona está interesada en adoptar a tu mascota!"
            + "\nLos datos del interesado son: "
            + "\nNombre: Julio"
            + "\nApellido: Omar"
            + "\nTeléfono: 123"
            + "\nEmail: 1234@gmail.com"
    );
  }

  void seLeRecomiendaAlInteresadoLasPublicacionesDeAdopcion(PublicacionInteresado publicacionInteresado,
                                                            List<PublicacionMascotaEnAdopcion> publicacionesAdopcion) {
    publicacionInteresado.recomendarAlInteresado(publicacionesAdopcion);

    verify(mailSender, times(1)).
        enviarMail("poneleUnMail@gmail.com",
            "¡Revisa las nuevas recomendaciones semanales que tenemos para ti!",
            "Informacion de la publicacion de mascota en adopcion:" +
                "\nNombre del duenio actual de la mascota: Jon Snow" +
                "\nNombre de la mascota: Mas" +
                "\nTamaño de la mascota: GRANDE" +
                "\nDescripcion de la mascota: Una mascota de prueba" +
                "\nSexo de mascota: M" +
                "\nEdad de mascota(años): 5"
        );
  }
}
*/
