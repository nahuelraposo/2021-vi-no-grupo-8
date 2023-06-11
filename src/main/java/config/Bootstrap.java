package config;

import models.Mascota.*;
import models.Mascota.Caracteristica.CaracteristicaIdealBooleana;
import models.Mascota.Caracteristica.CaracteristicaIdealStrings;
import models.Mascota.Caracteristica.CaracteristicaIdealTextoLibre;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleBooleana;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleString;
import models.Notificador.MailSender;
import models.Notificador.Notificador;
import models.Notificador.services.JavaMailService;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Duenio;
import models.Personas.Persona;
import models.Refugios.Ubicacion;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.ValidadorPassword;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      persistirUsers();
      persistirCaracteristicasIdeales();
      persistirChapita();
      persistirMascotasPerdidas();
    });
  }

  private void persistirUsers() {
    ValidadorPassword validadorPassword = new ValidadorPassword();
    Usuario usuario1 = new Usuario("admin_user@rescateDePatitas.com", "admin", validadorPassword, true);
    Usuario usuario2 = new Usuario("test_user@rescateDePatitas.com", "testing123", validadorPassword, false);
    Duenio duenio1 = generarDuenioRegistrado(usuario1);
    Duenio duenio2 = generarDuenioRegistrado(usuario2);
    persist(usuario1);
    persist(usuario2);
    persist(duenio1);
    persist(duenio2);
  }


  private void persistirCaracteristicasIdeales() {
    CaracteristicaIdealStrings comportamiento = new CaracteristicaIdealStrings("Comportamiento", Arrays.asList("Bueno", "Malo", "No sé"));
    CaracteristicaIdealTextoLibre tienePelo = new CaracteristicaIdealTextoLibre("Largo del pelo");
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");
    CaracteristicaIdealTextoLibre tamanio = new CaracteristicaIdealTextoLibre("Color de ojos");
    persist(comportamiento);
    persist(tienePelo);
    persist(peso);
    persist(tamanio);
  }

  private void persistirChapita() {
    ValidadorPassword validadorPassword = new ValidadorPassword();
    Chapita chapita = new Chapita(12345,generarDuenioRegistrado(new Usuario("boca@rescateDePatitas.com", "admin", validadorPassword, false)));
    persist(chapita);
  }

  private void persistirMascotasPerdidas(){
    MascotaPerdida mascotaPerdida1 = crearMascotaPerdida(TipoMascota.GATO, TamanioMascota.CHICA, "San Martin, Buenos Aires", "https://www.elindependiente.com/wp-content/uploads/2019/08/dia-gato.jpg", "Apareció dando en la terraza en la noche");
    MascotaPerdida mascotaPerdida2 = crearMascotaPerdida(TipoMascota.PERRO, TamanioMascota.MEDIANA, "San Isidro, Buenos Aires", "https://www.europeandailynews.org/wp-content/uploads/2019/09/perro-animal-vertebrado.jpg", "Encontrado en la vía pública");
    MascotaPerdida mascotaPerdida3 = crearMascotaPerdida(TipoMascota.PERRO, TamanioMascota.GRANDE, "San Rafael, Mendoza", "https://www.significados-nombres.com/wp-content/uploads/2017/10/perro-hembra-grande.jpg", "Encontrado en la zona turística");
    persist(mascotaPerdida1);
    persist(mascotaPerdida2);
    persist(mascotaPerdida3);
  }

  //Creaciones para persistir
  private MascotaPerdida crearMascotaPerdida(TipoMascota tipo, TamanioMascota tamanio, String direccion, String imagen, String descripcion) {
    List<String> fotos = new ArrayList<>();
    Ubicacion lugar = new Ubicacion(direccion, 15654789.2,65489.3);
    fotos.add(imagen);
    List<String> caracteristicas = new ArrayList<>();
    caracteristicas.add("Tranquila");
    caracteristicas.add("Juguetona");
    caracteristicas.add("Manchas en sus patitas");
    return new MascotaPerdida(fotos, descripcion, lugar, tipo, tamanio, caracteristicas);
  }

  public Duenio crearDuenioConMascota() {
    ValidadorPassword validadorPassword = new ValidadorPassword();
    Duenio duenio = generarDuenioRegistrado(new Usuario("duenio1@rescateDePatitas.com", "admin", validadorPassword, false));
    Mascota mascota = crearMascotaDePrueba(TipoMascota.GATO, TamanioMascota.CHICA);
    duenio.agregarMascota(mascota);
    return duenio;
  }

  public List<CaracteristicaSensible> listadoDeCaracteristicasSensiblesDePrueba() {
    CaracteristicaIdealBooleana tienePelo = new CaracteristicaIdealBooleana("Tiene pelo", Arrays.asList(true, false));
    CaracteristicaIdealTextoLibre peso = new CaracteristicaIdealTextoLibre("Peso");
    CaracteristicaSensible c2 = new CaracteristicaSensibleBooleana(tienePelo, true);
    CaracteristicaSensible c3 = new CaracteristicaSensibleBooleana(tienePelo, false);
    CaracteristicaSensible c4 = new CaracteristicaSensibleString(peso, "1.15");
    return new ArrayList<>(Arrays.asList(c2, c3, c4));
  }

  public Mascota crearMascotaDePrueba(TipoMascota tipo, TamanioMascota tamanio) {
    List<String> fotos = new ArrayList<>(Arrays.asList("foto1.jpg", "foto2.jpg"));
    List<CaracteristicaSensible> caracteristicas = listadoDeCaracteristicasSensiblesDePrueba();
    return new Mascota(tipo, "Mas", "Cota", 5, 'M', tamanio, "Una mascota de prueba", fotos, caracteristicas);
  }

  public Duenio generarDuenioRegistrado(Usuario usuario) {
    JavaMailService javaMailService = new JavaMailService("tuvieja@gmail.com","12345");
    MailSender mailSender = new MailSender(javaMailService);
    List<Notificador> notificadores = Arrays.asList(mailSender);
    return new Duenio(persona(), usuario, notificadores);
  }

  public Persona persona() {
    return new Persona("Jon", "Snow", generarDocumentoDePrueba(), crearFechaDeNacimiento(), generarContactos());
  }

  public List<Contacto> generarContactos() {
    Contacto contacto1 = new Contacto("Maria", "Gomez", 1135811485, "vengaElMail@gmail.com");
    Contacto contacto2 = new Contacto("Roberto", "Galileo", 1136150987, "algoBienExpresivo@gmail.com");
    return new ArrayList<>(Arrays.asList(contacto1, contacto2));
  }

  public Documento generarDocumentoDePrueba() {
    return new Documento(TipoDocumento.DNI, 12345678);
  }

  public LocalDate crearFechaDeNacimiento() {
    return LocalDate.of(1982, 3, 22);
  }
}
