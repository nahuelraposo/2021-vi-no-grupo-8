package RescateDePatitasApp;

import models.Asociacion.Asociacion;
import models.Mascota.Caracteristica.CaracteristicaIdealBooleana;
import models.Mascota.Caracteristica.CaracteristicaIdealTextoLibre;
import models.Mascota.Mascota;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Asociacion.Pregunta;
import models.Asociacion.PreguntaRespondida;
import models.Asociacion.Publicacion.Preferencia;
import models.Asociacion.Publicacion.PublicacionInteresado;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleBooleana;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleString;
import models.Notificador.*;
import models.Notificador.services.JavaMailService;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Duenio;
import models.Personas.Persona;
import models.Repositorios.RepositorioAsociaciones;
import models.Repositorios.RepositorioPreguntas;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.ValidadorPassword;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RescateDePatitasApp { // implements Job {

  //   public void execute(JobExecutionContext jobExecutionContext) throws  JobExecutionException {
  public static void main(String[] args) {
    RepositorioAsociaciones repositorioAsociaciones = new RepositorioAsociaciones();
    RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas();

    Pregunta preguntaPatio =
        new Pregunta<>("¿Tu mascota necesita un patio?", "¿Tenes patio?", Arrays.asList(true, false));

    Pregunta preguntaCantMascotas =
        new Pregunta<>("¿Con cuantas mascotas le gusta estar a la tuya?", "¿Cuantas mascotas tenes?", Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    Pregunta preguntaCantAmbientes =
        new Pregunta<>("¿Cuantos ambientes necesita tu mascota?", "¿Cuantos ambientes tenes?", Arrays.asList(1, 2, 3, 4, 5, 6));

    repositorioPreguntas.agregarPreguntaBase(preguntaPatio);
    repositorioPreguntas.agregarPreguntaBase(preguntaCantMascotas);
    repositorioPreguntas.agregarPreguntaBase(preguntaCantAmbientes);

    //CREAMOS LAS ASOCIACIONES DE EJEMPLO
    Asociacion asociacionDeEjemplo1 = new Asociacion(repositorioPreguntas);
    Asociacion asociacionDeEjemplo2 = new Asociacion(repositorioPreguntas);

    repositorioAsociaciones.agregarAsociacion(asociacionDeEjemplo1);
    repositorioAsociaciones.agregarAsociacion(asociacionDeEjemplo2);

    //CREAMOS MASCOTAS DE EJEMPLO
    CaracteristicaIdealBooleana tienePelo = new CaracteristicaIdealBooleana("Tiene pelo", Arrays.asList(true, false));
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");
    CaracteristicaSensible c2 = new CaracteristicaSensibleBooleana(tienePelo, true);
    CaracteristicaSensible c3 = new CaracteristicaSensibleBooleana(tienePelo, false);
    CaracteristicaSensible c4 = new CaracteristicaSensibleString(peso, "1.15");
    List<CaracteristicaSensible> caracteristicas = new ArrayList<>(Arrays.asList(c2, c3, c4));
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));

    Mascota mascotaDeEjemplo1 =
        new Mascota(TipoMascota.PERRO, "Cliford", "Rojo", 5, 'M', TamanioMascota.GRANDE, "Hola", fotos, caracteristicas);
    Mascota mascotaDeEjemplo2 =
        new Mascota(TipoMascota.GATO, "Cliford", "Rojo", 5, 'M', TamanioMascota.GRANDE, "Hola", fotos, caracteristicas);

    //CREAMOS DUEÑOS DE EJEMPLO
    ValidadorPassword validadorPassword = new ValidadorPassword();
    JavaMailService javaMail = new JavaMailService("jarraloca1@gmail.com", "12345");
    Notificador notificadorPrueba = new MailSender(javaMail);

    Contacto contacto1 = new Contacto("Maria", "Gomez", 1135811485, "jarraloca2@gmail.com");
    List<Contacto> contactos1 = Arrays.asList(contacto1);
    Contacto contacto2 = new Contacto("Maria", "Gomez", 1135811485, "jarraloca3@gmail.com");
    List<Contacto> contactos2 = Arrays.asList(contacto2);

    Usuario usuario1 = new Usuario("duenio1", "usuario1prueba1", validadorPassword, false);
    List<Notificador> notificadores1 = Arrays.asList(notificadorPrueba);
    Persona persona1 = new Persona("Juan", "Perez", new Documento(TipoDocumento.DNI, 12345678), LocalDate.of(1982, 3, 22),
        contactos1);

    Usuario usuario2 = new Usuario("duenio2", "usuario1prueba1", validadorPassword, false);
    List<Notificador> notificadores2 = Arrays.asList(notificadorPrueba);
    Persona persona2 = new Persona("Juan", "Perez", new Documento(TipoDocumento.DNI, 12345678), LocalDate.of(1982, 3, 22),
        contactos2);

    Duenio duenioDeEjemplo1 = new Duenio(persona1, usuario1, notificadores1);
    Duenio duenioDeEjemplo2 = new Duenio(persona2, usuario2, notificadores2);

    duenioDeEjemplo1.agregarMascota(mascotaDeEjemplo1);
    duenioDeEjemplo2.agregarMascota(mascotaDeEjemplo2);

    //RESPONDEMOS PREGUNTAS
    PreguntaRespondida preguntaTenesPatioRespondida = preguntaPatio.responder(true);
    PreguntaRespondida preguntaCantAmbientesRespondida = preguntaCantAmbientes.responder(3);
    PreguntaRespondida preguntaCantMascotasRespondida = preguntaCantMascotas.responder(1);

    List<PreguntaRespondida> preguntasConRespuestasAsociadas = Arrays.asList(preguntaCantAmbientesRespondida, preguntaCantMascotasRespondida, preguntaTenesPatioRespondida);

    //CREAMOS UNAS PUBLICACIONES RANDOM PARA RECOMENDAR
    List<PublicacionMascotaEnAdopcion> publicacionesMascotasEnAdopcion = new ArrayList<>();

    PublicacionMascotaEnAdopcion publicacionDeEjemplo1 = new PublicacionMascotaEnAdopcion(duenioDeEjemplo1, mascotaDeEjemplo1,
        preguntasConRespuestasAsociadas);
    PublicacionMascotaEnAdopcion publicacionDeEjemplo2 = new PublicacionMascotaEnAdopcion(duenioDeEjemplo2, mascotaDeEjemplo2,
        preguntasConRespuestasAsociadas);

    publicacionesMascotasEnAdopcion.add(publicacionDeEjemplo1);
    publicacionesMascotasEnAdopcion.add(publicacionDeEjemplo2);

    //CREAMOS LAS PUBLICACIONES DE INTERESADOS
    Preferencia preferencias1 = new Preferencia(TipoMascota.PERRO, TamanioMascota.GRANDE, 'M', null);
    Preferencia preferencias2 = new Preferencia(TipoMascota.GATO, TamanioMascota.GRANDE, 'M', null);
    MailSender mailSender = new MailSender(javaMail);
    Contacto contacto = new Contacto("Julio", "Omar", 123, "1234@gmail.com");

    PublicacionInteresado publicacionInteresadoDeEjemplo1 =
        new PublicacionInteresado(preferencias1, "palermo@gmail.com", contacto, preguntasConRespuestasAsociadas, mailSender);
    PublicacionInteresado publicacionInteresadoDeEjemplo2 =
        new PublicacionInteresado(preferencias2, "riquelme@gmail.com", contacto, preguntasConRespuestasAsociadas, mailSender);

    asociacionDeEjemplo1.agregarInteresado(publicacionInteresadoDeEjemplo1);
    asociacionDeEjemplo2.agregarInteresado(publicacionInteresadoDeEjemplo2);
    //FINALMENTE EL MÉTODO QUE SE ENVIARÍA SEMANALMENTE SERÍA ESTE

    repositorioAsociaciones.enviarRecomendacionesAInteresados(publicacionesMascotasEnAdopcion);

  }


  //////////////////////////////////////////////////////////////////////////////////////////////////////
  //METODOS QUE NECESITO PARA HACER UN MAIN DE PRUEBA ///////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public Duenio generarDuenioRegistrado(String username, String email) {
    ValidadorPassword validadorPassword = new ValidadorPassword();

    Usuario usuario = new Usuario(username, "usuario1prueba1", validadorPassword, false);
    JavaMailService javaMail = new JavaMailService(email, "12345");
    Notificador notificadorPrueba = new MailSender(javaMail);
    List<Notificador> notificadores = Arrays.asList(notificadorPrueba);
    Persona persona = new Persona("Juan", "Perez", new Documento(TipoDocumento.DNI, 12345678), LocalDate.of(1982, 3, 22),
        generarContactos(email));
    return new Duenio(persona, usuario, notificadores);
  }

  public List<Contacto> generarContactos(String email) {
    Contacto contacto1 = new Contacto("Maria", "Gomez", 1135811485, email);
    return new ArrayList<>(Arrays.asList(contacto1));
  }

  public Mascota crearMascotaDePrueba(TipoMascota tipoMascota) {
    List<CaracteristicaSensible> caracteristicas = listadoDeCaracteristicasDePrueba();
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    return new Mascota(tipoMascota, "Cliford", "Rojo", 5, 'M', TamanioMascota.GRANDE, "Hola", fotos, caracteristicas);
  }

  public List<CaracteristicaSensible> listadoDeCaracteristicasDePrueba() {
    CaracteristicaIdealBooleana tienePelo = new CaracteristicaIdealBooleana("Tiene pelo", Arrays.asList(true, false));
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");

    CaracteristicaSensible c2 = new CaracteristicaSensibleBooleana(tienePelo, true);
    CaracteristicaSensible c3 = new CaracteristicaSensibleBooleana(tienePelo, false);
    CaracteristicaSensible c4 = new CaracteristicaSensibleString(peso, "1.15");
    return new ArrayList<>(Arrays.asList(c2, c3, c4));
  }

}
