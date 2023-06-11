package controllers;

import models.Mascota.Mascota;
import models.Personas.Duenio;
import models.Repositorios.RepositorioDuenios;
import models.Repositorios.RepositorioUsuarios;
import models.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersController extends BaseController {

  public ModelAndView mostrarAdministracionCaracteristicas(Request req, Response res) {
    HashMap<String, Object> modelo = new HashMap<>();
    if (adminLogueado(req)) {
      return getModel(req, res, "administrarCaracteristicas.html.hbs", modelo);
    }
    res.redirect("/");
    return null;
  }

  private Usuario traerUsuarioSesion(Request req) {
    if (req.session().attribute("user_id") != null) {
      return RepositorioUsuarios.getInstance().getById(req.session().attribute("user_id"));
    }
    return null;
  }

  public ModelAndView getMascotas(Request request, Response response) {
    if (request.session().attribute("user_id") == null) {
      response.redirect("/login");
      return null;
    }

    Usuario usuario = RepositorioUsuarios.getInstance().getById(request.session().attribute("user_id"));
    Duenio duenio = RepositorioDuenios.getInstance().getDuenio(usuario.getUsername());
    List<Mascota> mascotas = duenio.getMascotas();

    HashMap<String, Object> modelo = new HashMap<>();
    modelo.put("mascotas", mascotas);
    return getModel(request, response, "mascotasDeUnDuenio.html.hbs", modelo);

  }

  private Boolean adminLogueado(Request req) {
    Usuario usuarioLogueado = traerUsuarioSesion(req);
    if (usuarioLogueado == null) {
      return false;
    }
    return usuarioLogueado.esAdmin();
  }
}
