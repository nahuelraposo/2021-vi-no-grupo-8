package support;

import models.Asociacion.Asociacion;
import models.Mascota.Caracteristica.CaracteristicaIdealBooleana;
import models.Mascota.Caracteristica.CaracteristicaIdealTextoLibre;
import models.Asociacion.Pregunta;
import models.Asociacion.PreguntaRespondida;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleBooleana;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleString;
import models.Mascota.Mascota;
import models.Mascota.TipoMascota;
import models.Mascota.TamanioMascota;
import models.Notificador.Notificador;
import models.Notificador.SMSSender;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Duenio;
import models.Personas.Persona;
import models.Refugios.dto.Admision;
import models.Refugios.dto.Hogar;
import models.Refugios.dto.Respuesta;
import models.Refugios.dto.UbicacionDto;
import models.Repositorios.RepositorioPreguntas;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

public class SharedExamples {

  public SharedExamples() {
  }

  public static PublicacionMascotaEnAdopcion crearPublicacionAdopcion(List<PreguntaRespondida> necesidades) {
    return new PublicacionMascotaEnAdopcion(generarDuenioRegistrado(), crearMascotaDePrueba(TipoMascota.GATO,
        TamanioMascota.CHICA), necesidades);
  }

  public static Asociacion crearAsociacionConPreguntasBase(RepositorioPreguntas repo) {
    return new Asociacion(repo);
  }

  public static Pregunta preguntaNecesidadPatio(){
    return new Pregunta<>("¿Tu mascota necesita un patio?","¿Tenes patio?", Arrays.asList(true, false));
  }
  public static Pregunta preguntaCantidadMascotas(){
    return new Pregunta<>("¿Con cuantas mascotas le gusta estar a la tuya?","¿Cuantas mascotas tenes?", Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
  }
  public static Pregunta preguntaCantidadAmbientes(){
    return new Pregunta<>("¿Cuantos ambientes necesita tu mascota?","¿Cuantos ambientes tenes?", Arrays.asList(1,2,3,4,5,6));
  }

  public static Duenio crearDuenio(List<Notificador> notificadores) {
    ValidadorPassword validadorPassword = new ValidadorPassword();
    validadorPassword.agregarValidaciones(new LongitudPassword());
    return new Duenio(
        persona(),
        new Usuario("pepelopez", "UNApasWORD18AB", validadorPassword, false),
        notificadores
    );
  }

  public static Notificador crearNotificador() {
    return new SMSSender();
  }

  public static Duenio generarDuenioRegistrado() {
    Usuario usuario = mock(Usuario.class);
    Notificador notificadorPrueba = mock(Notificador.class);
    List<Notificador> notificadores = Arrays.asList(notificadorPrueba);
    return new Duenio(persona(), usuario, notificadores);
  }

  public static Mascota crearMascotaDePrueba(TipoMascota tipo, TamanioMascota tamanio) {
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    List<CaracteristicaSensible> caracteristicas = listadoDeCaracteristicasDePrueba();
    return new Mascota(tipo, "Mas", "Cota", 5, 'M', tamanio, "Una mascota de prueba", fotos, caracteristicas);
  }

  public static List<CaracteristicaSensible> listadoDeCaracteristicasDePrueba() {
    CaracteristicaIdealBooleana tienePelo = new CaracteristicaIdealBooleana("Tiene pelo", Arrays.asList(true, false));
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");
    CaracteristicaSensible c2 = new CaracteristicaSensibleBooleana(tienePelo, true);
    CaracteristicaSensible c3 = new CaracteristicaSensibleBooleana(tienePelo, false);
    CaracteristicaSensible c4 = new CaracteristicaSensibleString(peso, "1.15");
    return new ArrayList<>(Arrays.asList(c2, c3, c4));
  }

  static LocalDate crearFechaDeNacimiento() {
    return LocalDate.of(1982, 3, 22);
  }

  static Documento generarDocumentoDePrueba() {
    return new Documento(TipoDocumento.DNI, 12345678);
  }

  static List<Contacto> generarContactos() {
    Contacto contacto1 = new Contacto("Maria", "Gomez", 1135811485, "vengaElMail@gmail.com");
    Contacto contacto2 = new Contacto("Roberto", "Galileo", 1136150987, "algoBienExpresivo@gmail.com");
    return new ArrayList<>(Arrays.asList(contacto1, contacto2));
  }

  public static Persona persona() {
    return new Persona("Jon", "Snow", generarDocumentoDePrueba(), crearFechaDeNacimiento(), generarContactos());
  }

  //---HOGARES---
  public static Respuesta respuestaDeHogaresDisponibles() {
    return new Respuesta(1, "1", hogaresDeTransito());
  }

  public static List<Hogar> hogaresDeTransito() {
    Hogar unHogar = new Hogar("1",
        "Casita de Adopciones de Berazategui",
        new UbicacionDto("Cucha cucha, B1648, Provincia de Buenos Aires", -34.42061525423029,-58.572775488348505),
        "+541144018746",
        new Admision(true,false),
        200,
        0,
        false,
        new ArrayList<>(Collections.singletonList("Tranquilo"))
    );
    Hogar otroHogar = new Hogar("2",
        "Casa Rosada",
        new UbicacionDto("Balcarce 50, C1064, CABA", -34.6082026,-58.3724951),
        "+541112517357",
        new Admision(true,false),
        20,
        0,
        true,
        new ArrayList<>()
    );
    Hogar unTercerLugar = new Hogar("3",
        "Casita de Adopciones en Tigre",
        new UbicacionDto("Los Mimbres 162, B1648 DUB, Provincia de Buenos Aires", -34.42061525423029,-58.572775488348505),
        "+541144096338",
        new Admision(true,true),
        200,
        110,
        false,
        new ArrayList<>(Collections.singletonList("Manso"))
    );
    return new ArrayList<>(Arrays.asList(unHogar, otroHogar, unTercerLugar));
  }
}
