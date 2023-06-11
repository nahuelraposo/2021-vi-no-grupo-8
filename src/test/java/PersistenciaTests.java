import models.Asociacion.Pregunta;
import models.Mascota.Caracteristica.CaracteristicaIdealBooleana;
import models.Mascota.Caracteristica.CaracteristicaIdealTextoLibre;
import models.Mascota.Mascota;
import models.Mascota.TipoMascota;
import models.Mascota.TamanioMascota;
import models.Notificador.Notificador;
import models.Personas.Duenio;
import models.Repositorios.RepositorioCaracteristicas;
import models.Repositorios.RepositorioDuenios;
import models.Repositorios.RepositorioPreguntas;
import models.Repositorios.RepositorioUsuarios;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.LongitudPassword;
import models.Usuario.ValidacionPassword.ValidadorPassword;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import support.SharedExamples;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/*
public class PersistenciaTests extends AbstractPersistenceTest implements WithGlobalEntityManager {
  RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas();
  EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
  ValidadorPassword validadorPassword = new ValidadorPassword();
  RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
  RepositorioCaracteristicas repositorioCaracteristicas = new RepositorioCaracteristicas();
  RepositorioDuenios repositorioDuenios = new RepositorioDuenios();

  @BeforeEach
  void init() {
    entityManager().clear();
    validadorPassword.agregarValidaciones(new LongitudPassword());
    tx.begin();
  }

  @AfterEach
  void finish() {
    tx.rollback();
  }

  @Test
  void sePuedenPersistirMascotasYRecuperarlas() {
    Mascota mascota = SharedExamples.crearMascotaDePrueba(TipoMascota.GATO, TamanioMascota.CHICA);
    entityManager().persist(mascota);
    assertNotNull(mascota.getId());
  }

  @Test
  void sePuedePersistirPreguntasBasesYRecuperarlas() {
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

    assertNotNull(preguntaRespuestaBaseA.getId());
    assertNotNull(preguntaRespuestaBaseB.getId());
    assertTrue(entityManager().createQuery("from Pregunta", Pregunta.class).getResultList().size() >= 2);
  }

*/
/*  @Test
  void sePuedePersistirUnDuenioYRecuperarlo() {
    entityManager().persist(SharedExamples.generarDuenioRegistrado());
    assertEquals(1, entityManager().createQuery("from Duenio", Duenio.class).getResultList().size());
  }*//*


  @Test
  void sePersistenLosUsuariosRasos() {
    Usuario usuario = new Usuario("user1", "unaContraseña4525!", validadorPassword, false);
    repositorioUsuarios.agregarUsuario(usuario);
    assertNotNull(usuario.getId());
  }

  @Test
  void sePersistenLosUsuariosAdmin() {
    Usuario usuario = new Usuario("user1", "unaContraseña4525!", validadorPassword, true);
    repositorioUsuarios.agregarUsuario(usuario);
    assertTrue(usuario.esAdmin());
  }

  @Test
  void sePersistenDistintosUsuariosConDistintosRoles() {
    Usuario usuario1 = new Usuario("user1", "unaContraseña4525!", validadorPassword, true);
    Usuario usuario2 = new Usuario("user2", "unaContraseña4525!", validadorPassword, false);
    Usuario usuario3 = new Usuario("user3", "unaContraseña4525!", validadorPassword, false);

    repositorioUsuarios.agregarUsuario(usuario1);
    repositorioUsuarios.agregarUsuario(usuario2);
    repositorioUsuarios.agregarUsuario(usuario3);

    assertEquals(repositorioUsuarios.getUsuarios().size(), 3);
  }

  @Test
  void sePuedeTraerUnDuenioTeniendoSuUsuario() {

    Notificador notificadorPrueba = SharedExamples.crearNotificador();
    List<Notificador> notificadorList = new ArrayList<Notificador>();
    notificadorList.add(notificadorPrueba);
    Duenio duenio = SharedExamples.crearDuenio(notificadorList);
    repositorioDuenios.agregarDuenio(duenio);

    assertEquals(repositorioDuenios.getDuenio("pepelopez").getApellido(), "Snow");
  }


  @Test
  void sePersistenDistintasCaracteristicasIdeales() {
    CaracteristicaIdealBooleana tienePelo = new CaracteristicaIdealBooleana("Tiene pelo", Arrays.asList(true, false));
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");
    CaracteristicaIdealTextoLibre tamanio = new CaracteristicaIdealTextoLibre("Tamanio");

    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(tienePelo);
    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(peso);
    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(tamanio);

    assertEquals(repositorioCaracteristicas.getCaracteristicasIdeales().size(), 3);
  }


}
*/
