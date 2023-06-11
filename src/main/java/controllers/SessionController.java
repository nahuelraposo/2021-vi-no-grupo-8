package controllers;

import models.Excepciones.PasswordInseguraException;
import models.Notificador.MailSender;
import models.Notificador.Notificador;
import models.Notificador.services.JavaMailService;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Duenio;
import models.Personas.Persona;
import models.Repositorios.RepositorioDuenios;
import models.Repositorios.RepositorioUsuarios;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.HashMap;

public class SessionController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView showLogin(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    return getModel(request, response, "index.html.hbs", modelo);
  }

  public Void createSession(Request request, Response response) {
    Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(request.queryParams("email"));
    try {
      if (esUsuarioValido(request, usuario)) {
        validarUsuarioAdmin(request, usuario);
        setSessionOptions(request, usuario);
      } else {
        System.out.println("El usuario o contraseña son incorrectos");
      }
      response.redirect("/");
      return null;
    } catch (NoSuchElementException e) {
      response.redirect("/");
      return null;
    }
  }

  private void setSessionOptions(Request request, Usuario usuario) {
    request.session(true);
    request.session().attribute("user_id", usuario.getId());
  }

  private boolean esUsuarioValido(Request request, Usuario usuario) {
    return usuario != null && Objects.equals(request.queryParams("password"), usuario.getPassword());
  }

  private void validarUsuarioAdmin(Request request, Usuario usuario) {
    request.session().attribute("user_admin", usuario.esAdmin());
  }

  public Void logout(Request request, Response response) {
    request.session(false);
    request.session().removeAttribute("user_id");
    request.session().removeAttribute("user_admin");
    response.redirect("/");
    return null;
  }

  public ModelAndView register(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    ValidadorPassword validadorPassword = new ValidadorPassword();
    Usuario usuario;

    validadorPassword.agregarValidaciones(new LongitudPassword());
    validadorPassword.agregarValidaciones(new NoEstaEntrePasswordsMasInseguras());

    try {
      usuario = new Usuario(request.queryParams("email"), request.queryParams("password"), validadorPassword, false);
    } catch (PasswordInseguraException e) {
      modelo.put("message", e.getMessage());
      modelo.put("icon", "&#10060");
      return new ModelAndView(modelo, "messages.html.hbs");
    }

    if (RepositorioUsuarios.getInstance().getUsuario(usuario.getUsername()) == null) {
      withTransaction(() -> {
        RepositorioUsuarios.getInstance().agregarUsuario(usuario);
        RepositorioDuenios.getInstance().agregarDuenio(getDuenioCreado(request, usuario));
      });
    } else {
      System.out.println("El usuario ya existe!");
      modelo.put("message", "El usuario ya existe!");
      modelo.put("icon", "&#10060");
      return new ModelAndView(modelo, "messages.html.hbs");
    }
    modelo.put("message", "Usted se ha registrado correctamente");
    modelo.put("icon", "&#9989");
    return new ModelAndView(modelo, "index.html.hbs");
  }

  private Duenio getDuenioCreado(Request request, Usuario usuario) {
    String nombre = request.queryParams("firstName");
    String apellido = request.queryParams("lastName");
    LocalDate fechaNacimientoPersona = LocalDate.parse(request.queryParams("fechaNacimiento"));
    TipoDocumento tipoDocumento = TipoDocumento.valueOf(request.queryParams("tipoDocumento").toUpperCase());
    MailSender mailDeContacto = new MailSender(new JavaMailService("tuvieja@gmail.com", "12345"));
    Documento documento = new Documento(tipoDocumento, Integer.parseInt(request.queryParams("numeroDocumento")));
    List<Notificador> notificadores = Arrays.asList(mailDeContacto);

    List<Contacto> contactos = setContactosOptions(request, nombre, apellido);
    Persona persona = new Persona(nombre, apellido, documento, fechaNacimientoPersona, contactos);
    return new Duenio(persona, usuario, notificadores);
  }

  private List<Contacto> setContactosOptions(Request request, String nombre, String apellido) {
    Contacto contacto = new Contacto(nombre, apellido, Integer.parseInt(request.queryParams("telefonoContacto")), request.queryParams("mailContacto"));
    return Arrays.asList(contacto);
  }
}
