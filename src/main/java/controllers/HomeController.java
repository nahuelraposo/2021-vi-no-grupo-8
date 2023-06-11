package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HomeController extends BaseController {
  public ModelAndView getHome(Request request, Response response) {
    HashMap<String, Object> modelo = new HashMap<>();
    return getModel(request, response, "index.html.hbs", modelo);
  }
}
