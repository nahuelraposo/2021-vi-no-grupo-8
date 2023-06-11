package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
  public ModelAndView getModel(Request request, Response response, String view, HashMap<String, Object> modelo) {
    modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
    modelo.put("admin", request.session().attribute("user_admin"));
    return new ModelAndView(modelo, view);
  }
}
