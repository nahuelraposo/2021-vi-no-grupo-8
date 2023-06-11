package controllers;

import models.Asociacion.Publicacion.PublicacionConChapita;
import models.Mascota.Caracteristica.CaracteristicaIdeal;
import models.Mascota.Chapita;
import models.Mascota.MascotaPerdida;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Notificador.Notificador;
import models.Personas.Contacto;
import models.Personas.Documento.Documento;
import models.Personas.Documento.TipoDocumento;
import models.Personas.Persona;
import models.Refugios.Ubicacion;
import models.Repositorios.RepositorioCaracteristicas;
import models.Repositorios.RepositorioChapitas;
import models.Repositorios.RepositorioMascotasPerdidas;
import models.Repositorios.RepositorioPublicacionesConChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class MascotasEncontradasConChapitaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView mascotaEncontradaConChapita(Request request, Response response) {
    String id = request.queryParams("chapitaId");
    RepositorioChapitas repo = RepositorioChapitas.getInstance();
    List<CaracteristicaIdeal> caracteristicasIdeales = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales();
    HashMap<String, Object> modelo = new HashMap<>();
    if (id != null && !id.isEmpty() && repo.chapitaRegistrada(Integer.parseInt(id))) {
      modelo.put("chapitaId", id);
      modelo.put("caracteristicasIdeales", caracteristicasIdeales);
      return getModel(request, response, "mascotasEncontradasConChapita.html.hbs", modelo);
    } else
      return getModel(request, response, "mascotaEncontradaUbicarDuenio.html.hbs", modelo);
  }

  public Void crearPublicacionConChapita(Request req, Response res) {
    List<Contacto> contactos = new ArrayList<>();

    //CREO LOS CONTACTOS
    int nContacto = 1;
//    while (req.queryParamOrDefault("nombreContacto" + nContacto, null) != null) {
      Contacto contacto = new Contacto(req.queryParams("nombreContacto" + nContacto), req.queryParams("apellidoContacto" + nContacto), Integer.parseInt(req.queryParams("telefonoContacto" + nContacto)), req.queryParams("emailContacto" + nContacto));
      contactos.add(contacto);
      nContacto++;
//    }

    //TIPO DE DOCUMENTO
    TipoDocumento tipoDocumento;
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

    //CREO AL RESCATISTA
    Persona rescatista = new Persona(req.queryParams("nombreRescatista"), req.queryParams("apellidoRescatista"),
        new Documento(tipoDocumento, Integer.parseInt(req.queryParams("nroDocumento"))),
        LocalDate.parse(req.queryParams("fechaNacimientoRescatista")), contactos);

    // Fotos hardcodeada --> podemos pensar si gato una, si perro otra.
    List<String> fotos = Arrays.asList("https://www.europeandailynews.org/wp-content/uploads/2019/09/perro-animal-vertebrado.jpg");

    // Ubicación solo nombre, lat long hardcodeado.
    String direccion = req.queryParams("lugar");
    Ubicacion lugar = new Ubicacion(direccion, 15654789.2, 65489.3);

    // tipo y tamanio
    TipoMascota tipo;
    if (req.queryParams("tipo").equalsIgnoreCase("GATO")) {
      tipo = TipoMascota.GATO;
    } else {
      tipo = TipoMascota.PERRO;
    }

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

    // CARACTERISTICAS
    List<CaracteristicaIdeal> caracteristicasIdeales = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales();
    List<String> caracteristicasSensibles = new ArrayList<>();
    caracteristicasIdeales.forEach(caracteristicaIdeal -> caracteristicasSensibles.add(req.queryParams(caracteristicaIdeal.getDescripcion())));

    //CREO A LA MASCOTA PERDIDA TODO VER LO DE LAS FOTOS
    MascotaPerdida mascotaPerdida = new MascotaPerdida(fotos, req.queryParams("descripcionMascota"), lugar, tipo, tamanio, caracteristicasSensibles);

    //CREO LA CHAPITA
    Chapita chapita = RepositorioChapitas
        .getInstance()
        .obtenerChapita(Integer.parseInt(req.queryParams("id")));

    PublicacionConChapita publicacionConChapita = new PublicacionConChapita(rescatista, direccion, chapita, mascotaPerdida,
        LocalDate.parse(req.queryParams("fechaEncuentro")), null);

    withTransaction(() -> {
      RepositorioPublicacionesConChapita.getInstance().agregar(publicacionConChapita);
    });
    res.redirect("/mascotasPerdidas");
    return null;
  }

}
