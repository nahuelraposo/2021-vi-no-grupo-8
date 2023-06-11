package controllers;

import models.Mascota.MascotaPerdida;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Refugios.Ubicacion;
import models.Repositorios.RepositorioMascotasPerdidas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.*;

public class MascotasPerdidasController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView mascotasPerdidas(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    List<MascotaPerdida> mascotasPerdidas = RepositorioMascotasPerdidas.getInstance().listarMascotasPerdidas();
    modelo.put("mascotasPerdidas", mascotasPerdidas);
    return getModel(request, response, "mascotasPerdidas.html.hbs", modelo);
  }
}
