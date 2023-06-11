package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;

public class MascotasEncontradasController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView mascotaEncontrada(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    return getModel(request, response, "mascotaEncontradaUbicarDuenio.html.hbs", modelo);
  }
}
