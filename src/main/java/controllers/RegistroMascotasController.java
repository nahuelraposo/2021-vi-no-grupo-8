package controllers;

import models.Mascota.Caracteristica.CaracteristicaIdeal;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Mascota;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Personas.Duenio;
import models.Repositorios.RepositorioCaracteristicas;
import models.Repositorios.RepositorioDuenios;
import models.Repositorios.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import models.Usuario.Usuario;

import java.util.*;

public class RegistroMascotasController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView formNuevaMascota(Request request, Response response) {
    if (request.session().attribute("user_id") == null) {
      response.redirect("/login");
      return null;
    }
    List<CaracteristicaIdeal> caracteristicasIdeales = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales();
    HashMap<String, Object> modelo = new HashMap<>();
    modelo.put("caracteristicasIdeales", caracteristicasIdeales);
    return getModel(request, response, "registraTuMascota.html.hbs", modelo);
  }

  public Void crearMascota(Request request, Response response) {
    if (request.session().attribute("user_id") == null) {
      response.redirect("/login");
      return null;
    }
    List<CaracteristicaSensible> caracteristicasSensibles = new ArrayList<>();
    for (int i = 0; i < RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales().size(); i++) {
      CaracteristicaIdeal caracteristicaIdeal = RepositorioCaracteristicas.getInstance().getCaracteristicasIdeales().get(i);
      CaracteristicaSensible caracteristicaSensible = caracteristicaIdeal.crearCaracteristicaSensible(request.queryParams(caracteristicaIdeal.getDescripcion()));
      caracteristicasSensibles.add(caracteristicaSensible);
    }
    String nombreMascota = request.queryParams("nombre");
    String apodoMascota = request.queryParams("apodo");
    int edadMascota = Integer.parseInt(request.queryParams("edad"));
    char sexoMascota = request.queryParams("sexo").charAt(0);
    TamanioMascota tamanioMascota = TamanioMascota.valueOf(request.queryParams("tamanio"));
    String descripcionMascota = request.queryParams("descripcionGeneral");
    TipoMascota tipo;
    String foto;
    if (request.queryParams("tipo").equalsIgnoreCase("GATO")) {
      foto = "https://www.elindependiente.com/wp-content/uploads/2019/08/dia-gato.jpg";
      tipo = TipoMascota.GATO;
    } else {
      foto = "https://www.europeandailynews.org/wp-content/uploads/2019/09/perro-animal-vertebrado.jpg";
      tipo = TipoMascota.PERRO;
    }
    List<String> fotos = Arrays.asList(foto);

    Mascota mascota = new Mascota(
        tipo,
        nombreMascota,
        apodoMascota,
        edadMascota,
        sexoMascota,
        tamanioMascota,
        descripcionMascota,
        fotos,
        caracteristicasSensibles
    );

    Usuario usuario = RepositorioUsuarios.getInstance().getById(request.session().attribute("user_id"));

    Duenio duenio = RepositorioDuenios.getInstance().getDuenio(usuario.getUsername());

    withTransaction(() -> {
      duenio.agregarMascota(mascota);
    });

    response.redirect("/mascotas");
    return null;
  }
}
