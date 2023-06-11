package controllers;

import models.Asociacion.Asociacion;
import models.Asociacion.Publicacion.PublicacionSinChapita;
import models.Mascota.Caracteristica.CaracteristicaIdeal;
import models.Mascota.MascotaPerdida;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Notificador.MailSender;
import models.Notificador.Notificador;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Persona;
import models.Refugios.BuscadorRefugios;
import models.Refugios.Ubicacion;
import models.Refugios.api.RefugiosService;
import models.Repositorios.RepositorioCaracteristicas;
import models.Repositorios.RepositorioMascotasPerdidas;
import models.Repositorios.RepositorioPublicacionesSinChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class MascotasEncontradasSinChapitaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView mascotaEncontradaSinChapita(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    List<CaracteristicaIdeal> caracteristicasIdeales = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales();
    modelo.put("caracteristicasIdeales", caracteristicasIdeales);
    return getModel(request, response, "mascotasEncontradasSinChapita.html.hbs", modelo);
  }

  public Void crearPublicacionSinChapita(Request req, Response res) {
    LocalDate fechaDeEncuentro = LocalDate.parse(req.queryParams("fechaEncuentro"));
    TipoDocumento tipoDocumento;
    String foto;
    switch (req.queryParams("tipoDocumento").toUpperCase()) {
      case "PASAPORTE":
        tipoDocumento = TipoDocumento.PASAPORTE;
        break;
      case "LC":
        tipoDocumento = TipoDocumento.LC;
        break;
      case "LR":
        tipoDocumento = TipoDocumento.LR;
        break;
      default:
        tipoDocumento = TipoDocumento.DNI;
        break;
    }
    Documento documento = new Documento(tipoDocumento, Integer.parseInt(req.queryParams("numeroDocumento")));
    LocalDate fechaDeNacimiento = LocalDate.parse(req.queryParams("fechaNacimiento"));

    //CONTACTOS

    List<Contacto> contactos = new ArrayList<>();
    int nContacto = 1;
//    while (req.queryParamOrDefault("nombreContacto" + nContacto, null) != null) {
      Contacto contacto = new Contacto(req.queryParams("nombreContacto" + nContacto), req.queryParams("apellidoContacto" + nContacto), Integer.parseInt(req.queryParams("telefonoContacto" + nContacto)), req.queryParams("emailContacto" + nContacto));
      contactos.add(contacto);
      nContacto++;
//    }

    Persona persona = new Persona(req.queryParams("nombre"), req.queryParams("apellido"), documento, fechaDeNacimiento, contactos);
    // Ubicación solo nombre, lat long hardcodeado.
    String direccion = req.queryParams("lugar");
    Ubicacion lugar = new Ubicacion(direccion, 15654789.2, 65489.3);

    // CARACTERISTICAS
    List<CaracteristicaIdeal> caracteristicasIdeales = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales();
    List<String> caracteristicasSensibles = new ArrayList<>();
    caracteristicasIdeales.forEach(caracteristicaIdeal -> caracteristicasSensibles.add(req.queryParams(caracteristicaIdeal.getDescripcion())));

    // Lo que se me ocurrio para tipo y tamanio
    TipoMascota tipo;
    if (req.queryParams("tipo").equalsIgnoreCase("GATO")) {
      foto = "https://www.elindependiente.com/wp-content/uploads/2019/08/dia-gato.jpg";
      tipo = TipoMascota.GATO;
    } else {
      foto = "https://www.europeandailynews.org/wp-content/uploads/2019/09/perro-animal-vertebrado.jpg";
      tipo = TipoMascota.PERRO;
    }
    List<String> fotos = Arrays.asList(foto);
    TamanioMascota tamanio;
    switch (req.queryParams("tamanio").toUpperCase()) {
      case "CHICA":
        tamanio = TamanioMascota.CHICA;
        break;
      case "GRANDE":
        tamanio = TamanioMascota.GRANDE;
        break;
      default:
        tamanio = TamanioMascota.MEDIANA;
        break;
    }

    MascotaPerdida nuevaMascota = new MascotaPerdida(fotos, req.queryParams("descripcionMascota"), lugar, tipo, tamanio, caracteristicasSensibles);
    PublicacionSinChapita nuevaPublicacion = new PublicacionSinChapita(persona, direccion, nuevaMascota, fechaDeEncuentro,
        Arrays.asList(new MailSender()), new BuscadorRefugios(null));

    // El notificador hay que sacar la logica segun el check
    // Buscador refugios es hardcodeado

    withTransaction(() -> {
      entityManager().persist(persona); // --> esto debería ir a un controller?
      RepositorioMascotasPerdidas.getInstance().agregar(nuevaMascota);
      RepositorioPublicacionesSinChapita.getInstance().agregar(nuevaPublicacion);
    });
    res.redirect("/mascotasPerdidas");
    return null;
  }
}
