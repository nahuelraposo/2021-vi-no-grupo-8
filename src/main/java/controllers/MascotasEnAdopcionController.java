package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MascotasEnAdopcionController extends BaseController {

  public ModelAndView mascotasEnAdopcion(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    return getModel(request, response, "mascotasEnAdopcion.html.hbs", modelo);
  }
}
